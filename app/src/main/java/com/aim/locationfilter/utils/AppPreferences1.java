/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:56 PM
 *
 */

package com.aim.locationfilter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.Keep;

import com.aim.locationfilter.model.Filter_user;
import com.google.gson.Gson;

@Keep
public class AppPreferences1 {

    static String prefKey = "PREFERENCE";

    public void setPreferenceObject(Context context, Object modal, String key) {
        /**** storing object in preferences  ****/
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();

        Gson gson = new Gson();
        String jsonObject = gson.toJson(modal);
        prefsEditor.putString(key, jsonObject);
        prefsEditor.apply();

    }

    public Object getPreferenceObjectJson(Context c, String key) {

        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(
                c.getApplicationContext());
        /**** get user data    *****/
        String json = appSharedPrefs.getString(key, "");
        Gson gson = new Gson();
        Filter_user filter_user = gson.fromJson(json, Filter_user.class);
        return filter_user;
    }
}
