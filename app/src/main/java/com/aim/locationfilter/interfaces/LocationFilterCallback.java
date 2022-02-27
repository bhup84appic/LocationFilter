/*
 *
 *  Created by Pooran Kharol on 30/1/21 6:02 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 5:10 PM
 *
 */

package com.aim.locationfilter.interfaces;

import androidx.annotation.Keep;

@Keep
public interface LocationFilterCallback {
    void onLocationClick(int requestCode);

    void onSessionExpired();
}
