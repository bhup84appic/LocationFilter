/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:42 PM
 *
 */

package com.aim.locationfilter.model.user_access;

import androidx.annotation.Keep;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Keep
public class UserRoleObject implements Serializable {
    private String mobile;
    private String userName;
    private String userEmail;
    private List<TidListObject> tidList = new ArrayList<>();

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail == null ? "" : userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<TidListObject> getTidList() {
        return tidList == null ? new ArrayList<TidListObject>() : tidList;
    }

    public void setTidList(List<TidListObject> tidList) {
        this.tidList = tidList;
    }

    @Override
    public String toString() {
        return "UserRoleObject{" +
                "mobile='" + mobile + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", tidList=" + tidList +
                '}';
    }
}
