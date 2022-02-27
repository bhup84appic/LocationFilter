/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:55 PM
 *
 */

package com.aim.locationfilter.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.locationfilter.R;
import com.aim.locationfilter.adapters.SelectLocationExpandableAdapter;
import com.aim.locationfilter.adapters.UserListCorpnameAdapter;
import com.aim.locationfilter.adapters.UserListDBAAdapter;
import com.aim.locationfilter.interfaces.LocationFilterApplyCallBack;
import com.aim.locationfilter.interfaces.LocationFilterToggleCallBack;
import com.aim.locationfilter.interfaces.OnClickCIDCallBack;
import com.aim.locationfilter.interfaces.OnUpdateGenRepoListener;
import com.aim.locationfilter.interfaces.sheetListener;
import com.aim.locationfilter.model.CidLevelModel;
import com.aim.locationfilter.model.CityLevelModel;
import com.aim.locationfilter.model.CorporateIdModel;
import com.aim.locationfilter.model.DbaLevelModel;
import com.aim.locationfilter.model.Filter_user;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.model.User_City;
import com.aim.locationfilter.model.User_Location;
import com.aim.locationfilter.utils.AppPreferences1;
import com.aim.locationfilter.utils.Constants;
import com.aim.locationfilter.utils.Utils;


import java.util.ArrayList;
import java.util.List;

@Keep
public class FilterFragment extends Fragment implements View.OnClickListener, LocationFilterToggleCallBack, OnClickCIDCallBack {

    Activity mActivity;
    Context mContext;
    int mPos;

    View view;
    ImageView imgV_btm_sheet_cross;

    Button btn_btm_slct_loct_confirm;
    CheckBox checkbox_slct_loct_select_all;

    RecyclerView rv_slct_loct_dba, cityRecycler;
    List<CityLevelModel> citiesItemList = new ArrayList<>();
    List<MidLevelModel> user_locationList = new ArrayList<>();
    SelectLocationExpandableAdapter selectLocationExpandableAdapter;
    List<User_City> pass_citiesList = new ArrayList<>();
    List<User_Location> pass_locationList = new ArrayList<>();
    UserListDBAAdapter userListDBAAdapter;
    List<DbaLevelModel> user_dbaArrayList = new ArrayList<>();
    UserListCorpnameAdapter userListCorpnameAdapter;
    List<CidLevelModel> user_Filter_userslist = new ArrayList<>();
    OnUpdateGenRepoListener mOnUpdateGenRepoListener;
    boolean isCheckboxEnable = true;
    private TextView tv_slct_loct_clear, tv_slct_loct_locations_count, tv_slct_loct_city_count;
    private EditText edt_transaction_search;
    // data :
    private AppPreferences1 appPreferences = new AppPreferences1();
    private Filter_user filter_user_pref;
    // prashant logic :
//    private ViewGroup viewGroup;
//    private TreeNode root;
//    private TreeView treeView;
    private View view2;
    private LocationFilterApplyCallBack locationFilterApplyCallBack;
    private LocationFilterToggleCallBack locationFilterToggleCallBack;
    private final CorporateIdModel corporateIdModel = null;
    boolean isCashier;
    int selectedDbaLocation = 0;
    ArrayList<CidLevelModel> filterPojo = new ArrayList<>();
    resetSearch resetSearch;
    boolean resetSearchVar = false;

    public FilterFragment(int mPos, ArrayList<CidLevelModel> filterPojo, LocationFilterApplyCallBack locationFilterApplyCallBack, boolean isCashier) {
        // Required empty public constructor
        this.mPos = mPos;
        this.locationFilterApplyCallBack = locationFilterApplyCallBack;
        this.isCashier = isCashier;
        this.filterPojo = filterPojo;
    }

