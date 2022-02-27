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
public class UserList_Data {

    @SerializedName("mintoakMid")
    @Expose
    private String mintoakMid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("emailID")
    @Expose
    private String emailID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("isViewEnabled")
    @Expose
    private Boolean isViewEnabled;
    @SerializedName("isReportsEnabled")
    @Expose
    private Boolean isReportsEnabled;
    @SerializedName("reportList")
    @Expose
    private List<String> reportList = null;
    @SerializedName("hierarchy")
    @Expose
    private List<User_Hierarchy> hierarchy = null;

    public String getMintoakMid() {
        return mintoakMid;
    }

    public void setMintoakMid(String mintoakMid) {
        this.mintoakMid = mintoakMid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIsViewEnabled() {
        return isViewEnabled;
    }

    public void setIsViewEnabled(Boolean isViewEnabled) {
        this.isViewEnabled = isViewEnabled;
    }

    public Boolean getIsReportsEnabled() {
        return isReportsEnabled;
    }

    public void setIsReportsEnabled(Boolean isReportsEnabled) {
        this.isReportsEnabled = isReportsEnabled;
    }

    public List<String> getReportList() {
        return reportList;
    }

    public void setReportList(List<String> reportList) {
        this.reportList = reportList;
    }

    public List<User_Hierarchy> getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(List<User_Hierarchy> hierarchy) {
        this.hierarchy = hierarchy;
    }

}
