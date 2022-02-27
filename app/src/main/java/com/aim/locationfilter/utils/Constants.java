/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:56 PM
 *
 */

package com.aim.locationfilter.utils;



import androidx.annotation.Keep;

import com.aim.locationfilter.model.CompanyInfoModel;
import com.aim.locationfilter.model.CorporateIdModel;
import com.aim.locationfilter.model.Filter_user;
import com.aim.locationfilter.model.Pojo_dba_filter;
import com.aim.locationfilter.model.TerminalInfoModel;

import java.util.ArrayList;
@Keep
public class Constants {
    // paresh :
    public static final String FILTER_DATA_JSON = "FILTER_DATA_JSON";
    public static ArrayList<TerminalInfoModel> TERMINAL_INFO_GET = new ArrayList<>();
    public static ArrayList<CompanyInfoModel> COMPANY_INFO_GET = new ArrayList<>();
    public static Filter_user dbaDetailsPojoMain = null;
    public static CorporateIdModel corporateIdMain = null;
    public static ArrayList<Pojo_dba_filter> selectedLocationData = new ArrayList<>();
    public static ArrayList<Pojo_dba_filter> selectedLocationDataFinal = new ArrayList<>();

    public static ArrayList<Pojo_dba_filter> selectedCitiesData = new ArrayList<>();
    public static ArrayList<Pojo_dba_filter> selectedCitiesDataFinal = new ArrayList<>();

    // if user select all :
    public static boolean SELECT_ALL_LOCATIONS = false;

    public static int USER_ACCESS_ROLE_MANAGER1 = 0;
    public static int USER_ACCESS_ROLE_MANAGER2 = 1;
    public static int USER_ACCESS_ROLE_OWNER = 2;
    public static int USER_ACCESS_ROLE_CASHIER = 3;

    public static String ROLE_MANAGER1 = "manager1";
    public static String ROLE_MANAGER2 = "manager2";
    public static String ROLE_OWNER = "owner";
    public static String ROLE_CASHIER = "cashier";

}
