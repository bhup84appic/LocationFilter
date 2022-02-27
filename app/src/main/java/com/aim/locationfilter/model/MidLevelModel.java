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
public class MidLevelModel implements Parcelable {

    String mid;
    String location;
    String address;
    String pinCode;
    ArrayList<TerminalLevelModel> terminalLevelModels = new ArrayList<>();
    private boolean isSelected;
    int role = 5;
    boolean userroleOwner = false;
    boolean userroleL1 = false;
    boolean userroleL2 = false;
    boolean userroleCashier = false;
    int userRole = 5;


    public static final Creator<MidLevelModel> CREATOR = new Creator<MidLevelModel>() {
        @Override
        public MidLevelModel createFromParcel(Parcel in) {
            return new MidLevelModel(in);
        }

        @Override
        public MidLevelModel[] newArray(int size) {
            return new MidLevelModel[size];
        }
    };

    public MidLevelModel() {
    }

    protected MidLevelModel(Parcel in) {
        mid = in.readString();
        location = in.readString();
        pinCode = in.readString();
        address = in.readString();
        isSelected = in.readByte() != 0;
        userroleL1 = in.readByte() != 0;
        userroleL2 = in.readByte() != 0;
        userroleCashier = in.readByte() != 0;
        userroleOwner = in.readByte() != 0;
        role = in.readInt();
        userRole = in.readInt();
        terminalLevelModels = in.createTypedArrayList(TerminalLevelModel.CREATOR);
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public ArrayList<TerminalLevelModel> getTerminalLevelModels() {
        return terminalLevelModels == null ? new ArrayList<>() : terminalLevelModels;
    }

    public void setTerminalLevelModels(ArrayList<TerminalLevelModel> terminalLevelModels) {
        this.terminalLevelModels = terminalLevelModels;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mid);
        dest.writeString(location);
        dest.writeString(pinCode);
        dest.writeString(address);
        dest.writeByte((byte) (isSelected ? 1 : 0));
        dest.writeByte((byte) (userroleL1 ? 1 : 0));
        dest.writeByte((byte) (userroleL2 ? 1 : 0));
        dest.writeByte((byte) (userroleCashier ? 1 : 0));
        dest.writeByte((byte) (userroleOwner ? 1 : 0));
        dest.writeInt(role);
        dest.writeInt(userRole);
        dest.writeTypedList(terminalLevelModels);
    }

    public boolean isUserroleL1() {
        return userroleL1;
    }

    public void setUserroleL1(boolean userroleL1) {
        this.userroleL1 = userroleL1;
    }

    public boolean isUserroleL2() {
        return userroleL2;
    }

    public void setUserroleL2(boolean userroleL2) {
        this.userroleL2 = userroleL2;
    }

    public boolean isUserroleCashier() {
        return userroleCashier;
    }

    public void setUserroleCashier(boolean userroleCashier) {
        this.userroleCashier = userroleCashier;
    }

    public boolean isUserroleOwner() {
        return userroleOwner;
    }

    public void setUserroleOwner(boolean userroleOwner) {
        this.userroleOwner = userroleOwner;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}
