
package com.aim.locationfilter.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.aim.locationfilter.R;
import com.aim.locationfilter.interfaces.LocationFilterApplyCallBack;
import com.aim.locationfilter.interfaces.LocationFilterToggleCallBack;
import com.aim.locationfilter.model.CidLevelModel;
import com.aim.locationfilter.model.CityLevelModel;
import com.aim.locationfilter.model.DbaLevelModel;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.model.TerminalLevelModel;
import com.aim.locationfilter.utils.AppPreferences1;

import com.aim.locationfilter.utils.LocationFilterUtils;
import com.aim.locationfilter.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Keep
public class BTM_SelectLocationFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    View view;
    ImageView imgV_btm_sheet_cross;
    Fragment fragment = null;
    TextView tvTitle;
    Button btnConfirm;
    int tabPos = 0;
    boolean reloadTab = true;

    // data :
    private final AppPreferences1 appPreferences = new AppPreferences1();
    private LocationFilterApplyCallBack locationFilterApplyCallBack;
    private LocationFilterToggleCallBack locationFilterToggleCallBack;
    private String fromScreen;

    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<CidLevelModel> filterPojo = new ArrayList<>();

    public BTM_SelectLocationFragment(LocationFilterApplyCallBack locationFilterApplyCallBack) {
        this.locationFilterApplyCallBack = locationFilterApplyCallBack;
    }

    public BTM_SelectLocationFragment() {
        //empty constructor
    }

    public BTM_SelectLocationFragment(LocationFilterApplyCallBack locationFilterApplyCallBack, String fromScreen) {
        this.locationFilterApplyCallBack = locationFilterApplyCallBack;
        this.fromScreen = fromScreen;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.b_t_m_select_location_fragment, container, false);
        LocationFilterUtils.FilterCidLevel = LocationFilterUtils.getFilterCidList();
        filterPojo.clear();
        if (LocationFilterUtils.selectedCidLevel != null && LocationFilterUtils.selectedCidLevel.size() > 0) {
            filterPojo.addAll(LocationFilterUtils.selectedCidLevel);
            saveFilterCopy();
        } else {
            filterPojo.addAll(LocationFilterUtils.FilterCidLevel);
            if (filterPojo.size() > 0) {
                filterPojo.get(0).setSelected(true);
                for (int j = 0; j < filterPojo.get(0).getDbaLevelModels().size(); j++) {
                    filterPojo.get(0).getDbaLevelModels().get(j).setSelected(true);
                    for (int k = 0; k < filterPojo.get(0).getDbaLevelModels().get(j).getCityLevelList().size(); k++) {
                        filterPojo.get(0).getDbaLevelModels().get(j).getCityLevelList().get(k).setSelected(true);
                        for (int l = 0; l < filterPojo.get(0).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().size(); l++) {
                            filterPojo.get(0).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l).setSelected(true);
                        }
                    }
                }
                saveFilterCopy();
            }

        }

        initialise();

        Objects.requireNonNull(getDialog()).setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, android.view.KeyEvent event) {

                if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                    Bundle bundle = new Bundle();
                    //ApiNetworkUtils.captureEvent("location-device-back-clicked", "Location", "location", "device", "back", "clicked", bundle, getActivity());
                    dismiss();
                    return true; // pretend we've processed it
                } else {
                    return false;
                }
            }
        });

        return view;
    }

    private void saveFilterCopy() {
        try {
            Gson gson = new GsonBuilder().create();
            LocationFilterUtils.FILTER_JSON_COPY = gson.toJson(filterPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyFilterCopy() {
        try {
            Gson gson = new GsonBuilder().create();
            Type userListType = new TypeToken<ArrayList<CidLevelModel>>() {
            }.getType();

            ArrayList<CidLevelModel> userArray = gson.fromJson(LocationFilterUtils.FILTER_JSON_COPY, userListType);
            LocationFilterUtils.selectedCidLevel.clear();
            LocationFilterUtils.selectedCidLevel.addAll(userArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialise() {
        imgV_btm_sheet_cross = view.findViewById(R.id.imgV_btm_sheet_cross);
        imgV_btm_sheet_cross.setOnClickListener(this);

        tvTitle = view.findViewById(R.id.tvTitle);
        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewPager);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.avoidDoubleClicks(v);
                Bundle bundle = new Bundle();
                //ApiNetworkUtils.captureEvent("location-confirm-button-clicked", "Location", "location", "confirm", "button", "clicked", bundle, getActivity());
                ((FilterFragment) fragment).removeSearchChar(new FilterFragment.resetSearch() {
                    @Override
                    public void resetSearchText() {
                        if (validateFilterData()) {
                            resetFilterPojoSelectedCid(tabPos);
                            CidLevelModel selectedCidModel = filterPojo.get(tabPos);
                            ArrayList<DbaLevelModel> selectedDbaLevel = new ArrayList<>();
                            ArrayList<CityLevelModel> selectedcityLevelModels = new ArrayList<>();
                            ArrayList<MidLevelModel> selectedmidLevelModels = new ArrayList<>();
                            ArrayList<TerminalLevelModel> selectedterminalLevelModels = new ArrayList<>();

                            for (int i = 0; i < selectedCidModel.getDbaLevelModels().size(); i++) {
                                DbaLevelModel dbaLevelModel = selectedCidModel.getDbaLevelModels().get(i);
                                if (dbaLevelModel.isSelected()) {
                                    selectedDbaLevel.add(dbaLevelModel);
                                    for (int j = 0; j < selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                                        CityLevelModel cityLevelModel = selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().get(j);
                                        if (cityLevelModel.isSelected()) {
                                            selectedcityLevelModels.add(cityLevelModel);
                                            for (int k = 0; k < selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                                                MidLevelModel midLevelModel = selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k);
                                                if (midLevelModel.isSelected()) {
                                                    selectedmidLevelModels.add(midLevelModel);
                                                    for (int l = 0; l < selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
                                                        selectedterminalLevelModels.addAll(selectedCidModel.getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels());
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }

//                            for (int i = 0; i < selectedcityLevelModels.size(); i++) {
//                                Iterator<MidLevelModel> j = selectedcityLevelModels.get(i).getMidLevelModels().iterator();
//                                while (j.hasNext()) {
//                                    MidLevelModel cityLevelModelTemp = j.next();
//                                    if (!cityLevelModelTemp.isSelected()) {
//                                        j.remove();
//                                    }
//                                }
//                            }
                            LocationFilterUtils.selectedCidLevel.clear();
                            LocationFilterUtils.selectedCidLevel.addAll(filterPojo);
                            locationFilterApplyCallBack.allSelectedData(selectedCidModel, selectedDbaLevel, selectedcityLevelModels, selectedmidLevelModels, selectedterminalLevelModels);
                            dismiss();

                        } else {
                            //Toast.makeText(requireActivity().getApplicationContext(), getString(R.string.location_error), Toast.LENGTH_SHORT).show();
                            Utils.popUpDialog(requireActivity(), "", getString(R.string.location_error));
                        }
                    }
                });

            }
        });

        if (!TextUtils.isEmpty(fromScreen) && fromScreen.equalsIgnoreCase("reports")) {
            tvTitle.setText(getString(R.string.select_tids));
        }
        hardCoded();

    }

    private boolean validateFilterData() {
        for (int i = 0; i < filterPojo.get(tabPos).getDbaLevelModels().size(); i++) {
            for (int j = 0; j < filterPojo.get(tabPos).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                for (int k = 0; k < filterPojo.get(tabPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                    MidLevelModel midLevelModel = filterPojo.get(tabPos).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k);
                    if (midLevelModel.isSelected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imgV_btm_sheet_cross) {
            Bundle bundle = new Bundle();
            //ApiNetworkUtils.captureEvent("location-close-icon-clicked", "Location", "location", "close", "icon", "clicked", bundle, getActivity());
            applyFilterCopy();
            dismiss();
        }
    }


    private void hardCoded() {

        initAdapter();

    }

    private void initAdapter() {

        for (int i = 0; i < filterPojo.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(filterPojo.get(i).getCompanyName()));
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Bundle bundle = new Bundle();
                bundle.putString("position", (tab.getPosition() + 1) + "");
                //ApiNetworkUtils.captureEvent("location-company-tab-switched", "Location", "location", "company",
//                        "tab", "switched", bundle, requireActivity());


                viewPager.setCurrentItem(tab.getPosition());
                tabPos = tab.getPosition();
                if (reloadTab) {
                    selectSelectedCidFilter(tabPos);
                }
                reloadTab = true;
                ((FilterFragment) fragment).updateCid(tab.getPosition(), filterPojo.get(tab.getPosition()));
                //LoggerUtils.E("onTabSelected: ", Objects.requireNonNull(tab.getText()).toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //LoggerUtils.E("onTabUnselected: ", Objects.requireNonNull(tab.getText()).toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //LoggerUtils.E("onTabReselected: ", Objects.requireNonNull(tab.getText()).toString());
            }
        });

        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (filterPojo.size() > 1 && LocationFilterUtils.selectedCidLevel != null && LocationFilterUtils.selectedCidLevel.size() > 0) {
                    int selectedCid = 0;
                    for (int i = 0; i < filterPojo.size(); i++) {
                        if (filterPojo.get(i).isSelected()) {
                            selectedCid = i;
                            tabPos = i;
                        }
                    }
                    if (selectedCid != 0) {
                        reloadTab = false;
                        Objects.requireNonNull(tabLayout.getTabAt(selectedCid)).select();
                    }
                }
                viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });


    }

    private void selectSelectedCidFilter(int selectedCid) {
        filterPojo.get(selectedCid).setSelected(true);
        for (int j = 0; j < filterPojo.get(selectedCid).getDbaLevelModels().size(); j++) {
            filterPojo.get(selectedCid).getDbaLevelModels().get(j).setSelected(true);
            for (int k = 0; k < filterPojo.get(selectedCid).getDbaLevelModels().get(j).getCityLevelList().size(); k++) {
                filterPojo.get(selectedCid).getDbaLevelModels().get(j).getCityLevelList().get(k).setSelected(true);
                for (int l = 0; l < filterPojo.get(selectedCid).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().size(); l++) {
                    filterPojo.get(selectedCid).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l).setSelected(true);
                }
            }
        }
    }

    public class ViewPagerAdapter extends FragmentStatePagerAdapter {

        int mNumOfTabs;


        public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            this.mNumOfTabs = NumOfTabs;
        }


        @Override
        public Fragment getItem(int position) {
            fragment = new FilterFragment(position, filterPojo, new LocationFilterApplyCallBack() {

                @Override
                public void allSelectedData(CidLevelModel cidLevelModel, List<DbaLevelModel> dbaLevelModelList, List<CityLevelModel> cityLevelModelList, List<MidLevelModel> midLevelModelList, List<TerminalLevelModel> terminalLevelModelList) {

                }

                @Override
                public void onCidSelected(CidLevelModel cidLevelModel) {

                }

                @Override
                public void onDBASelected(List<DbaLevelModel> dbaLevelModelList) {

                }

                @Override
                public void onCitySelected(List<CityLevelModel> cityLevelModelList) {

                }

                @Override
                public void onMidSelected(List<MidLevelModel> midLevelModelList) {

                }

                @Override
                public void onTidSelected(List<TerminalLevelModel> terminalLevelModelList) {

                }
            }, false);
            return fragment;

        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BottomSheetDialog d = (BottomSheetDialog) dialog;
                        FrameLayout bottomSheet = d.findViewById(R.id.design_bottom_sheet);
                        if (bottomSheet != null) {
                            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }
                }, 0);
            }

        });
        return dialog;
    }


    public void resetFilterPojoSelectedCid(int selectedCidPosition) {
        for (int i = 0; i < filterPojo.size(); i++) {
            if (!(selectedCidPosition == i)) {
                filterPojo.get(i).setSelected(false);
                for (int j = 0; j < filterPojo.get(i).getDbaLevelModels().size(); j++) {
                    filterPojo.get(i).getDbaLevelModels().get(j).setSelected(false);
                    for (int k = 0; k < filterPojo.get(i).getDbaLevelModels().get(j).getCityLevelList().size(); k++) {
                        filterPojo.get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).setSelected(false);
                        for (int l = 0; l < filterPojo.get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().size(); l++) {
                            filterPojo.get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l).setSelected(false);
                        }
                    }
                }
            }
        }
    }
}