/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:30 PM
 *
 */

package com.aim.locationfilter.interfaces;



import androidx.annotation.Keep;

import com.aim.locationfilter.model.CidLevelModel;
import com.aim.locationfilter.model.CityLevelModel;
import com.aim.locationfilter.model.DbaLevelModel;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.model.TerminalLevelModel;

import java.util.List;

@Keep
public interface LocationFilterApplyCallBack {

    void allSelectedData(CidLevelModel cidLevelModel, List<DbaLevelModel> dbaLevelModelList, List<CityLevelModel> cityLevelModelList, List<MidLevelModel> midLevelModelList, List<TerminalLevelModel> terminalLevelModelList);

    void onCidSelected(CidLevelModel cidLevelModel);

    void onDBASelected(List<DbaLevelModel> dbaLevelModelList);

    void onCitySelected(List<CityLevelModel> cityLevelModelList);

    void onMidSelected(List<MidLevelModel> midLevelModelList);

    void onTidSelected(List<TerminalLevelModel> terminalLevelModelList);

}
