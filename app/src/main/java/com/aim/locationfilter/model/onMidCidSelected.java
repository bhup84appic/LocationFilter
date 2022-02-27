/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:30 PM
 *
 */

package com.aim.locationfilter.model;

import androidx.annotation.Keep;

import java.util.List;
@Keep
public interface onMidCidSelected {
    void onMidCidClicked(List<MidLevelModel> midLevelModelList, CidLevelModel cidLevelModel);
}
