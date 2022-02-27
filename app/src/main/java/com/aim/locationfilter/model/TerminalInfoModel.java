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
public class TerminalInfoModel {

    public String tid;
    public String city;
    public String location;
    public String address;
    public String mid;
    public String cid;
    public String DBA;
    public ArrayList<String> segments;
    public String roleStatus;


    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String pinCode;

    public BankProduct getBankProduct() {
        return bankProduct;
    }

    public void setBankProduct(BankProduct bankProduct) {
        this.bankProduct = bankProduct;
    }

    public BankProduct bankProduct;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String accountNumber;

    public String getTidStatus() {
        return tidStatus;
    }

    public void setTidStatus(String tidStatus) {
        this.tidStatus = tidStatus;
    }

    public String tidStatus;
    public String companyName;
    public String role;
    public int userRole;

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDBA() {
        return DBA;
    }

    public void setDBA(String DBA) {
        this.DBA = DBA;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<String> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<String> segments) {
        this.segments = segments;
    }


    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public String toString() {
        return "TerminalInfoModel{" +
                "tid='" + tid + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' + ", pinCode='" + pinCode + '\'' +
                ", mid='" + mid + '\'' +
                ", cid='" + cid + '\'' +
                ", DBA='" + DBA + '\'' +
                ", bankProduct=" + bankProduct +
                ", accountNumber='" + accountNumber + '\'' +
                ", tidStatus='" + tidStatus + '\'' +
                ", companyName='" + companyName + '\'' +
                ", role='" + role + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
