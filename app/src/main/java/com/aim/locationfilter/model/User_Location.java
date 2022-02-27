/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.model;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
@Keep
public class User_Location {

    @SerializedName("merchantId")
    @Expose
    private String merchantId;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("tidList")
    @Expose
    private List<User_TidList> tidList = null;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<User_TidList> getTidList() {
        return tidList;
    }

    public void setTidList(List<User_TidList> tidList) {
        this.tidList = tidList;
    }
}
