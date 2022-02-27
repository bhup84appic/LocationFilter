/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:29 PM
 *
 */

package com.aim.locationfilter.model;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class CidLevelModel {
    String cid;
    String companyName;
    ArrayList<DbaLevelModel> dbaLevelModels = new ArrayList<>();
    private boolean isSelected;
    int role = 5;
    private String corpMerchantCount;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ArrayList<DbaLevelModel> getDbaLevelModels() {
        return dbaLevelModels;
    }

    public void setDbaLevelModels(ArrayList<DbaLevelModel> dbaLevelModels) {
        this.dbaLevelModels = dbaLevelModels;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCorpMerchantCount() {
        return corpMerchantCount;
    }

    public void setCorpMerchantCount(String corpMerchantCount) {
        this.corpMerchantCount = corpMerchantCount;
    }
}
