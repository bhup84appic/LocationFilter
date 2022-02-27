/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:56 PM
 *
 */

package com.aim.locationfilter.interfaces;

import androidx.annotation.Keep;

import com.aim.locationfilter.model.MidLevelModel;
@Keep
public interface sheetListener {
    void openSheet(int pos, MidLevelModel midLevelModel);
}