    public FilterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        appPreferences = new AppPreferences1();
        initialise();
    }

    private void initialise() {

        filter_user_pref = (Filter_user) appPreferences.getPreferenceObjectJson(requireActivity(), Constants.FILTER_DATA_JSON);

        rv_slct_loct_dba = view.findViewById(R.id.rv_slct_loct_dba);
        cityRecycler = view.findViewById(R.id.cityRecycler);

        checkbox_slct_loct_select_all = view.findViewById(R.id.checkbox_slct_loct_select_all);
        tv_slct_loct_locations_count = view.findViewById(R.id.tv_slct_loct_locations_count);
        tv_slct_loct_city_count = view.findViewById(R.id.tv_slct_loct_city_count);
        tv_slct_loct_clear = view.findViewById(R.id.tv_slct_loct_clear);
        edt_transaction_search = view.findViewById(R.id.edt_transaction_search);

        hardCoded();


        checkbox_slct_loct_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isCheckboxEnable) {
                    if (isChecked) {
                        selectFunction(true);
                        filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).setSelected(true);
                        userListDBAAdapter.notifyDataSetChanged();
                        selectLocationExpandableAdapter.notifyDataSetChanged();

                        Bundle bundle = new Bundle();
//                        ApiNetworkUtils.captureEvent("location-selectall-checkbox-selected", "Location", "location",
//                                "", "",
//                                "", bundle, requireActivity());
                    } else {
                        selectFunction(false);
                        filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).setSelected(false);
                        userListDBAAdapter.notifyDataSetChanged();
                        selectLocationExpandableAdapter.notifyDataSetChanged();

                        Bundle bundle = new Bundle();
//                        ApiNetworkUtils.captureEvent("location-selectall-checkbox-unselected", "Location", "location",
//                                "", "",
//                                "", bundle, requireActivity());
                    }
                }
                isCheckboxEnable = true;
            }
        });


        tv_slct_loct_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.avoidDoubleClicks(v);

                checkbox_slct_loct_select_all.setChecked(false);
                clearLocation();
                tv_slct_loct_city_count.setText(String.valueOf(0));
                tv_slct_loct_locations_count.setText(String.valueOf(0));

                Bundle bundle = new Bundle();
//                ApiNetworkUtils.captureEvent("location-clear-button-clicked", "Location", "location",
//                        "", "",
//                        "", bundle, requireActivity());
            }
        });


        edt_transaction_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                Bundle bundle = new Bundle();
                bundle.putString("characters", s.toString());
