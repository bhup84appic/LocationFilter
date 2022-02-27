/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 1:55 PM
 *
 */

package com.aim.locationfilter.utils;

import androidx.annotation.Keep;

import com.aim.locationfilter.interfaces.LocationFilterApplyCallBack;
import com.aim.locationfilter.model.CidLevelModel;
import com.aim.locationfilter.model.CityLevelModel;
import com.aim.locationfilter.model.CompanyInfoModel;
import com.aim.locationfilter.model.DbaLevelModel;
import com.aim.locationfilter.model.MidLevelModel;
import com.aim.locationfilter.model.TerminalInfoModel;
import com.aim.locationfilter.model.TerminalLevelModel;
import com.aim.locationfilter.model.user_access.UserRoleObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Keep
public class LocationFilterUtils {

    public static ArrayList<CidLevelModel> globalCidLevel = new ArrayList<>();
    public static ArrayList<CidLevelModel> FilterCidLevel = new ArrayList<>();
    public static ArrayList<CidLevelModel> storeCidLevel = new ArrayList<>();
    public static ArrayList<CidLevelModel> userAccessCidLevel = new ArrayList<>();
    public static ArrayList<CidLevelModel> selectedCidLevel = new ArrayList<>();
    public static List<UserRoleObject> userRolesData = new ArrayList<>();
    public static String FILTER_JSON;
    public static String FILTER_JSON_COPY;

