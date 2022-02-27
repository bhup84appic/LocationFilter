/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.locationfilter.R;
import com.aim.locationfilter.interfaces.OnUpdateGenRepoListener;
import com.aim.locationfilter.interfaces.sheetListener;
import com.aim.locationfilter.model.MidLevelModel;


import java.util.ArrayList;
import java.util.List;
@Keep
public class SelectLocationsAdapter extends RecyclerView.Adapter<SelectLocationsAdapter.MyViewHolder> implements Filterable, OnUpdateGenRepoListener {

    onCheckedListener onCheckedListener;
    // public ArrayList<User_Location> arrayListUser = new ArrayList<>();
    public ArrayList<String> stringArrayList = new ArrayList<>();
    Activity activity;
    private boolean isSelectedAll;


    // List<String> selectedLocationsList  = new ArrayList<String>();
    // private List<User_Location> selectLocation_locationsList;
    private List<MidLevelModel> location;
    private List<MidLevelModel> locationListFull;
    private List<MidLevelModel> locations_checkedlist = new ArrayList<>();
    private int mPos;
    private boolean isCashier;
    private sheetListener sheetList;
    ClickedOnMid clickedOnMid;

    public SelectLocationsAdapter() {
    }

    public SelectLocationsAdapter(List<MidLevelModel> location, Activity activity, int mPos, onCheckedListener onCheckedListener, boolean isCashier, sheetListener sheetList, ClickedOnMid clickedOnMid) {
        this.location = location;
        this.activity = activity;
        this.mPos = mPos;
        this.onCheckedListener = onCheckedListener;

        locationListFull = new ArrayList<>(location);
        this.isCashier = isCashier;
        this.sheetList = sheetList;
        this.clickedOnMid = clickedOnMid;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_slct_loct_lcations, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        MidLevelModel user_location = location.get(position);
        //holder.checkbox_rv_slct_loct_location.setText(location.get(position).getLocation());
        //holder.textArea.setText(location.get(position).getLocation());
        holder.textArea.setText(location.get(position).getPinCode());
        holder.textAreaAddress.setText(location.get(position).getAddress());


        holder.checkbox_rv_slct_loct_location.setChecked(location.get(position).isSelected());

        holder.checkbox_rv_slct_loct_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkbox_rv_slct_loct_location.isChecked()) {
//                    onCheckedListener.onChecked(mPos, position, true);
                    user_location.setSelected(true);
                } else {
//                    onCheckedListener.onChecked(mPos, position, false);
                    user_location.setSelected(false);
                }
                clickedOnMid.onClickedMid(user_location);
                notifyDataSetChanged();
            }
        });

        holder.llArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show();
                if (holder.checkbox_rv_slct_loct_location.isChecked()) {
//                    onCheckedListener.onChecked(mPos, position, true);
                    user_location.setSelected(false);
                } else {
//                    onCheckedListener.onChecked(mPos, position, false);
                    user_location.setSelected(true);
                }
                clickedOnMid.onClickedMid(user_location);
                notifyDataSetChanged();
            }
        });

        holder.node_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.get(position).setSelected(!location.get(position).isSelected());
                clickedOnMid.onClickedMid(user_location);
            }
        });
        if (isCashier) {
            holder.selectTIDsTv.setVisibility(View.VISIBLE);
        } else {
            holder.selectTIDsTv.setVisibility(View.GONE);
        }
        holder.selectTIDsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetList.openSheet(position, location.get(position));
            }
        });

        if (user_location.isSelected()) {
            highLightSelection(holder.textArea, holder.textAreaAddress, true);
        } else {
            highLightSelection(holder.textArea, holder.textAreaAddress, false);
        }
    }

    @Override
    public int getItemCount() {
        return location.size();
    }

    @Override
    public void updateListener(String btm_response, ArrayList<String> list) {

    }

    @Override
    public void updateListener(String btm_response, ArrayList<String> selSctedlist, boolean b) {

    }

    public interface onCheckedListener {
        void onChecked(int parentPos, int childPosition, boolean isChecked);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkbox_rv_slct_loct_location;
        RelativeLayout node_container;
        TextView selectTIDsTv;
        TextView textArea;
        TextView textAreaAddress;
        LinearLayoutCompat llArea;


        public MyViewHolder(View view) {
            super(view);

            checkbox_rv_slct_loct_location = view.findViewById(R.id.checkbox_rv_slct_loct_location);
            node_container = view.findViewById(R.id.node_container);
            selectTIDsTv = view.findViewById(R.id.selectTIDsTv);
            textArea = view.findViewById(R.id.textArea);
            textAreaAddress = view.findViewById(R.id.textAreaAddress);
            llArea = view.findViewById(R.id.llArea);


        }
    }

    public void highLightSelection(TextView textArea, TextView textAreaAddress, boolean b) {
        textArea.setTextColor(b ? ContextCompat.getColor(activity, R.color.black_russian90) : ContextCompat.getColor(activity, R.color.grey90));
        textAreaAddress.setTextColor(b ? ContextCompat.getColor(activity, R.color.charcoal90) : ContextCompat.getColor(activity, R.color.grey90));
    }

    // filter :
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MidLevelModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(locationListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MidLevelModel item : locationListFull) {
                    if (item.getLocation().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            location.clear();
            location.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public interface ClickedOnMid {
        void onClickedMid(MidLevelModel midLevelModel);
    }

}