//                ApiNetworkUtils.captureEvent("location-search-action-successful", "Location", "location",
//                        "", "",
//                        "", bundle, requireActivity());

                selectLocationExpandableAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt_transaction_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                ApiNetworkUtils.captureEvent("location-search-icon-clicked", "Location", "location",
//                        "", "",
//                        "", bundle, requireActivity());
            }
        });

        setCityCount();
        setLocationCount();
        // prashant logic :
    }

    private void clearLocation() {
        for (int i = 0; i < filterPojo.get(mPos).getDbaLevelModels().size(); i++) {
            filterPojo.get(mPos).getDbaLevelModels().get(i).setSelected(false);
            for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).setSelected(false);
                for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                    filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).setSelected(false);
                    for (int l = 0; l < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
                        filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).setSelected(false);
                    }
                }
            }
        }
        checkbox_slct_loct_select_all.setChecked(false);
        userListDBAAdapter.notifyDataSetChanged();
        selectLocationExpandableAdapter.notifyDataSetChanged();
    }

    private void selectFunction(boolean selectParam) {
        for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().size(); j++) {
            filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).setSelected(selectParam);
            for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).setSelected(selectParam);
                for (int l = 0; l < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
                    filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).setSelected(selectParam);
                }
            }
        }
        updateCityLocationNumber();
    }

    private void updateCityLocationNumber() {
        setCityCount();
        setLocationCount();
    }

    private void setCityCount() {
        int cityCount = 0;
        for (int i = 0; i < filterPojo.get(mPos).getDbaLevelModels().size(); i++) {
            for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                if (filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).isSelected()) {
                    cityCount++;
                }
            }
        }
        tv_slct_loct_city_count.setText(String.valueOf(cityCount));
    }

    private void setLocationCount() {
        int locationCount = 0;
        for (int i = 0; i < filterPojo.get(mPos).getDbaLevelModels().size(); i++) {
            for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                    if (filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).isSelected()) {
                        locationCount++;
                    }
                }
            }
        }
        tv_slct_loct_locations_count.setText(String.valueOf(locationCount));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgV_btm_sheet_cross) {//dismiss();
        }
    }

    private void hardCoded() {

        initAdapter(0);

    }

    private void initAdapter(int position) {
        if (filterPojo != null && filterPojo.size() > 0) {
            user_dbaArrayList = filterPojo.get(position).getDbaLevelModels();
        } else {
            user_dbaArrayList = new ArrayList<>();
        }


        for (int i = 0; i < user_dbaArrayList.size(); i++) {
            citiesItemList = user_dbaArrayList.get(i).getCityLevelList();
            for (int j = 0; j < citiesItemList.size(); j++)
                user_locationList = citiesItemList.get(j).getMidLevelModels();
        }

        //LoggerUtils.E(citiesItemList.size() + " " + user_locationList.size() + "   Hiiiiiii123");


        userListDBAAdapter = new UserListDBAAdapter(getActivity(), filterPojo.get(position).getDbaLevelModels(), new UserListDBAAdapter.OnDbaClicked() {
            @Override
            public void onDbaCheckClicked(int cityPosition) {
//                edt_transaction_search.setText("");
//                userListDBAAdapter.notifyDataSetChanged();
                boolean selectParam = false;
                selectParam = !filterPojo.get(position).getDbaLevelModels().get(cityPosition).isSelected();
                filterPojo.get(position).getDbaLevelModels().get(cityPosition).setSelected(selectParam);
                for (int j = 0; j < filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().size(); j++) {
                    filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().get(j).setSelected(selectParam);
                    for (int k = 0; k < filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                        filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().get(j).getMidLevelModels().get(k).setSelected(selectParam);
                        for (int l = 0; l < filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
                            filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).setSelected(selectParam);
                        }
                    }
                }
                edt_transaction_search.setText("");
                selectedDbaLocation = cityPosition;
                checkForDataSelected();
                selectLocationExpandableAdapter = new SelectLocationExpandableAdapter(filterPojo.get(position).getDbaLevelModels().get(cityPosition).getCityLevelList(), getActivity(), isCashier, new sheetListener() {
                    @Override
                    public void openSheet(int locationPos, MidLevelModel midLevelModel) {
                        BTM_UserAccessTID fragment = new BTM_UserAccessTID(new OnUpdateGenRepoListener() {
                            @Override
                            public void updateListener(String btm_response, ArrayList<String> list) {

                            }

                            @Override
                            public void updateListener(String btm_response, ArrayList<String> selSctedlist, boolean b) {

                            }
                        }, midLevelModel);
                        fragment.show(getChildFragmentManager(), "");
                    }
                }, new SelectLocationExpandableAdapter.ClickedOnLocation() {
                    @Override
                    public void onLocationClicked(CityLevelModel cityLevelModel) {
                        isCheckboxEnable = false;
                        checkForDataSelected();
                    }
                });
                cityRecycler.setAdapter(selectLocationExpandableAdapter);
            }

            @Override
            public void onDbaItemClicked(int cityPostion) {
                edt_transaction_search.setText("");
                selectedDbaLocation = cityPostion;
                checkForDataSelected();
                selectLocationExpandableAdapter = new SelectLocationExpandableAdapter(filterPojo.get(position).getDbaLevelModels().get(cityPostion).getCityLevelList(), getActivity(), isCashier, new sheetListener() {
                    @Override
                    public void openSheet(int locationPos, MidLevelModel midLevelModel) {
                        BTM_UserAccessTID fragment = new BTM_UserAccessTID(new OnUpdateGenRepoListener() {
                            @Override
                            public void updateListener(String btm_response, ArrayList<String> list) {

                            }

                            @Override
                            public void updateListener(String btm_response, ArrayList<String> selSctedlist, boolean b) {

                            }
                        }, midLevelModel);
                        fragment.show(getChildFragmentManager(), "");
                    }
                }, new SelectLocationExpandableAdapter.ClickedOnLocation() {
                    @Override
                    public void onLocationClicked(CityLevelModel cityLevelModel) {
                        isCheckboxEnable = false;
                        checkForDataSelected();
                    }
                });
                cityRecycler.setAdapter(selectLocationExpandableAdapter);
            }
        });
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_slct_loct_dba.setLayoutManager(mLayoutManager2);
        rv_slct_loct_dba.setAdapter(userListDBAAdapter);


        selectLocationExpandableAdapter = new SelectLocationExpandableAdapter(filterPojo.get(position).getDbaLevelModels().get(0).getCityLevelList(), getActivity(), isCashier, new sheetListener() {
            @Override
            public void openSheet(int locationPos, MidLevelModel midLevelModel) {
                BTM_UserAccessTID fragment = new BTM_UserAccessTID(new OnUpdateGenRepoListener() {
                    @Override
                    public void updateListener(String btm_response, ArrayList<String> list) {

                    }

                    @Override
                    public void updateListener(String btm_response, ArrayList<String> selSctedlist, boolean b) {

                    }
                }, midLevelModel);
                fragment.show(getChildFragmentManager(), "");
            }
        }, new SelectLocationExpandableAdapter.ClickedOnLocation() {
            @Override
            public void onLocationClicked(CityLevelModel cityLevelModel) {
                checkForDataSelected();

            }
        });
        cityRecycler.setAdapter(selectLocationExpandableAdapter);
        checkForDataSelected();
    }

    private void checkForSelectAll() {
        boolean allSelected = true;
        for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().size(); j++) {
            if (!filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).isSelected()) {
                allSelected = false;
                break;
            }
            for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                if (!filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).isSelected()) {
                    allSelected = false;
                    break;
                }
            }
        }
        isCheckboxEnable = false;
        checkbox_slct_loct_select_all.setChecked(allSelected);
    }

    private void checkForDataSelected() {
//        for (int i = 0; i < filterPojo.get(mPos).getDbaLevelModels().size(); i++) {
//            filterPojo.get(mPos).getDbaLevelModels().get(i).setSelected(selectParam);
        for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().size(); j++) {

            boolean cityChecked = false;
            for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                if (filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).isSelected()) {
                    cityChecked = true;
                }