    public static ArrayList<MidLevelModel> getTermList() {
        ArrayList<MidLevelModel> midLevelModels = new ArrayList<>();
        for (int i = 0; i < getTerminalInfoList().size(); i++) {
            for (int j = 0; j < getTerminalInfoList().get(i).getDbaLevelModels().size(); j++) {
                for (int k = 0; k < getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().size(); k++) {
                    ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                    for (int l = 0; l < getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().size(); l++) {
                        //MidLevelModel model =  getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l);
                        ArrayList<TerminalLevelModel> terminalModels = new ArrayList<>();

                        MidLevelModel temp = getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l);
                        for (int m = 0; m < getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l).getTerminalLevelModels().size(); m++) {
                            TerminalLevelModel terminalLevelModel = getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels().get(l).getTerminalLevelModels().get(m);
                            if (terminalLevelModel.getTidStatus().equalsIgnoreCase("Active")) {
                                terminalModels.add(terminalLevelModel);
                                //midLevelModels.addAll(getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels());
                            }
                        }
                        temp.setTerminalLevelModels(terminalModels);
                        if (temp != null && temp.getTerminalLevelModels() != null && temp.getTerminalLevelModels().size() > 0) {
                            midLevelModelArrayList.add(temp);
                        }
                    }
                    midLevelModels.addAll(midLevelModelArrayList);
                }
            }
        }

        if (midLevelModels != null && midLevelModels.size() > 0) {
            //LoggerUtils.E("getTermList()", " size after:" + midLevelModels.size());
        }
        return midLevelModels;
    }

    public static ArrayList<MidLevelModel> getsegment() {
        ArrayList<MidLevelModel> midLevelModels = new ArrayList<>();
        for (int i = 0; i < getTerminalInfoList().size(); i++) {
            for (int j = 0; j < getTerminalInfoList().get(i).getDbaLevelModels().size(); j++) {
                for (int k = 0; k < getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().size(); k++) {
                    midLevelModels.addAll(getTerminalInfoList().get(i).getDbaLevelModels().get(j).getCityLevelList().get(k).getMidLevelModels());
                }
            }
        }
        return midLevelModels;
    }

    public static ArrayList<CidLevelModel> getTerminalInfoList() {
        ArrayList<TerminalInfoModel> demoList = new ArrayList<>();

//        String Str = "{\n" +
//                "  \"status\": \"Success\",\n" +
//                "  \"respMessage\": \"Success\",\n" +
//                "  \"statusCode\": \"S101\",\n" +
//                "  \"terminalInfo\": [\n" +
//                "    {\n" +
//                "      \"112238\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Jayanagar\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53512\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112237\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Hosur Road\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53511\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112236\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98201915\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112235\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112234\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"9820181246\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112233\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98777224\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00002\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00003\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00001\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00004\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202014\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali West\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400092\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202012\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri West\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400048\",\n" +
//                "        \"mid\": \"1289\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202013\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400041\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202011\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"6789\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"73000020\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98177223\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";


        String Str = FILTER_JSON;
        try {

            JSONObject mainJsonObj = new JSONObject(Str);
            //LoggerUtils.E("status: ", mainJsonObj.getString("status"));
            //LoggerUtils.E("respMessage: ", mainJsonObj.getString("respMessage"));
            //LoggerUtils.E("statusCode: ", mainJsonObj.getString("statusCode"));
            JSONArray terminalArr = mainJsonObj.getJSONArray("terminalInfo");

            JSONObject terminalObj = terminalArr.getJSONObject(0);

            Iterator<String> iter = terminalObj.keys();
            while (iter.hasNext()) {
                String tid = iter.next();
                try {
                    JSONObject value = (JSONObject) terminalObj.get(tid);
//                    //LoggerUtils.E("Terminal Number : ", tid);
//                    //LoggerUtils.E("city: ", value.getString("city"));
//                    //LoggerUtils.E("location: ", value.getString("location"));
//                    //LoggerUtils.E("address: ", value.getString("address"));
//                    //LoggerUtils.E("mid: ", value.getString("mid"));
//                    //LoggerUtils.E("cid: ", value.getString("cid"));
//                    //LoggerUtils.E("DBA: ", value.getString("DBA"));
//                    //LoggerUtils.E("companyName: ", value.getString("companyName"));
//                    //LoggerUtils.E("role: ", value.getString("role"));

                    TerminalInfoModel terminalInfoModel = new TerminalInfoModel();
                    terminalInfoModel.setTid(tid);
                    terminalInfoModel.setCity(value.optString("city", ""));
                    terminalInfoModel.setLocation(value.optString("location", ""));
                    terminalInfoModel.setAddress(value.optString("address", ""));
                    terminalInfoModel.setPinCode(value.optString("pinCode", ""));
                    terminalInfoModel.setMid(value.optString("mid", ""));
                    terminalInfoModel.setCid(value.optString("cid", ""));
                    terminalInfoModel.setDBA(value.optString("dba", ""));
                    terminalInfoModel.setCompanyName(value.optString("companyName", ""));
                    terminalInfoModel.setRole(value.optString("role", ""));
                    terminalInfoModel.setTidStatus(value.optString("tidStatus", ""));
                    terminalInfoModel.setRoleStatus(value.optString("roleStatus", ""));

                    ArrayList<String> list = new ArrayList<String>();
                    JSONArray jsonArray = value.getJSONArray("segments");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(jsonArray.get(i).toString());
                        }
                    }
                    terminalInfoModel.setSegments(list);

                    demoList.add(terminalInfoModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable t) {
            //LoggerUtils.E("My App", "Could not parse malformed JSON: \"" + Str + "\"");
        }
        Constants.TERMINAL_INFO_GET.clear();
        Constants.TERMINAL_INFO_GET.addAll(demoList);
        //LoggerUtils.E("Size of TID List : ", "" + Constants.TERMINAL_INFO_GET.size());

        ArrayList<CompanyInfoModel> demoCompanyInfoList = new ArrayList<>();
        ArrayList<String> cidDemoList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {
            if (i == 0) {
                cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                demoCompanyInfoList.add(companyInfoModel);
            } else {
                int index1 = cidDemoList.indexOf(Constants.TERMINAL_INFO_GET.get(i).getCid());
                if (index1 == -1) {
                    cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                    companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                    demoCompanyInfoList.add(companyInfoModel);
                }
            }
        }

        Constants.COMPANY_INFO_GET.clear();
        Constants.COMPANY_INFO_GET.addAll(demoCompanyInfoList);
        //LoggerUtils.E("Size of Company : ", "" + Constants.COMPANY_INFO_GET.size());

        ArrayList<CidLevelModel> cidLevelModelArrayList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {
            TerminalInfoModel terminalInfoModel = Constants.TERMINAL_INFO_GET.get(i);
            if (i == 0 || !checkCidIsPresent(cidLevelModelArrayList, terminalInfoModel.getCid())) {
                CidLevelModel cidLevelModel = new CidLevelModel();
                cidLevelModel.setCid(terminalInfoModel.getCid());
                cidLevelModel.setCompanyName(terminalInfoModel.getCompanyName());

                DbaLevelModel dbaLevelModel = new DbaLevelModel();
                dbaLevelModel.setDbaName(terminalInfoModel.getDBA());


                CityLevelModel cityLevelModel = new CityLevelModel();
                cityLevelModel.setCity(terminalInfoModel.getCity());


                MidLevelModel midLevelModel = new MidLevelModel();
                midLevelModel.setMid(terminalInfoModel.getMid());
                midLevelModel.setAddress(terminalInfoModel.getAddress());
                midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                midLevelModel.setLocation(terminalInfoModel.getLocation());


                TerminalLevelModel terminalLevelModel = new TerminalLevelModel();
                terminalLevelModel.setTid(terminalInfoModel.getTid());
                terminalLevelModel.setRole(terminalInfoModel.getRole());
                terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                terminalLevelModelArrayList.add(terminalLevelModel);

                midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                midLevelModelArrayList.add(midLevelModel);

                cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                cityLevelModelArrayList.add(cityLevelModel);

                dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                ArrayList<DbaLevelModel> dbaLevelModelArrayList = new ArrayList<>();
                dbaLevelModelArrayList.add(dbaLevelModel);

                cidLevelModel.setDbaLevelModels(dbaLevelModelArrayList);
                cidLevelModelArrayList.add(cidLevelModel);

            } else {
                boolean isCidAvailable = false;
                boolean isDbaAvailable = false;
                boolean isCityAvailable = false;
                boolean isMidAvailable = false;
                boolean isTerminalAvailable = false;

                int cidLocation = 0;
                int dbaLocation = 0;
                int cityLocation = 0;
                int midLocation = 0;
                int tidLocation = 0;

                cidLocation = getCidLocation(cidLevelModelArrayList, terminalInfoModel.getCid());

                CidLevelModel cidLevelModel = cidLevelModelArrayList.get(cidLocation);

                DbaLevelModel dbaLevelModel = new DbaLevelModel();
                CityLevelModel cityLevelModel = new CityLevelModel();
                MidLevelModel midLevelModel = new MidLevelModel();
                TerminalLevelModel terminalLevelModel = new TerminalLevelModel();

                if (checkDbaIsPresent(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA())) {
                    dbaLocation = getDbaLocation(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA());
                    dbaLevelModel = cidLevelModel.getDbaLevelModels().get(dbaLocation);
                    if (checkCityIsPresent(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity())) {
                        cityLocation = getCityLocation(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity());
                        cityLevelModel = dbaLevelModel.getCityLevelList().get(cityLocation);
                        if (checkMidIsPresent(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid())) {
                            midLocation = getMidLocation(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid());
                            midLevelModel = cityLevelModel.getMidLevelModels().get(midLocation);
                            terminalLevelModel = new TerminalLevelModel();
                            terminalLevelModel.setTid(terminalInfoModel.getTid());
                            terminalLevelModel.setRole(terminalInfoModel.getRole());
                            terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                            terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                            terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                            terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                            cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().get(midLocation).getTerminalLevelModels().add(terminalLevelModel);

                        } else {
                            midLevelModel = new MidLevelModel();
                            midLevelModel.setMid(terminalInfoModel.getMid());
                            midLevelModel.setAddress(terminalInfoModel.getAddress());
                            midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            midLevelModel.setLocation(terminalInfoModel.getLocation());

                            terminalLevelModel = new TerminalLevelModel();
                            terminalLevelModel.setTid(terminalInfoModel.getTid());
                            terminalLevelModel.setRole(terminalInfoModel.getRole());
                            terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                            terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                            terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                            terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                            ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                            terminalLevelModelArrayList.add(terminalLevelModel);

                            midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                            cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().add(midLevelModel);

                        }

                    } else {
                        cityLevelModel = new CityLevelModel();
                        cityLevelModel.setCity(terminalInfoModel.getCity());


                        midLevelModel = new MidLevelModel();
                        midLevelModel.setMid(terminalInfoModel.getMid());
                        midLevelModel.setAddress(terminalInfoModel.getAddress());
                        midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        midLevelModel.setLocation(terminalInfoModel.getLocation());


                        terminalLevelModel = new TerminalLevelModel();
                        terminalLevelModel.setTid(terminalInfoModel.getTid());
                        terminalLevelModel.setRole(terminalInfoModel.getRole());
                        terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                        terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                        terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                        terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                        ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                        terminalLevelModelArrayList.add(terminalLevelModel);

                        midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                        ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                        midLevelModelArrayList.add(midLevelModel);

                        cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                        cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().add(cityLevelModel);

                    }

                } else {
                    dbaLevelModel.setDbaName(terminalInfoModel.getDBA());

                    cityLevelModel = new CityLevelModel();
                    cityLevelModel.setCity(terminalInfoModel.getCity());


                    midLevelModel = new MidLevelModel();
                    midLevelModel.setMid(terminalInfoModel.getMid());
                    midLevelModel.setAddress(terminalInfoModel.getAddress());
                    midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    midLevelModel.setLocation(terminalInfoModel.getLocation());


                    terminalLevelModel = new TerminalLevelModel();
                    terminalLevelModel.setTid(terminalInfoModel.getTid());
                    terminalLevelModel.setRole(terminalInfoModel.getRole());
                    terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                    terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                    terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                    terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                    ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                    terminalLevelModelArrayList.add(terminalLevelModel);

                    midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                    ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                    midLevelModelArrayList.add(midLevelModel);

                    cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                    ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                    cityLevelModelArrayList.add(cityLevelModel);

                    dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                    cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().add(dbaLevelModel);

                }
            }
        }

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(cidLevelModelArrayList);
        //LoggerUtils.sout("CID Level Array List : " + json);
        //LoggerUtils.sout("Array is : " + cidLevelModelArrayList.size());
        globalCidLevel.clear();
        globalCidLevel.addAll(cidLevelModelArrayList);
//        userAccessCidLevel.clear();
//        userAccessCidLevel.addAll(cidLevelModelArrayList);
        storeCidLevel.clear();
        storeCidLevel.addAll(cidLevelModelArrayList);

        return cidLevelModelArrayList;
    }

    public static ArrayList<CidLevelModel> getUserAccessCIDList() {
        ArrayList<TerminalInfoModel> demoList = new ArrayList<>();

//        String Str = "{\n" +
//                "  \"status\": \"Success\",\n" +
//                "  \"respMessage\": \"Success\",\n" +
//                "  \"statusCode\": \"S101\",\n" +
//                "  \"terminalInfo\": [\n" +
//                "    {\n" +
//                "      \"112238\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Jayanagar\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53512\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112237\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Hosur Road\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53511\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112236\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98201915\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112235\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112234\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"9820181246\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112233\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98777224\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00002\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00003\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00001\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00004\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202014\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali West\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400092\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202012\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri West\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400048\",\n" +
//                "        \"mid\": \"1289\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202013\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400041\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202011\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"6789\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"73000020\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98177223\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";


        String Str = FILTER_JSON;
        try {

            JSONObject mainJsonObj = new JSONObject(Str);
            //LoggerUtils.E("status: ", mainJsonObj.getString("status"));
            //LoggerUtils.E("respMessage: ", mainJsonObj.getString("respMessage"));
            //LoggerUtils.E("statusCode: ", mainJsonObj.getString("statusCode"));
            JSONArray terminalArr = mainJsonObj.getJSONArray("terminalInfo");

            JSONObject terminalObj = terminalArr.getJSONObject(0);

            Iterator<String> iter = terminalObj.keys();
            while (iter.hasNext()) {
                String tid = iter.next();
                try {
                    JSONObject value = (JSONObject) terminalObj.get(tid);
//                    //LoggerUtils.E("Terminal Number : ", tid);
//                    //LoggerUtils.E("city: ", value.getString("city"));
//                    //LoggerUtils.E("location: ", value.getString("location"));
//                    //LoggerUtils.E("address: ", value.getString("address"));
//                    //LoggerUtils.E("mid: ", value.getString("mid"));
//                    //LoggerUtils.E("cid: ", value.getString("cid"));
//                    //LoggerUtils.E("DBA: ", value.getString("DBA"));
//                    //LoggerUtils.E("companyName: ", value.getString("companyName"));
//                    //LoggerUtils.E("role: ", value.getString("role"));

                    TerminalInfoModel terminalInfoModel = new TerminalInfoModel();
                    terminalInfoModel.setTid(tid);
                    terminalInfoModel.setCity(value.getString("city"));
                    terminalInfoModel.setLocation(value.getString("location"));
                    terminalInfoModel.setAddress(value.getString("address"));
                    terminalInfoModel.setPinCode(value.getString("pinCode"));
                    terminalInfoModel.setMid(value.getString("mid"));
                    terminalInfoModel.setCid(value.getString("cid"));
                    terminalInfoModel.setDBA(value.getString("dba"));
                    terminalInfoModel.setCompanyName(value.getString("companyName"));
                    terminalInfoModel.setRole(value.getString("role"));
                    terminalInfoModel.setTidStatus(value.optString("tidStatus", ""));
                    terminalInfoModel.setRoleStatus(value.optString("roleStatus", ""));


                    ArrayList<String> list = new ArrayList<String>();
                    JSONArray jsonArray = value.getJSONArray("segments");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(jsonArray.get(i).toString());
                        }
                    }
                    terminalInfoModel.setSegments(list);

                    demoList.add(terminalInfoModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable t) {
            //LoggerUtils.E("My App", "Could not parse malformed JSON: \"" + Str + "\"");
        }
        Constants.TERMINAL_INFO_GET.clear();
        Constants.TERMINAL_INFO_GET.addAll(demoList);
        //LoggerUtils.E("Size of TID List : ", "" + Constants.TERMINAL_INFO_GET.size());

        ArrayList<CompanyInfoModel> demoCompanyInfoList = new ArrayList<>();
        ArrayList<String> cidDemoList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {
            if (i == 0) {
                cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                demoCompanyInfoList.add(companyInfoModel);
            } else {
                int index1 = cidDemoList.indexOf(Constants.TERMINAL_INFO_GET.get(i).getCid());
                if (index1 == -1) {
                    cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                    companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                    demoCompanyInfoList.add(companyInfoModel);
                }
            }
        }

        Constants.COMPANY_INFO_GET.clear();
        Constants.COMPANY_INFO_GET.addAll(demoCompanyInfoList);
        //LoggerUtils.E("Size of Company : ", "" + Constants.COMPANY_INFO_GET.size());

        ArrayList<CidLevelModel> cidLevelModelArrayList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {

            TerminalInfoModel terminalInfoModel = Constants.TERMINAL_INFO_GET.get(i);
            if ((terminalInfoModel.getRole().equalsIgnoreCase("" + Constants.ROLE_OWNER)) || (terminalInfoModel.getRole().equalsIgnoreCase("" + Constants.ROLE_MANAGER1))) {
                if (i == 0 || !checkCidIsPresent(cidLevelModelArrayList, terminalInfoModel.getCid())) {
                    CidLevelModel cidLevelModel = new CidLevelModel();
                    cidLevelModel.setCid(terminalInfoModel.getCid());
                    cidLevelModel.setCompanyName(terminalInfoModel.getCompanyName());

                    DbaLevelModel dbaLevelModel = new DbaLevelModel();
                    dbaLevelModel.setDbaName(terminalInfoModel.getDBA());


                    CityLevelModel cityLevelModel = new CityLevelModel();
                    cityLevelModel.setCity(terminalInfoModel.getCity());


                    MidLevelModel midLevelModel = new MidLevelModel();
                    midLevelModel.setMid(terminalInfoModel.getMid());
                    midLevelModel.setAddress(terminalInfoModel.getAddress());
                    midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    midLevelModel.setLocation(terminalInfoModel.getLocation());


                    TerminalLevelModel terminalLevelModel = new TerminalLevelModel();
                    terminalLevelModel.setTid(terminalInfoModel.getTid());
                    terminalLevelModel.setRole(terminalInfoModel.getRole());
                    terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                    terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                    terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());
                    terminalLevelModel.setSegments(terminalInfoModel.getSegments());


                    ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                    terminalLevelModelArrayList.add(terminalLevelModel);

                    midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                    ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                    midLevelModelArrayList.add(midLevelModel);

                    cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                    ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                    cityLevelModelArrayList.add(cityLevelModel);

                    dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                    ArrayList<DbaLevelModel> dbaLevelModelArrayList = new ArrayList<>();
                    dbaLevelModelArrayList.add(dbaLevelModel);

                    cidLevelModel.setDbaLevelModels(dbaLevelModelArrayList);
                    cidLevelModelArrayList.add(cidLevelModel);

                } else {
                    boolean isCidAvailable = false;
                    boolean isDbaAvailable = false;
                    boolean isCityAvailable = false;
                    boolean isMidAvailable = false;
                    boolean isTerminalAvailable = false;

                    int cidLocation = 0;
                    int dbaLocation = 0;
                    int cityLocation = 0;
                    int midLocation = 0;
                    int tidLocation = 0;

                    cidLocation = getCidLocation(cidLevelModelArrayList, terminalInfoModel.getCid());

                    CidLevelModel cidLevelModel = cidLevelModelArrayList.get(cidLocation);

                    DbaLevelModel dbaLevelModel = new DbaLevelModel();
                    CityLevelModel cityLevelModel = new CityLevelModel();
                    MidLevelModel midLevelModel = new MidLevelModel();
                    TerminalLevelModel terminalLevelModel = new TerminalLevelModel();

                    if (checkDbaIsPresent(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA())) {
                        dbaLocation = getDbaLocation(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA());
                        dbaLevelModel = cidLevelModel.getDbaLevelModels().get(dbaLocation);
                        if (checkCityIsPresent(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity())) {
                            cityLocation = getCityLocation(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity());
                            cityLevelModel = dbaLevelModel.getCityLevelList().get(cityLocation);
                            if (checkMidIsPresent(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid())) {
                                midLocation = getMidLocation(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid());
                                midLevelModel = cityLevelModel.getMidLevelModels().get(midLocation);
                                terminalLevelModel = new TerminalLevelModel();
                                terminalLevelModel.setTid(terminalInfoModel.getTid());
                                terminalLevelModel.setRole(terminalInfoModel.getRole());
                                terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                                terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                                terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());
                                terminalLevelModel.setSegments(terminalInfoModel.getSegments());


                                cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().get(midLocation).getTerminalLevelModels().add(terminalLevelModel);

                            } else {
                                midLevelModel = new MidLevelModel();
                                midLevelModel.setMid(terminalInfoModel.getMid());
                                midLevelModel.setAddress(terminalInfoModel.getAddress());
                                midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                midLevelModel.setLocation(terminalInfoModel.getLocation());

                                terminalLevelModel = new TerminalLevelModel();
                                terminalLevelModel.setTid(terminalInfoModel.getTid());
                                terminalLevelModel.setRole(terminalInfoModel.getRole());
                                terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                                terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                                terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());
                                terminalLevelModel.setSegments(terminalInfoModel.getSegments());


                                ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                                terminalLevelModelArrayList.add(terminalLevelModel);

                                midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                                cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().add(midLevelModel);

                            }

                        } else {
                            cityLevelModel = new CityLevelModel();
                            cityLevelModel.setCity(terminalInfoModel.getCity());


                            midLevelModel = new MidLevelModel();
                            midLevelModel.setMid(terminalInfoModel.getMid());
                            midLevelModel.setAddress(terminalInfoModel.getAddress());
                            midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            midLevelModel.setLocation(terminalInfoModel.getLocation());


                            terminalLevelModel = new TerminalLevelModel();
                            terminalLevelModel.setTid(terminalInfoModel.getTid());
                            terminalLevelModel.setRole(terminalInfoModel.getRole());
                            terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                            terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                            terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());
                            terminalLevelModel.setSegments(terminalInfoModel.getSegments());


                            ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                            terminalLevelModelArrayList.add(terminalLevelModel);

                            midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                            ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                            midLevelModelArrayList.add(midLevelModel);

                            cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                            cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().add(cityLevelModel);

                        }

                    } else {
                        dbaLevelModel.setDbaName(terminalInfoModel.getDBA());

                        cityLevelModel = new CityLevelModel();
                        cityLevelModel.setCity(terminalInfoModel.getCity());


                        midLevelModel = new MidLevelModel();
                        midLevelModel.setMid(terminalInfoModel.getMid());
                        midLevelModel.setAddress(terminalInfoModel.getAddress());
                        midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        midLevelModel.setLocation(terminalInfoModel.getLocation());


                        terminalLevelModel = new TerminalLevelModel();
                        terminalLevelModel.setTid(terminalInfoModel.getTid());
                        terminalLevelModel.setRole(terminalInfoModel.getRole());
                        terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                        terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                        terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());
                        terminalLevelModel.setSegments(terminalInfoModel.getSegments());

                        ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                        terminalLevelModelArrayList.add(terminalLevelModel);

                        midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                        ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                        midLevelModelArrayList.add(midLevelModel);

                        cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                        ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                        cityLevelModelArrayList.add(cityLevelModel);

                        dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                        cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().add(dbaLevelModel);

                    }
                }
            }

        }

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(cidLevelModelArrayList);
        //LoggerUtils.sout("CID Level Array List : " + json);
        //LoggerUtils.sout("Array is : " + cidLevelModelArrayList.size());
        userAccessCidLevel.clear();
        userAccessCidLevel.addAll(cidLevelModelArrayList);

        return cidLevelModelArrayList;
    }

    public static ArrayList<String> getAllTIDs() {
        ArrayList<TerminalInfoModel> demoList = new ArrayList<>();
        ArrayList<String> allTIDs = new ArrayList<>();
        String Str = FILTER_JSON;
        try {

            JSONObject mainJsonObj = new JSONObject(Str);
            //LoggerUtils.E("status: ", mainJsonObj.getString("status"));
            //LoggerUtils.E("respMessage: ", mainJsonObj.getString("respMessage"));
            //LoggerUtils.E("statusCode: ", mainJsonObj.getString("statusCode"));
            JSONArray terminalArr = mainJsonObj.getJSONArray("terminalInfo");

            JSONObject terminalObj = terminalArr.getJSONObject(0);

            Iterator<String> iter = terminalObj.keys();
            while (iter.hasNext()) {
                String tid = iter.next();
                try {
                    JSONObject value = (JSONObject) terminalObj.get(tid);
//                    //LoggerUtils.E("Terminal Number : ", tid);
//                    //LoggerUtils.E("city: ", value.getString("city"));
//                    //LoggerUtils.E("location: ", value.getString("location"));
//                    //LoggerUtils.E("address: ", value.getString("address"));
//                    //LoggerUtils.E("mid: ", value.getString("mid"));
//                    //LoggerUtils.E("cid: ", value.getString("cid"));
//                    //LoggerUtils.E("DBA: ", value.getString("DBA"));
//                    //LoggerUtils.E("companyName: ", value.getString("companyName"));
//                    //LoggerUtils.E("role: ", value.getString("role"));

                    TerminalInfoModel terminalInfoModel = new TerminalInfoModel();
                    terminalInfoModel.setTid(tid);
                    terminalInfoModel.setCity(value.getString("city"));
                    terminalInfoModel.setLocation(value.optString("location", ""));
                    terminalInfoModel.setAddress(value.getString("address"));
                    terminalInfoModel.setPinCode(value.getString("pinCode"));
                    terminalInfoModel.setMid(value.getString("mid"));
                    terminalInfoModel.setCid(value.getString("cid"));
                    terminalInfoModel.setDBA(value.getString("dba"));
                    terminalInfoModel.setCompanyName(value.optString("companyName", ""));
                    terminalInfoModel.setRole(value.getString("role"));
                    terminalInfoModel.setTidStatus(value.getString("tidStatus"));
                    terminalInfoModel.setRoleStatus(value.getString("roleStatus"));

                    ArrayList<String> list = new ArrayList<String>();
                    JSONArray jsonArray = value.getJSONArray("segments");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(jsonArray.get(i).toString());
                        }
                    }
                    terminalInfoModel.setSegments(list);

                    demoList.add(terminalInfoModel);
                    allTIDs.add(tid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable t) {
            //LoggerUtils.E("My App", "Could not parse malformed JSON: \"" + Str + "\"");
        }
        return allTIDs;
    }

    public static ArrayList<String> getAllMIDs() {
        ArrayList<TerminalInfoModel> demoList = new ArrayList<>();
        ArrayList<String> allMIDs = new ArrayList<>();
        String Str = FILTER_JSON;
        try {

            JSONObject mainJsonObj = new JSONObject(Str);
            //LoggerUtils.E("status: ", mainJsonObj.getString("status"));
            //LoggerUtils.E("respMessage: ", mainJsonObj.getString("respMessage"));
            //LoggerUtils.E("statusCode: ", mainJsonObj.getString("statusCode"));
            JSONArray terminalArr = mainJsonObj.getJSONArray("terminalInfo");

            JSONObject terminalObj = terminalArr.getJSONObject(0);

            Iterator<String> iter = terminalObj.keys();
            while (iter.hasNext()) {
                String tid = iter.next();
                try {
                    JSONObject value = (JSONObject) terminalObj.get(tid);
//                    //LoggerUtils.E("Terminal Number : ", tid);
//                    //LoggerUtils.E("city: ", value.getString("city"));
//                    //LoggerUtils.E("location: ", value.getString("location"));
//                    //LoggerUtils.E("address: ", value.getString("address"));
//                    //LoggerUtils.E("mid: ", value.getString("mid"));
//                    //LoggerUtils.E("cid: ", value.getString("cid"));
//                    //LoggerUtils.E("DBA: ", value.getString("DBA"));
//                    //LoggerUtils.E("companyName: ", value.getString("companyName"));
//                    //LoggerUtils.E("role: ", value.getString("role"));

                    TerminalInfoModel terminalInfoModel = new TerminalInfoModel();
                    terminalInfoModel.setTid(tid);
                    terminalInfoModel.setCity(value.getString("city"));
                    terminalInfoModel.setLocation(value.optString("location", ""));
                    terminalInfoModel.setAddress(value.getString("address"));
                    terminalInfoModel.setPinCode(value.getString("pinCode"));
                    terminalInfoModel.setMid(value.getString("mid"));
                    terminalInfoModel.setCid(value.getString("cid"));
                    terminalInfoModel.setDBA(value.getString("dba"));
                    terminalInfoModel.setCompanyName(value.optString("companyName", ""));
                    terminalInfoModel.setRole(value.getString("role"));
                    terminalInfoModel.setRoleStatus(value.getString("roleStatus"));

                    ArrayList<String> list = new ArrayList<String>();
                    JSONArray jsonArray = value.getJSONArray("segments");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(jsonArray.get(i).toString());
                        }
                    }
                    terminalInfoModel.setSegments(list);


                    demoList.add(terminalInfoModel);
                    allMIDs.add(value.getString("mid"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable t) {
            //LoggerUtils.E("My App", "Could not parse malformed JSON: \"" + Str + "\"");
        }


        return allMIDs;
    }

    public static ArrayList<CidLevelModel> getFilterCidList() {
        ArrayList<TerminalInfoModel> demoList = new ArrayList<>();

//        String Str = "{\n" +
//                "  \"status\": \"Success\",\n" +
//                "  \"respMessage\": \"Success\",\n" +
//                "  \"statusCode\": \"S101\",\n" +
//                "  \"terminalInfo\": [\n" +
//                "    {\n" +
//                "      \"112238\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Jayanagar\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53512\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112237\": {\n" +
//                "        \"city\": \"Bengaluru\",\n" +
//                "        \"location\": \"Hosur Road\",\n" +
//                "        \"address\": \"null, Bengaluru, null-null\",\n" +
//                "        \"mid\": \"Y53511\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112236\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98201915\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112235\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112234\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"9820181246\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"112233\": {\n" +
//                "        \"city\": \"PERNEM\",\n" +
//                "        \"location\": \"PANJIM\",\n" +
//                "        \"address\": \"H NO 657 SARMALENORTH GOAPERNEM, PERNEM, GOA-403512\",\n" +
//                "        \"mid\": \"1234\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"SHUBHAM BAR AND RESTAURANT\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98777224\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Roads and Pavements, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"9876\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Rovers\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00002\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00003\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00001\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"00004\": {\n" +
//                "        \"city\": \"Mumbai \",\n" +
//                "        \"location\": \"Anderi West\",\n" +
//                "        \"address\": \"Anderi West, Mumbai , Maharastra-560079\",\n" +
//                "        \"mid\": \"Y53514\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Esperance\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202014\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali West\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400092\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202012\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri West\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400048\",\n" +
//                "        \"mid\": \"1289\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202013\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Borivali\",\n" +
//                "        \"address\": \"Metals and Marbles, Mumbai, Maharastra-400041\",\n" +
//                "        \"mid\": \"4567\",\n" +
//                "        \"cid\": \"10\",\n" +
//                "        \"dba\": \"Silver Shines\",\n" +
//                "        \"companyName\": \"Elite and Grey\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98202011\": {\n" +
//                "        \"city\": \"Mumbai\",\n" +
//                "        \"location\": \"Andheri East\",\n" +
//                "        \"address\": \"Homes and Brains, Mumbai, Maharastra-400047\",\n" +
//                "        \"mid\": \"6789\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"The Humans\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"50200054034612\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"73000020\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      },\n" +
//                "      \"98177223\": {\n" +
//                "        \"city\": \"Bangalore \",\n" +
//                "        \"location\": \"bhopal\",\n" +
//                "        \"address\": \"Basaveshwar nagar, Bangalore , Karnataka-560079\",\n" +
//                "        \"mid\": \"Y53503\",\n" +
//                "        \"cid\": \"3\",\n" +
//                "        \"dba\": \"Vasan care\",\n" +
//                "        \"companyName\": \"RoRoGaJo and Co.\",\n" +
//                "        \"accountNumber\": \"123456\",\n" +
//                "        \"role\": \"owner\"\n" +
//                "      }\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}";


        String Str = FILTER_JSON;
        try {

            JSONObject mainJsonObj = new JSONObject(Str);
            //LoggerUtils.E("status: ", mainJsonObj.getString("status"));
            //LoggerUtils.E("respMessage: ", mainJsonObj.getString("respMessage"));
            //LoggerUtils.E("statusCode: ", mainJsonObj.getString("statusCode"));
            JSONArray terminalArr = mainJsonObj.getJSONArray("terminalInfo");

            JSONObject terminalObj = terminalArr.getJSONObject(0);

            Iterator<String> iter = terminalObj.keys();
            while (iter.hasNext()) {
                String tid = iter.next();
                try {
                    JSONObject value = (JSONObject) terminalObj.get(tid);
//                    //LoggerUtils.E("Terminal Number : ", tid);
//                    //LoggerUtils.E("city: ", value.getString("city"));
//                    //LoggerUtils.E("location: ", value.getString("location"));
//                    //LoggerUtils.E("address: ", value.getString("address"));
//                    //LoggerUtils.E("mid: ", value.getString("mid"));
//                    //LoggerUtils.E("cid: ", value.getString("cid"));
//                    //LoggerUtils.E("DBA: ", value.getString("DBA"));
//                    //LoggerUtils.E("companyName: ", value.getString("companyName"));
//                    //LoggerUtils.E("role: ", value.getString("role"));

                    TerminalInfoModel terminalInfoModel = new TerminalInfoModel();
                    terminalInfoModel.setTid(tid);
                    terminalInfoModel.setCity(value.getString("city"));
                    terminalInfoModel.setLocation(value.optString("location", ""));
                    terminalInfoModel.setAddress(value.getString("address"));
                    terminalInfoModel.setPinCode(value.getString("pinCode"));
                    terminalInfoModel.setMid(value.getString("mid"));
                    terminalInfoModel.setCid(value.getString("cid"));
                    terminalInfoModel.setDBA(value.getString("dba"));
                    terminalInfoModel.setCompanyName(value.optString("companyName", ""));
                    terminalInfoModel.setRole(value.getString("role"));
                    terminalInfoModel.setTidStatus(value.getString("tidStatus"));
                    terminalInfoModel.setRoleStatus(value.getString("roleStatus"));

                    ArrayList<String> list = new ArrayList<String>();
                    JSONArray jsonArray = value.getJSONArray("segments");
                    if (jsonArray != null) {
                        int len = jsonArray.length();
                        for (int i = 0; i < len; i++) {
                            list.add(jsonArray.get(i).toString());
                        }
                    }
                    terminalInfoModel.setSegments(list);

                    demoList.add(terminalInfoModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Throwable t) {
            //LoggerUtils.E("My App", "Could not parse malformed JSON: \"" + Str + "\"");
        }
        Constants.TERMINAL_INFO_GET.clear();
        Constants.TERMINAL_INFO_GET.addAll(demoList);
        //LoggerUtils.E("Size of TID List : ", "" + Constants.TERMINAL_INFO_GET.size());

        ArrayList<CompanyInfoModel> demoCompanyInfoList = new ArrayList<>();
        ArrayList<String> cidDemoList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {
            if (i == 0) {
                cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                demoCompanyInfoList.add(companyInfoModel);
            } else {
                int index1 = cidDemoList.indexOf(Constants.TERMINAL_INFO_GET.get(i).getCid());
                if (index1 == -1) {
                    cidDemoList.add(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    CompanyInfoModel companyInfoModel = new CompanyInfoModel();
                    companyInfoModel.setCid(Constants.TERMINAL_INFO_GET.get(i).getCid());
                    companyInfoModel.setCompanyName(Constants.TERMINAL_INFO_GET.get(i).getCompanyName());
                    demoCompanyInfoList.add(companyInfoModel);
                }
            }
        }

        Constants.COMPANY_INFO_GET.clear();
        Constants.COMPANY_INFO_GET.addAll(demoCompanyInfoList);
//        //LoggerUtils.E("Size of Company : ", "" + Constants.COMPANY_INFO_GET.size());

        ArrayList<CidLevelModel> cidLevelModelArrayList = new ArrayList<>();
        for (int i = 0; i < Constants.TERMINAL_INFO_GET.size(); i++) {
            TerminalInfoModel terminalInfoModel = Constants.TERMINAL_INFO_GET.get(i);
            if (!(terminalInfoModel.getRole().equalsIgnoreCase("" + Constants.ROLE_CASHIER))) {
                if (i == 0 || !checkCidIsPresent(cidLevelModelArrayList, terminalInfoModel.getCid())) {
                    CidLevelModel cidLevelModel = new CidLevelModel();
                    cidLevelModel.setCid(terminalInfoModel.getCid());
                    cidLevelModel.setCompanyName(terminalInfoModel.getCompanyName());

                    DbaLevelModel dbaLevelModel = new DbaLevelModel();
                    dbaLevelModel.setDbaName(terminalInfoModel.getDBA());


                    CityLevelModel cityLevelModel = new CityLevelModel();
                    cityLevelModel.setCity(terminalInfoModel.getCity());


                    MidLevelModel midLevelModel = new MidLevelModel();
                    midLevelModel.setMid(terminalInfoModel.getMid());
                    midLevelModel.setAddress(terminalInfoModel.getAddress());
                    midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    midLevelModel.setLocation(terminalInfoModel.getLocation());


                    TerminalLevelModel terminalLevelModel = new TerminalLevelModel();
                    terminalLevelModel.setTid(terminalInfoModel.getTid());
                    terminalLevelModel.setRole(terminalInfoModel.getRole());
                    terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                    terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                    terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                    terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                    terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());

                    ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                    terminalLevelModelArrayList.add(terminalLevelModel);

                    midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                    ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                    midLevelModelArrayList.add(midLevelModel);

                    cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                    ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                    cityLevelModelArrayList.add(cityLevelModel);

                    dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                    ArrayList<DbaLevelModel> dbaLevelModelArrayList = new ArrayList<>();
                    dbaLevelModelArrayList.add(dbaLevelModel);

                    cidLevelModel.setDbaLevelModels(dbaLevelModelArrayList);
                    cidLevelModelArrayList.add(cidLevelModel);

                } else {
                    boolean isCidAvailable = false;
                    boolean isDbaAvailable = false;
                    boolean isCityAvailable = false;
                    boolean isMidAvailable = false;
                    boolean isTerminalAvailable = false;

                    int cidLocation = 0;
                    int dbaLocation = 0;
                    int cityLocation = 0;
                    int midLocation = 0;
                    int tidLocation = 0;

                    cidLocation = getCidLocation(cidLevelModelArrayList, terminalInfoModel.getCid());

                    CidLevelModel cidLevelModel = cidLevelModelArrayList.get(cidLocation);

                    DbaLevelModel dbaLevelModel = new DbaLevelModel();
                    CityLevelModel cityLevelModel = new CityLevelModel();
                    MidLevelModel midLevelModel = new MidLevelModel();
                    TerminalLevelModel terminalLevelModel = new TerminalLevelModel();

                    if (checkDbaIsPresent(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA())) {
                        dbaLocation = getDbaLocation(cidLevelModel.getDbaLevelModels(), terminalInfoModel.getDBA());
                        dbaLevelModel = cidLevelModel.getDbaLevelModels().get(dbaLocation);
                        if (checkCityIsPresent(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity())) {
                            cityLocation = getCityLocation(dbaLevelModel.getCityLevelList(), terminalInfoModel.getCity());
                            cityLevelModel = dbaLevelModel.getCityLevelList().get(cityLocation);
                            if (checkMidIsPresent(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid())) {
                                midLocation = getMidLocation(cityLevelModel.getMidLevelModels(), terminalInfoModel.getMid());
                                midLevelModel = cityLevelModel.getMidLevelModels().get(midLocation);
                                terminalLevelModel = new TerminalLevelModel();
                                terminalLevelModel.setTid(terminalInfoModel.getTid());
                                terminalLevelModel.setRole(terminalInfoModel.getRole());
                                terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                                terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                                terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                                terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                                cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().get(midLocation).getTerminalLevelModels().add(terminalLevelModel);

                            } else {
                                midLevelModel = new MidLevelModel();
                                midLevelModel.setMid(terminalInfoModel.getMid());
                                midLevelModel.setAddress(terminalInfoModel.getAddress());
                                midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                midLevelModel.setLocation(terminalInfoModel.getLocation());

                                terminalLevelModel = new TerminalLevelModel();
                                terminalLevelModel.setTid(terminalInfoModel.getTid());
                                terminalLevelModel.setRole(terminalInfoModel.getRole());
                                terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                                terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                                terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                                terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                                terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                                ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                                terminalLevelModelArrayList.add(terminalLevelModel);

                                midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                                cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().get(cityLocation).getMidLevelModels().add(midLevelModel);

                            }

                        } else {
                            cityLevelModel = new CityLevelModel();
                            cityLevelModel.setCity(terminalInfoModel.getCity());


                            midLevelModel = new MidLevelModel();
                            midLevelModel.setMid(terminalInfoModel.getMid());
                            midLevelModel.setAddress(terminalInfoModel.getAddress());
                            midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            midLevelModel.setLocation(terminalInfoModel.getLocation());


                            terminalLevelModel = new TerminalLevelModel();
                            terminalLevelModel.setTid(terminalInfoModel.getTid());
                            terminalLevelModel.setRole(terminalInfoModel.getRole());
                            terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                            terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                            terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                            terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                            terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());


                            ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                            terminalLevelModelArrayList.add(terminalLevelModel);

                            midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                            ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                            midLevelModelArrayList.add(midLevelModel);

                            cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                            cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().get(dbaLocation).getCityLevelList().add(cityLevelModel);

                        }

                    } else {
                        dbaLevelModel.setDbaName(terminalInfoModel.getDBA());

                        cityLevelModel = new CityLevelModel();
                        cityLevelModel.setCity(terminalInfoModel.getCity());


                        midLevelModel = new MidLevelModel();
                        midLevelModel.setMid(terminalInfoModel.getMid());
                        midLevelModel.setAddress(terminalInfoModel.getAddress());
                        midLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        midLevelModel.setLocation(terminalInfoModel.getLocation());


                        terminalLevelModel = new TerminalLevelModel();
                        terminalLevelModel.setTid(terminalInfoModel.getTid());
                        terminalLevelModel.setRole(terminalInfoModel.getRole());
                        terminalLevelModel.setAddress(terminalInfoModel.getAddress());
                        terminalLevelModel.setPinCode(terminalInfoModel.getPinCode());
                        terminalLevelModel.setSegments(terminalInfoModel.getSegments());
                        terminalLevelModel.setTidStatus(terminalInfoModel.getTidStatus());
                        terminalLevelModel.setRoleStatus(terminalInfoModel.getRoleStatus());

                        ArrayList<TerminalLevelModel> terminalLevelModelArrayList = new ArrayList<>();
                        terminalLevelModelArrayList.add(terminalLevelModel);

                        midLevelModel.setTerminalLevelModels(terminalLevelModelArrayList);
                        ArrayList<MidLevelModel> midLevelModelArrayList = new ArrayList<>();
                        midLevelModelArrayList.add(midLevelModel);

                        cityLevelModel.setMidLevelModels(midLevelModelArrayList);
                        ArrayList<CityLevelModel> cityLevelModelArrayList = new ArrayList<>();
                        cityLevelModelArrayList.add(cityLevelModel);

                        dbaLevelModel.setCityLevelList(cityLevelModelArrayList);
                        cidLevelModelArrayList.get(cidLocation).getDbaLevelModels().add(dbaLevelModel);

                    }
                }
            }

        }

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(cidLevelModelArrayList);
        //LoggerUtils.sout("CID Level Array List : " + json);
        //LoggerUtils.sout("Array is : " + cidLevelModelArrayList.size());
        FilterCidLevel.clear();
        FilterCidLevel.addAll(cidLevelModelArrayList);

        return cidLevelModelArrayList;
    }

    private static int getMidLocation(ArrayList<MidLevelModel> midLevelModels, String mid) {
        int loc = midLevelModels.size() - 1;
        for (int i = 0; i < midLevelModels.size(); i++) {
            if (mid.trim().equalsIgnoreCase(midLevelModels.get(i).getMid().trim())) {
                loc = i;
                break;
            }
        }
        return loc;
    }

    private static boolean checkMidIsPresent(ArrayList<MidLevelModel> midLevelModels, String mid) {
        for (int i = 0; i < midLevelModels.size(); i++) {
            if (mid.trim().equalsIgnoreCase(midLevelModels.get(i).getMid().trim())) {
                return true;
            }
        }
        return false;
    }

    private static int getCityLocation(ArrayList<CityLevelModel> cityLevelList, String city) {
        int loc = cityLevelList.size() - 1;
        for (int i = 0; i < cityLevelList.size(); i++) {
            if (city.trim().equalsIgnoreCase(cityLevelList.get(i).getCity().trim())) {
                loc = i;
                break;
            }
        }
        return loc;
    }

    private static boolean checkCityIsPresent(ArrayList<CityLevelModel> cityLevelList, String city) {
        for (int i = 0; i < cityLevelList.size(); i++) {
            if (city.trim().equalsIgnoreCase(cityLevelList.get(i).getCity().trim())) {
                return true;
            }
        }
        return false;
    }

    private static int getDbaLocation(ArrayList<DbaLevelModel> dbaLevelModels, String dba) {
        int loc = dbaLevelModels.size() - 1;
        for (int i = 0; i < dbaLevelModels.size(); i++) {
            if (dba.trim().equalsIgnoreCase(dbaLevelModels.get(i).getDbaName().trim())) {
                loc = i;
                break;
            }
        }
        return loc;
    }

    private static boolean checkDbaIsPresent(ArrayList<DbaLevelModel> dbaLevelModelArrayList, String dba) {
        for (int i = 0; i < dbaLevelModelArrayList.size(); i++) {
            if (dba.trim().equalsIgnoreCase(dbaLevelModelArrayList.get(i).getDbaName().trim())) {
                return true;
            }
        }
        return false;
    }

    private static int getCidLocation(ArrayList<CidLevelModel> cidLevelModelArrayList, String cid) {
        int loc = cidLevelModelArrayList.size() - 1;
        for (int i = 0; i < cidLevelModelArrayList.size(); i++) {
            if (cid.trim().equalsIgnoreCase(cidLevelModelArrayList.get(i).getCid().trim())) {
                loc = i;
                break;
            }
        }
        return loc;
    }

    private static boolean checkCidIsPresent(ArrayList<CidLevelModel> cidLevelModelArrayList, String cid) {
        for (int i = 0; i < cidLevelModelArrayList.size(); i++) {
            if (cid.trim().equalsIgnoreCase(cidLevelModelArrayList.get(i).getCid().trim())) {
                return true;
            }
        }
        return false;
    }

    public static void getSelectedLocationFilter(LocationFilterApplyCallBack locationFilterApplyCallBack) {
        ArrayList<CidLevelModel> pojoArrayListSelected = new ArrayList<>();
        if (!(FilterCidLevel != null && FilterCidLevel.size() > 0)) {
            getFilterCidList();
            pojoArrayListSelected.addAll(LocationFilterUtils.FilterCidLevel);
        } else {
            if (selectedCidLevel != null && selectedCidLevel.size() > 0) {
                pojoArrayListSelected.clear();
                pojoArrayListSelected.addAll(selectedCidLevel);
            } else {
                pojoArrayListSelected.clear();
                pojoArrayListSelected.addAll(LocationFilterUtils.FilterCidLevel);
            }
        }
        CidLevelModel selectedCidModel = new CidLevelModel();
        ArrayList<DbaLevelModel> selectedDbaLevel = new ArrayList<>();
        ArrayList<CityLevelModel> selectedcityLevelModels = new ArrayList<>();
        ArrayList<MidLevelModel> selectedmidLevelModels = new ArrayList<>();
        ArrayList<TerminalLevelModel> selectedterminalLevelModels = new ArrayList<>();
        for (int m = 0; m < pojoArrayListSelected.size(); m++) {
            if (pojoArrayListSelected.get(m).isSelected()) {
                for (int i = 0; i < pojoArrayListSelected.get(m).getDbaLevelModels().size(); i++) {
                    DbaLevelModel dbaLevelModel = pojoArrayListSelected.get(m).getDbaLevelModels().get(i);
                    if (dbaLevelModel.isSelected()) {
                        selectedDbaLevel.add(dbaLevelModel);
                        for (int j = 0; j < pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                            CityLevelModel cityLevelModel = pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j);
                            if (cityLevelModel.isSelected()) {
                                selectedCidModel = pojoArrayListSelected.get(m);
                                selectedcityLevelModels.add(cityLevelModel);
                                for (int k = 0; k < pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                                    MidLevelModel midLevelModel = pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k);
                                    if (midLevelModel.isSelected()) {
                                        selectedmidLevelModels.add(midLevelModel);
                                        for (int l = 0; l < pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {

                                            String roleStatus = pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(m).getRoleStatus();
                                            String userRole = pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(m).getRole();

                                            if (roleStatus.equalsIgnoreCase("Active") || userRole.equalsIgnoreCase("Owner")) {
                                                selectedterminalLevelModels.addAll(pojoArrayListSelected.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels());

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!(selectedDbaLevel.size() > 0 || selectedcityLevelModels.size() > 0 || selectedmidLevelModels.size() > 0 || selectedterminalLevelModels.size() > 0)) {
            selectedCidModel = new CidLevelModel();
            selectedDbaLevel = new ArrayList<>();
            selectedcityLevelModels = new ArrayList<>();
            selectedmidLevelModels = new ArrayList<>();
            selectedterminalLevelModels = new ArrayList<>();
            if (pojoArrayListSelected.size() > 0) {
                for (int i = 0; i < pojoArrayListSelected.get(0).getDbaLevelModels().size(); i++) {
                    DbaLevelModel dbaLevelModel = pojoArrayListSelected.get(0).getDbaLevelModels().get(i);
                    selectedDbaLevel.add(dbaLevelModel);
                    for (int j = 0; j < pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                        CityLevelModel cityLevelModel = pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j);
                        selectedcityLevelModels.add(cityLevelModel);
                        for (int k = 0; k < pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                            MidLevelModel midLevelModel = pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k);
                            selectedmidLevelModels.add(midLevelModel);
                            for (int l = 0; l < pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {

                                String roleStatus = pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRoleStatus();
                                String userRole = pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRole();

                                if (roleStatus.equalsIgnoreCase("Active") || userRole.equalsIgnoreCase("Owner")) {

                                    selectedterminalLevelModels.addAll(pojoArrayListSelected.get(0).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels());
                                }
                            }
                        }
                    }
                }
                selectedCidModel = pojoArrayListSelected.get(0);
            }
        }

        locationFilterApplyCallBack.allSelectedData(selectedCidModel, selectedDbaLevel, selectedcityLevelModels, selectedmidLevelModels, selectedterminalLevelModels);


//        selectedCidModel = pojoArrayListSelected.get(m);

    }


    public static void getSelectedLocation(LocationFilterApplyCallBack locationFilterApplyCallBack) {
        if (!(globalCidLevel != null && globalCidLevel.size() > 0)) {
            getTerminalInfoList();
        }
        CidLevelModel selectedCidModel = new CidLevelModel();
        ArrayList<DbaLevelModel> selectedDbaLevel = new ArrayList<>();
        ArrayList<CityLevelModel> selectedcityLevelModels = new ArrayList<>();
        ArrayList<MidLevelModel> selectedmidLevelModels = new ArrayList<>();
        ArrayList<TerminalLevelModel> selectedterminalLevelModels = new ArrayList<>();
        for (int m = 0; m < LocationFilterUtils.globalCidLevel.size(); m++) {
            for (int i = 0; i < LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().size(); i++) {
                DbaLevelModel dbaLevelModel = LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i);
                if (dbaLevelModel.isSelected()) {
                    selectedDbaLevel.add(dbaLevelModel);
                    for (int j = 0; j < LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().size(); j++) {
                        CityLevelModel cityLevelModel = LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j);
                        if (cityLevelModel.isSelected()) {
                            selectedCidModel = LocationFilterUtils.globalCidLevel.get(m);
                            selectedcityLevelModels.add(cityLevelModel);
                            for (int k = 0; k < LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                                MidLevelModel midLevelModel = LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k);
                                if (midLevelModel.isSelected()) {
                                    selectedmidLevelModels.add(midLevelModel);
                                    for (int l = 0; l < LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {

                                        String roleStatus = LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRoleStatus();
                                        String userRole = LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRole();
                                        if (roleStatus.equalsIgnoreCase("Active") || userRole.equalsIgnoreCase("Owner")) {

                                            selectedterminalLevelModels.addAll(LocationFilterUtils.globalCidLevel.get(m).getDbaLevelModels().get(i).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!(selectedDbaLevel.size() > 0 || selectedcityLevelModels.size() > 0 || selectedmidLevelModels.size() > 0 || selectedterminalLevelModels.size() > 0)) {
            selectedCidModel = new CidLevelModel();
            selectedDbaLevel = new ArrayList<>();
            selectedcityLevelModels = new ArrayList<>();
            selectedmidLevelModels = new ArrayList<>();
            selectedterminalLevelModels = new ArrayList<>();
            if (LocationFilterUtils.globalCidLevel.size() > 0) {
//                for (int i = 0; i < LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().size(); i++) {
                DbaLevelModel dbaLevelModel = LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0);
                selectedDbaLevel.add(dbaLevelModel);
                for (int j = 0; j < LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().size(); j++) {
                    CityLevelModel cityLevelModel = LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j);
                    selectedcityLevelModels.add(cityLevelModel);
                    for (int k = 0; k < LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().size(); k++) {
                        MidLevelModel midLevelModel = LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().get(k);
                        selectedmidLevelModels.add(midLevelModel);
                        for (int l = 0; l < LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().size(); l++) {

                            String roleStatus = LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRoleStatus();
                            String userRole = LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels().get(l).getRole();
                            if (roleStatus.equalsIgnoreCase("Active") || userRole.equalsIgnoreCase("Owner")) {

                                selectedterminalLevelModels.addAll(LocationFilterUtils.globalCidLevel.get(0).getDbaLevelModels().get(0).getCityLevelList().get(j).getMidLevelModels().get(k).getTerminalLevelModels());
                            }
                        }
                    }
                }
//                }
                selectedCidModel = LocationFilterUtils.globalCidLevel.get(0);
            }
        }

        locationFilterApplyCallBack.allSelectedData(selectedCidModel, selectedDbaLevel, selectedcityLevelModels, selectedmidLevelModels, selectedterminalLevelModels);


//        selectedCidModel = LocationFilterUtils.globalCidLevel.get(m);

    }
}

