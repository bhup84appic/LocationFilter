/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:55 PM
 *
 */

package com.aim.locationfilter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.locationfilter.R;
import com.aim.locationfilter.adapters.LocationTIDAdapter;
import com.aim.locationfilter.interfaces.OnUpdateGenRepoListener;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.model.onSelectTIDListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


import java.util.ArrayList;

public class BTM_UserAccessTID extends BottomSheetDialogFragment implements View.OnClickListener {

    ImageView imgV_btm_sheet_cross;
    Button btn_btm_slct_tid_confirm;
    CheckBox checkbox_slct_tid_select_all;
    RecyclerView rv_slct_tid_cities;
    OnUpdateGenRepoListener mOnUpdateGenRepoListener;
    MidLevelModel mMidLevelModelList;
    ArrayList<String> strings = new ArrayList<>();
    TextView area_name;
    TextView area_tid_count;
    LocationTIDAdapter locationNameTIDAdapter;
    private View view;
    int userRole;

    public BTM_UserAccessTID() {
        // Required empty public constructor
    }

    public BTM_UserAccessTID(OnUpdateGenRepoListener callback, MidLevelModel mMidLevelModelList) {

        this.mOnUpdateGenRepoListener = callback;
        this.mMidLevelModelList = mMidLevelModelList;

    }

    public BTM_UserAccessTID(OnUpdateGenRepoListener callback, MidLevelModel mMidLevelModelList, int userRole) {

        this.mOnUpdateGenRepoListener = callback;
        this.mMidLevelModelList = mMidLevelModelList;
        this.userRole = userRole;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b_t_m__user_access_t_i_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialise(view);
    }

    private void initialise(View view) {

        imgV_btm_sheet_cross = view.findViewById(R.id.imgV_btm_sheet_cross);
        area_name = view.findViewById(R.id.area_name);
        area_name.setText(mMidLevelModelList.getLocation());

        area_tid_count = view.findViewById(R.id.area_tid_count);
        area_tid_count.setText("0 / " + mMidLevelModelList.getTerminalLevelModels().size() + " TIDs");
        btn_btm_slct_tid_confirm = view.findViewById(R.id.btn_btm_slct_tid_confirm);
        imgV_btm_sheet_cross.setOnClickListener(this);
        btn_btm_slct_tid_confirm.setOnClickListener(this);

        rv_slct_tid_cities = view.findViewById(R.id.rv_slct_tid_cities);

        checkbox_slct_tid_select_all = view.findViewById(R.id.checkbox_slct_tid_select_all);
        /*checkbox_slct_tid_select_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    buttonView.setTextColor(getActivity().getColor(R.color.blackText));
                    locationNameTIDAdapter.selectAll();
                } else {
                    buttonView.setTextColor(getActivity().getColor(R.color.grayText));
                    locationNameTIDAdapter.deSelectAll();
                }
            }
        });*/

        checkbox_slct_tid_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox_slct_tid_select_all.isChecked()) {
                    checkbox_slct_tid_select_all.setChecked(true);
                    checkbox_slct_tid_select_all.setTextColor(getActivity().getColor(R.color.blackText));
                    locationNameTIDAdapter.selectAll();
                } else {
                    checkbox_slct_tid_select_all.setChecked(false);
                    checkbox_slct_tid_select_all.setTextColor(getActivity().getColor(R.color.grayText));
                    locationNameTIDAdapter.deSelectAll();
                }
            }
        });

        initAdapter(mMidLevelModelList);
        for (int i = 0; i < mMidLevelModelList.getTerminalLevelModels().size(); i++) {
            if (mMidLevelModelList.getTerminalLevelModels().get(i).isSelected()) {
                strings.add(mMidLevelModelList.getTerminalLevelModels().get(i).getTid());
            }
        }
        if (strings.size() == mMidLevelModelList.getTerminalLevelModels().size()) {
            checkbox_slct_tid_select_all.setChecked(true);
        } else {
            checkbox_slct_tid_select_all.setChecked(false);
        }
        area_tid_count.setText(strings.size() + " / " + mMidLevelModelList.getTerminalLevelModels().size() + " TIDs");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imgV_btm_sheet_cross) {
            dismiss();
        } else if (id == R.id.btn_btm_slct_tid_confirm) {
            dismiss();

//            stringsFirst

            if (strings.size() != 0) {

                mMidLevelModelList.setSelected(true);
                mMidLevelModelList.setUserRole(userRole);
                for (int j = 0; j < mMidLevelModelList.getTerminalLevelModels().size(); j++) {
                    mMidLevelModelList.getTerminalLevelModels().get(j).setSelected(false);
                }
                for (int j = 0; j < mMidLevelModelList.getTerminalLevelModels().size(); j++) {
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).equalsIgnoreCase(mMidLevelModelList.getTerminalLevelModels().get(j).getTid())) {
                            mMidLevelModelList.getTerminalLevelModels().get(j).setSelected(true);
                            mMidLevelModelList.getTerminalLevelModels().get(j).setUserRole(userRole);
                        }
                    }
                }

                mOnUpdateGenRepoListener.updateListener("BTM_SelectTIDFragment", strings, true);
            } else {
                mMidLevelModelList.setSelected(false);
                mMidLevelModelList.setUserRole(5);
                for (int j = 0; j < mMidLevelModelList.getTerminalLevelModels().size(); j++) {
                    mMidLevelModelList.getTerminalLevelModels().get(j).setSelected(false);
                    mMidLevelModelList.getTerminalLevelModels().get(j).setUserRole(5);
                }
                mOnUpdateGenRepoListener.updateListener("BTM_SelectTIDFragment", locationNameTIDAdapter.getSelectedTIDs(), false);
            }
        }
    }

    private void initAdapter(MidLevelModel mMidLevelModelList) {

        strings.clear();
        locationNameTIDAdapter = new LocationTIDAdapter(mMidLevelModelList.getTerminalLevelModels(), new onSelectTIDListener() {
            @Override
            public void onTIDClicked(String tid) {
                if (strings.contains(tid)) {
                    strings.remove(tid);
                } else {
                    strings.add(tid);
                }
                if (strings.size() == mMidLevelModelList.getTerminalLevelModels().size()) {
                    checkbox_slct_tid_select_all.setChecked(true);
                } else {
                    checkbox_slct_tid_select_all.setChecked(false);
                }
                area_tid_count.setText(strings.size() + " / " + mMidLevelModelList.getTerminalLevelModels().size() + " TIDs");
            }
        }, userRole);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getActivity());
        rv_slct_tid_cities.setLayoutManager(mLayoutManager1);
        rv_slct_tid_cities.setAdapter(locationNameTIDAdapter);
    }


}