/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.interfaces;

import androidx.annotation.Keep;

import java.util.ArrayList;
@Keep
public interface OnUpdateGenRepoListener {

    void updateListener(String btm_response, ArrayList<String> list);

    void updateListener(String btm_response,ArrayList<String> list, boolean b);

}
