/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.aim.locationfilter.R;
import com.aim.locationfilter.model.DbaLevelModel;
import com.aim.locationfilter.utils.Utils;


import java.util.ArrayList;
@Keep
public class UserListDBAAdapter extends RecyclerView.Adapter<UserListDBAAdapter.ViewHolder> {
    Context context;
    ArrayList<DbaLevelModel> dbaArrayList = new ArrayList<>();
    OnDbaClicked onDbaClicked;

    int checkedPosition = 0;

    public UserListDBAAdapter(Context c, ArrayList<DbaLevelModel> dbaArrayList, OnDbaClicked onDbaClicked) {
        this.context = c;
        this.dbaArrayList = dbaArrayList;
        this.onDbaClicked = onDbaClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_userlist_dba_location, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DbaLevelModel user_dba = dbaArrayList.get(position);

//        holder.checkbox_slct_loct_comp_name.setText(user_dba.getDbaName());
        holder.txt_dba_name.setText(user_dba.getDbaName());

        if (user_dba.isSelected()) {
            holder.checkbox_slct_loct_comp_name.setChecked(true);
        } else {
            holder.checkbox_slct_loct_comp_name.setChecked(false);
        }

        holder.txt_dba_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.avoidDoubleClicks(v);

                Bundle bundle = new Bundle();
//                ApiNetworkUtils.captureEvent("location-store-checkbox-clicked", "Location", "location", "store", "checkbox", "clicked", bundle, context);

//                holder.checkbox_slct_loct_comp_name.setChecked(true);
                checkedPosition = position;
                onDbaClicked.onDbaItemClicked(position);
                notifyDataSetChanged();

            }
        });

        holder.rl_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean selectParam = false;
//                if(holder.checkbox_slct_loct_comp_name.isChecked()) {
//                    selectParam = false;
//                }else {
//                    selectParam = true;
//                }
//                for (int j = 0; j < dbaArrayList.get(position).getCityLevelList().size(); j++) {
//                    dbaArrayList.get(position).getCityLevelList().get(j).setSelected(selectParam);
//                    for (int k = 0; k < dbaArrayList.get(position).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
//                        dbaArrayList.get(position).getCityLevelList().get(j).getMidLevelModels().get(k).setSelected(selectParam);
//                        for (int l = 0; l < dbaArrayList.get(position).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {
//                            dbaArrayList.get(position).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).setSelected(selectParam);
//                        }
//                    }
//                }
//                notifyDataSetChanged();
                checkedPosition = position;
                onDbaClicked.onDbaCheckClicked(position);
            }
        });

//        holder.checkbox_slct_loct_comp_name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                if(isChecked){
//                    holder.checkbox_slct_loct_comp_name.setChecked(false);
//                    // Code to display your message.
//                }
//            }
//        });
        if (checkedPosition == position) {
//            holder.checkbox_slct_loct_comp_name.setChecked(true);
//            user_dba.setSelected(true);
            holder.checkbox_slct_loct_comp_name.setTextColor(ContextCompat.getColor(context, R.color.textStorePrice));
            holder.view_slct_loct.setBackgroundColor(ContextCompat.getColor(context, R.color.textStorePrice));
//            onDbaClicked.onDbaItemClicked(position);
            // durationClickListener.durationItemClick(duration_genReport.getDuration());

        } else {
            holder.checkbox_slct_loct_comp_name.setTextColor(ContextCompat.getColor(context, R.color.burmudaGrey));
            holder.view_slct_loct.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return dbaArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkbox_slct_loct_comp_name;
        View view_slct_loct;
        RelativeLayout main_lyr, rl_checkbox;
        TextView txt_dba_name;

        public ViewHolder(View itemView) {
            super(itemView);
            checkbox_slct_loct_comp_name = itemView.findViewById(R.id.checkbox_slct_loct_comp_name);
            view_slct_loct = itemView.findViewById(R.id.view_slct_loct);
            main_lyr = itemView.findViewById(R.id.main_lyr);
            txt_dba_name = itemView.findViewById(R.id.txt_dba_name);
            rl_checkbox = itemView.findViewById(R.id.rl_checkbox);
        }
    }

    public interface OnDbaClicked {
        void onDbaItemClicked(int cityPostion);

        void onDbaCheckClicked(int cityPosition);
    }

}
