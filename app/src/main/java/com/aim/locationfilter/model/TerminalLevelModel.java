/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:30 PM
 *
 */

package com.aim.locationfilter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class TerminalLevelModel implements Parcelable {

    String address;
    String pinCode;
    String role;
    String tid;
    String roleStatus;
    boolean isSelected;
    int userRole;
    ArrayList<String> segments;
    String tidStatus;



    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }


    public static final Creator<TerminalLevelModel> CREATOR = new Creator<TerminalLevelModel>() {
        @Override
        public TerminalLevelModel createFromParcel(Parcel in) {
            return new TerminalLevelModel(in);
        }

        @Override
        public TerminalLevelModel[] newArray(int size) {
            return new TerminalLevelModel[size];
        }
    };

    public TerminalLevelModel() {

    }

    public TerminalLevelModel(String address, String pinCode, String role, String tid, String roleStatus, boolean isSelected, int userRole) {
        this.address = address;
        this.pinCode = pinCode;
        this.role = role;
        this.tid = tid;
        this.roleStatus = roleStatus;
        this.isSelected = isSelected;
        this.userRole = userRole;
    }

    protected TerminalLevelModel(Parcel in) {
        address = in.readString();
        pinCode = in.readString();
        roleStatus = in.readString();
        role = in.readString();
        tid = in.readString();
        isSelected = in.readByte() != 0;
        userRole = in.readInt();
        segments = (ArrayList<String>) in.readSerializable();
        tidStatus =  in.readString();

    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTid() {
        return tid == null ? "" : tid.trim();
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(pinCode);
        dest.writeString(role);
        dest.writeString(roleStatus);
        dest.writeString(tid);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeInt(userRole);
        dest.writeSerializable(segments);
        dest.writeString(tidStatus);
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public ArrayList<String> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<String> segments) {
        this.segments = segments;
    }

    public String getTidStatus() {
        return tidStatus == null ? "" : tidStatus;
    }

    public void setTidStatus(String tidStatus) {
        this.tidStatus = tidStatus;
    }
}
