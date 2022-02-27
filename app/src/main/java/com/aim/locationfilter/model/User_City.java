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
public class User_City {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("location")
    @Expose
    private List<User_Location> location = null;


    private boolean isSelected;



    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<User_Location> getLocation() {
        return location;
    }

    public void setLocation(List<User_Location> location) {
        this.location = location;
    }

}
