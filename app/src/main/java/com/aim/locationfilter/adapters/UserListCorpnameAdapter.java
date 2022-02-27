/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.aim.locationfilter.R;
import com.aim.locationfilter.model.Filter_user_Data;

import java.util.ArrayList;
import java.util.List;
@Keep
public class UserListCorpnameAdapter extends RecyclerView.Adapter<UserListCorpnameAdapter.ViewHolder> {
    Context context;
    List<Filter_user_Data> filter_user_dataList = new ArrayList<>();

    int checkedPosition = -1;

    public UserListCorpnameAdapter(Context c, List<Filter_user_Data> filter_user_dataList) {
        this.context = c;
        this.filter_user_dataList = filter_user_dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_userlist_cids, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Filter_user_Data user_dba = filter_user_dataList.get(position);

        holder.txt_slct_loct_corp_name.setText(user_dba.getCorpName());

        holder.linear_slct_loct_cid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedPosition = position;
                notifyDataSetChanged();
            }
        });
        if (checkedPosition == position) {

            holder.txt_slct_loct_corp_name.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            holder.view_slct_loct_corp_name.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            // durationClickListener.durationItemClick(duration_genReport.getDuration());

        } else {
            holder.txt_slct_loct_corp_name.setTextColor(ContextCompat.getColor(context, R.color.link_water));
            holder.view_slct_loct_corp_name.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        }

    }

    @Override
    public int getItemCount() {
        return  filter_user_dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_slct_loct_corp_name;
        View view_slct_loct_corp_name;
        LinearLayout linear_slct_loct_cid;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_slct_loct_corp_name =  itemView.findViewById(R.id.txt_slct_loct_corp_name);
            view_slct_loct_corp_name =  itemView.findViewById(R.id.view_slct_loct_corp_name);
            linear_slct_loct_cid =  itemView.findViewById(R.id.linear_slct_loct_cid);
        }
    }

}

