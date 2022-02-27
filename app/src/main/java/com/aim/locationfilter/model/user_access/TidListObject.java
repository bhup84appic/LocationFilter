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
@Keep
public class TidListObject implements Serializable {
    private String tid;
    private String roleName;
    private String roleStatus;
    private String mid;
    private String city;
    private String dba;
    private String cid;

    public String getMid() {
        return mid == null ? "" : mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCity() {
        return city == null ? "" : city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDba() {
        return dba == null ? "" : dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public String getCid() {
        return cid == null ? "" : cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "TidListObject{" +
                "tid='" + tid + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleStatus='" + roleStatus + '\'' +
                ", mid='" + mid + '\'' +
                ", city='" + city + '\'' +
                ", dba='" + dba + '\'' +
                ", cid='" + cid + '\'' +
                '}';
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }
}
