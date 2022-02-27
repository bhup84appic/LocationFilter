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
public class User_Hierarchy {

    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("dba")
    @Expose
    private List<User_Dba> dba = null;
    @SerializedName("corp_Name")
    @Expose
    private String corpName;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<User_Dba> getDba() {
        return dba;
    }

    public void setDba(List<User_Dba> dba) {
        this.dba = dba;
    }

    public String getCorpName() {
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }
}
