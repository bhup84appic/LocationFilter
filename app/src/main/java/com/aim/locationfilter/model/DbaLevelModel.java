/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:30 PM
 *
 */

package com.aim.locationfilter.model;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class DbaLevelModel {

    String dbaName;
    ArrayList<CityLevelModel> cityLevelList = new ArrayList<>();
    private boolean isSelected;
    int role = 5;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDbaName() {
        return dbaName;
    }

    public void setDbaName(String dbaName) {
        this.dbaName = dbaName;
    }

    public ArrayList<CityLevelModel> getCityLevelList() {
        return cityLevelList;
    }

    public void setCityLevelList(ArrayList<CityLevelModel> cityLevelList) {
        this.cityLevelList = cityLevelList;
    }
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