//                    for (int l = 0; l < filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
//                        filterPojo.get(mPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).setSelected(selectParam);
//                    }
            }
            filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).setSelected(cityChecked);
//            }
        }
        boolean isAllSelected = true;
        for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().size(); j++) {
            if (!(filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).isSelected())) {
                isAllSelected = false;
                break;
            }
            for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                if (!(filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).isSelected())) {
                    isAllSelected = false;
                    break;
                }
            }

        }

        isCheckboxEnable = false;
        checkbox_slct_loct_select_all.setChecked(isAllSelected);
        isCheckboxEnable = true;

        boolean isIteminDbaSelected = false;
        for (int j = 0; j < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().size(); j++) {
            for (int k = 0; k < filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                if (filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).getCityLevelList().get(j).getMidLevelModels().get(k).isSelected()) {
                    isIteminDbaSelected = true;
                    break;
                }
            }
            if (isIteminDbaSelected) {
                break;
            }
        }

        filterPojo.get(mPos).getDbaLevelModels().get(selectedDbaLocation).setSelected(isIteminDbaSelected);

        userListDBAAdapter.notifyDataSetChanged();
        selectLocationExpandableAdapter.notifyDataSetChanged();
        updateCityLocationNumber();
        if (resetSearch != null) {
            if (resetSearchVar) {
                resetSearch.resetSearchText();
            }
            resetSearchVar = false;
        }
        resetSearchVar = false;
    }


    @Override
    public void onExplicitNodeToggle() {
    }

    @Override
    public void onClickCidLocationDialog(CorporateIdModel corporateIdModel) {
    }


    public void updateCid(int mPosition, CidLevelModel cidLevelModel) {
        edt_transaction_search.setText("");
        mPos = mPosition;
        selectedDbaLocation = 0;
        initAdapter(mPosition);
        checkForDataSelected();
        isCheckboxEnable = true;
    }

    public void removeSearchChar(resetSearch resetSearch) {
        this.resetSearch = resetSearch;
        resetSearchVar = true;
        edt_transaction_search.setText("");
    }

    public interface resetSearch {
        void resetSearchText();
    }
}
