/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:59 PM
 *
 */

package com.aim.locationfilter.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.aim.locationfilter.R;
import com.aim.locationfilter.model.TerminalLevelModel;
import com.aim.locationfilter.model.onSelectTIDListener;


import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

@Keep
public class LocationTIDAdapter extends RecyclerView.Adapter<LocationTIDAdapter.LocationTIDViewHolder> {


    ArrayList<TerminalLevelModel> terminalLevelModels;
    private onSelectTIDListener listener;
    int userRole = 5;
    boolean istrue = false;

    public LocationTIDAdapter(ArrayList<TerminalLevelModel> terminalLevelModels, onSelectTIDListener listener, int userRole) {
        this.terminalLevelModels = terminalLevelModels;
        this.listener = listener;
        this.userRole = userRole;
        istrue = true;
    }

    public LocationTIDAdapter(ArrayList<TerminalLevelModel> terminalLevelModels, onSelectTIDListener listener) {
        this.terminalLevelModels = terminalLevelModels;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LocationTIDViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_slct_loct_cities, parent, false);

        return new LocationTIDViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationTIDViewHolder holder, int position) {
        holder.checkbox_rv_slct_loct_city.setChecked(terminalLevelModels.get(position).isSelected());
        holder.checkbox_rv_slct_loct_city.setText(terminalLevelModels.get(position).getTid());

        holder.checkbox_rv_slct_loct_city.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (istrue) {
                    listener.onTIDClicked(terminalLevelModels.get(position).getTid());
                } else {
                    listener.onTIDClicked(terminalLevelModels.get(position).getTid());
                    terminalLevelModels.get(position).setSelected(isChecked);
                }
            }
        });
    }

    public ArrayList<String> getSelectedTIDs() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < terminalLevelModels.size(); i++) {
            if (terminalLevelModels.get(i).isSelected()) {
                list.add(terminalLevelModels.get(i).getTid());
            }
        }
        return list;
    }

    public void selectAll() {
        for (int i = 0; i < terminalLevelModels.size(); i++) {
            terminalLevelModels.get(i).setSelected(true);
        }
        notifyDataSetChanged();
    }

    public void deSelectAll() {
        for (int i = 0; i < terminalLevelModels.size(); i++) {
            terminalLevelModels.get(i).setSelected(false);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return terminalLevelModels.size();
    }

    public class LocationTIDViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox_rv_slct_loct_city;
        TextView tv_rv_slct_loct_city_count;
        ImageView imgV_rv_slct_loct, imgV_rv_slct_loct2;

        RecyclerView rv_slct_loct_lcations;
        ExpandableLayout expandableLayout_rv_slct_location;

        public LocationTIDViewHolder(@NonNull View view) {
            super(view);
            imgV_rv_slct_loct = view.findViewById(R.id.imgV_rv_slct_loct);
            imgV_rv_slct_loct.setVisibility(View.GONE);
            imgV_rv_slct_loct2 = view.findViewById(R.id.imgV_rv_slct_loct2);
            imgV_rv_slct_loct2.setVisibility(View.GONE);
            checkbox_rv_slct_loct_city = view.findViewById(R.id.checkbox_rv_slct_loct_city);

            tv_rv_slct_loct_city_count = view.findViewById(R.id.tv_rv_slct_loct_city_count);
            tv_rv_slct_loct_city_count.setVisibility(View.GONE);

            rv_slct_loct_lcations = view.findViewById(R.id.rv_slct_loct_lcations);
            rv_slct_loct_lcations.setVisibility(View.GONE);
            expandableLayout_rv_slct_location = view.findViewById(R.id.expandableLayout_rv_slct_location);
            expandableLayout_rv_slct_location.setVisibility(View.GONE);
        }
    }
}
