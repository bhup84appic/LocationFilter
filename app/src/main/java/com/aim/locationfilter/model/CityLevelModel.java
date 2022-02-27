/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:29 PM
 *
 */

package com.aim.locationfilter.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public class CityLevelModel implements Parcelable {

    String city;
    ArrayList<MidLevelModel> midLevelModels = new ArrayList<>();
    private boolean isSelected;
    int role = 5;
    boolean userroleOwner = false;
    boolean userroleL1 = false;
    boolean userroleL2 = false;
    boolean userrolecashier = false;

    public CityLevelModel() {
    }

    protected CityLevelModel(Parcel in) {
        city = in.readString();
        midLevelModels = in.createTypedArrayList(MidLevelModel.CREATOR);
        isSelected = in.readByte() != 0;
        role = in.readInt();
        userroleOwner = in.readByte() != 0;
        userroleL1 = in.readByte() != 0;
        userroleL2 = in.readByte() != 0;
        userrolecashier = in.readByte() != 0;
    }

    public static final Creator<CityLevelModel> CREATOR = new Creator<CityLevelModel>() {
        @Override
        public CityLevelModel createFromParcel(Parcel in) {
            return new CityLevelModel(in);
        }

        @Override
        public CityLevelModel[] newArray(int size) {
            return new CityLevelModel[size];
        }
    };

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<MidLevelModel> getMidLevelModels() {
        return midLevelModels;
    }

    public void setMidLevelModels(ArrayList<MidLevelModel> midLevelModels) {
        this.midLevelModels = midLevelModels;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public boolean isUserrolecashier() {
        return userrolecashier;
    }

    public void setUserrolecashier(boolean userrolecashier) {
        this.userrolecashier = userrolecashier;
    }

    public boolean isUserroleOwner() {
        return userroleOwner;
    }

    public void setUserroleOwner(boolean userroleOwner) {
        this.userroleOwner = userroleOwner;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(city);
        parcel.writeTypedList(midLevelModels);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeInt(role);
        parcel.writeByte((byte) (userroleOwner ? 1 : 0));
        parcel.writeByte((byte) (userroleL1 ? 1 : 0));
        parcel.writeByte((byte) (userroleL2 ? 1 : 0));
        parcel.writeByte((byte) (userrolecashier ? 1 : 0));
    }
}
