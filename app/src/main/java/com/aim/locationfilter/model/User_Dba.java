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
public class User_Dba {

    @SerializedName("dbaName")
    @Expose
    private String dbaName;
    @SerializedName("cities")
    @Expose
    private List<User_City> cities = null;

    public String getDbaName() {
        return dbaName;
    }

    public void setDbaName(String dbaName) {
        this.dbaName = dbaName;
    }

    public List<User_City> getCities() {
        return cities;
    }

    public void setCities(List<User_City> cities) {
        this.cities = cities;
    }
}
