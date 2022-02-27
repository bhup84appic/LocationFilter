/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:55 PM
 *
 */

package com.aim.locationfilter.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.locationfilter.R;
import com.aim.locationfilter.interfaces.sheetListener;
import com.aim.locationfilter.model.CityLevelModel;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.utils.Utils;



import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Keep
public class SelectLocationExpandableAdapter extends RecyclerView.Adapter<SelectLocationExpandableAdapter.MyViewHolder> implements SelectLocationsAdapter.onCheckedListener, Filterable {

    private ArrayList<CityLevelModel> cities;
    private ArrayList<CityLevelModel> citiesListFull;
    private ArrayList<CityLevelModel> cities_checkedlist = new ArrayList<>();
    private ArrayList<MidLevelModel> locations_checkedlist = new ArrayList<>();

    Activity activity;
    SelectLocationsAdapter selectLocationsAdapter;
    private boolean isSelectedAll;
    boolean isCashier;
    private sheetListener sheetList;
    private ClickedOnLocation clickedOnLocation;


    public SelectLocationExpandableAdapter(ArrayList<CityLevelModel> cities, Activity activity, boolean isCashier, sheetListener sheetList, ClickedOnLocation clickedOnLocation) {
        this.cities = cities;
        this.activity = activity;
        this.isCashier = isCashier;
        citiesListFull = new ArrayList<>(cities);
        this.sheetList = sheetList;
        this.clickedOnLocation = clickedOnLocation;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_slct_loct_cities, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CityLevelModel user_city = cities.get(position);

        holder.expandableLayout_rv_slct_location.setExpanded(true);
        holder.expandableLayout_rv_slct_location.expand();

        holder.checkbox_rv_slct_loct_city.setText(cities.get(position).getCity());
        holder.tv_rv_slct_loct_city_count.setText(String.valueOf(user_city.getMidLevelModels().size()));
        // select All
        holder.checkbox_rv_slct_loct_city.setChecked(cities.get(position).isSelected());
        // End select All
        holder.checkbox_rv_slct_loct_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.avoidDoubleClicks(v);

                Bundle bundle = new Bundle();
//                ApiNetworkUtils.captureEvent("location-city-checkbox-clicked", "Location", "location", "city", "checkbox", "clicked", bundle, activity);


                if (holder.checkbox_rv_slct_loct_city.isChecked()) {
                    cities_checkedlist.add(user_city);
                    cities.get(position).setSelected(true);
                    selectAllChild(cities.get(position));
                } else {
                    cities_checkedlist.remove(user_city);
                    cities.get(position).setSelected(false);
                    DeselectAllChild(cities.get(position));
                }
                clickedOnLocation.onLocationClicked(user_city);
            }
        });

        holder.imgV_rv_slct_loct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.expandableLayout_rv_slct_location.toggle();

                if (holder.expandableLayout_rv_slct_location.isExpanded()) {
                    Bundle bundle = new Bundle();
//                    ApiNetworkUtils.captureEvent("location-expand-icon-clicked", "Location", "location", "expand", "icon",
//                            "clicked", bundle, activity);
                } else {
                    Bundle bundle = new Bundle();
//                    ApiNetworkUtils.captureEvent("location-collapse-icon-clicked", "Location", "location", "collapse", "icon",
//                            "clicked", bundle, activity);
                }
            }
        });

        // hardCoded :

        selectLocationsAdapter = new SelectLocationsAdapter(cities.get(position).getMidLevelModels(), activity, position, SelectLocationExpandableAdapter.this, isCashier, new sheetListener() {
            @Override
            public void openSheet(int pos, MidLevelModel midLevelModel) {
                sheetList.openSheet(pos, midLevelModel);
            }
        }, (SelectLocationsAdapter.ClickedOnMid) midLevelModel -> {

            Bundle bundle = new Bundle();
//                ApiNetworkUtils.captureEvent("location-location-checkbox-clicked", "Location", "location", "location", "checkbox", "clicked", bundle, activity);


            clickedOnLocation.onLocationClicked(user_city);
        });
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(activity);
        holder.rv_slct_loct_lcations.setLayoutManager(mLayoutManager1);
        holder.rv_slct_loct_lcations.setAdapter(selectLocationsAdapter);

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    @Override
    public void onChecked(int parentPos, int childPosition, boolean isChecked) {

//        cities.get(parentPos).getMidLevelModels().get(childPosition).setSelected(isChecked);
//        clickedOnLocation.onLocationClicked(cities.get(parentPos));
//        for (int i = 0; i < cities.size(); i++) {
//            if (i == parentPos) {
//                for (int j = 0; j < cities.get(i).getMidLevelModels().size(); j++) {
//                    if (j == childPosition) {
//                        cities.get(i).getMidLevelModels().get(j).setSelected(isChecked);
//                    }
//                }
//            }
//        }
//        for (int i = 0; i < cities.size(); i++) {
//            for (int j = 0; j < cities.get(i).getMidLevelModels().size(); j++) {
//                LoggerUtils.E("value", i + " " + j + " " + cities.get(i).getMidLevelModels().get(j).isSelected());
//                locations_checkedlist = cities.get(i).getMidLevelModels();
//            }
//        }

        //  notifyDataSetChanged();
    }

    public void selectAllChild(CityLevelModel selectLocation_cities) {
        for (int i = 0; i < selectLocation_cities.getMidLevelModels().size(); i++) {
            selectLocation_cities.getMidLevelModels().get(i).setSelected(true);
        }
        notifyDataSetChanged();

    }

    public void DeselectAllChild(CityLevelModel selectLocation_cities) {
        for (int i = 0; i < selectLocation_cities.getMidLevelModels().size(); i++) {
            selectLocation_cities.getMidLevelModels().get(i).setSelected(false);
        }
        notifyDataSetChanged();

    }

    public void unselectall() {
        isSelectedAll = false;
        notifyDataSetChanged();
        //selectLocation_CitiesList.clear();
        //selectLocation_LocationList.clear();
    }

    public void selectAll() {
        isSelectedAll = true;
        notifyDataSetChanged();
    }

    // filter :
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CityLevelModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(citiesListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CityLevelModel item : citiesListFull) {
                    if (item.getCity().toLowerCase().contains(filterPattern)) {
                        // selectLocationsAdapter.getFilter().filter(filterPattern);
                        filteredList.add(item);
                    } else {
                        ArrayList<MidLevelModel> midListFull = new ArrayList<>(item.getMidLevelModels());
                        boolean isMidAvailable = false;
                        for (MidLevelModel midCheck : midListFull) {
                            if (midCheck.getLocation().toLowerCase().contains(filterPattern)) {
                                isMidAvailable = true;
                                break;
                            }
                        }
                        if (isMidAvailable) {
                            CityLevelModel addedItem = new CityLevelModel();
                            addedItem.setCity(item.getCity());
                            addedItem.setRole(item.getRole());
                            addedItem.setSelected(item.isSelected());
                            addedItem.setUserrolecashier(item.isUserrolecashier());
                            addedItem.setUserroleL1(item.isUserroleL1());
                            addedItem.setUserroleL2(item.isUserroleL2());
                            addedItem.setUserroleOwner(item.isUserroleOwner());
                            ArrayList<MidLevelModel> midLevelModel = new ArrayList<>();
                            Iterator<MidLevelModel> i = midListFull.iterator();
                            while (i.hasNext()) {
                                MidLevelModel s = i.next();
                                if (s.getLocation().toLowerCase().contains(filterPattern)) {
                                    midLevelModel.add(s);
                                }
                            }
                            addedItem.setMidLevelModels(midLevelModel);
                            filteredList.add(addedItem);

                        }
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cities.clear();
            cities.addAll((List) results.values);
            clickedOnLocation.onLocationClicked(null);
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox_rv_slct_loct_city;
        TextView tv_rv_slct_loct_city_count;
        ImageView imgV_rv_slct_loct, imgV_rv_slct_loct2;

        RecyclerView rv_slct_loct_lcations;
        ExpandableLayout expandableLayout_rv_slct_location;


        public MyViewHolder(View view) {
            super(view);

            imgV_rv_slct_loct = view.findViewById(R.id.imgV_rv_slct_loct);
            imgV_rv_slct_loct2 = view.findViewById(R.id.imgV_rv_slct_loct2);

            checkbox_rv_slct_loct_city = view.findViewById(R.id.checkbox_rv_slct_loct_city);

            tv_rv_slct_loct_city_count = view.findViewById(R.id.tv_rv_slct_loct_city_count);

            rv_slct_loct_lcations = view.findViewById(R.id.rv_slct_loct_lcations);

            expandableLayout_rv_slct_location = view.findViewById(R.id.expandableLayout_rv_slct_location);

        }

    }

    public interface ClickedOnLocation {
        void onLocationClicked(CityLevelModel cityLevelModel);
    }

}