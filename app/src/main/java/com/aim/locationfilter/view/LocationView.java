//package com.aim.locationfilter.view;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Typeface;
//import android.os.Build;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.widget.LinearLayout;
//
//import androidx.annotation.Nullable;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.ContextCompat;
//import androidx.core.content.res.ResourcesCompat;
//import androidx.databinding.DataBindingUtil;
//
//import com.aim.locationfilter.R;
//import com.aim.locationfilter.databinding.LocationViewLayoutBinding;
//import com.aim.locationfilter.fragment.BTM_SelectLocationFragment;
//import com.aim.locationfilter.interfaces.LocationFilterApplyCallBack;
//import com.aim.locationfilter.model.CidLevelModel;
//import com.aim.locationfilter.model.CityLevelModel;
//import com.aim.locationfilter.model.DbaLevelModel;
//import com.aim.locationfilter.model.MidLevelModel;
//import com.aim.locationfilter.model.TerminalLevelModel;
//import com.mintoak.corelib.LoggerUtils;
//import com.mintoak.corelib.builder.RequestParameters;
////import com.mintoak.corelib.LoggerUtils;
////import com.mintoak.corelib.apiutil.ModuleConfiguration;
////import com.mintoak.corelib.builder.RequestParameters;
////import com.mintoak.corelib.enums.ProjectType;
////import com.mintoak.corelib.enums.Type;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.internal.Util;
//
//public class LocationView extends LinearLayout {
//    LocationViewLayoutBinding binding;
//    String title;
//    int titleColor;
//    float titleSize;
//    Typeface titleFont;
//    private RequestParameters requestParameters;
//    private Context mcontext;
//    public static final int TYPE_ONE_APP_BIZVIEW = 6;
//
////    public LocationView(Context context) {
////        super(context);
////
////    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public LocationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        mcontext = context;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        binding = DataBindingUtil.inflate(inflater, R.layout.location_view_layout, this, true);
//        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSalesCard, 0, 0);
//
//        try {
//            handleAttributes(typedArray);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            typedArray.recycle();
//        }
//    }
//
//    ;
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void handleAttributes(TypedArray typedArray) {
//
//        title = typedArray.getString(R.styleable.CustomSalesCard_title);
//        titleColor = typedArray.getColor(R.styleable.CustomSalesCard_titleColor, ContextCompat.getColor(getContext(), R.color.white));
//        titleSize = getRawSizeValue(typedArray, R.styleable.CustomSalesCard_titleSize, 18);
//        if (Build.VERSION.SDK_INT >= 26) {
//            titleFont = typedArray.getFont(R.styleable.CustomSalesCard_titleFont);
//        } else {
//            int fontId = typedArray.getResourceId(R.styleable.CustomSalesCard_titleFont, -1);
//            titleFont = ResourcesCompat.getFont(getContext(), fontId);
//        }
//
//        init();
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//        if (title != null) {
//            binding.tidTv.setText(title);
//        }
//    }
//
//    public void setTitleColor(int titleColor) {
//        this.titleColor = titleColor;
//        /*if (titleColor != 0) {
//            tv_sale_title.setTextColor(titleColor);
//        }*/
//    }
//
//    public void setTitleSize(float titleSize) {
//        this.titleSize = titleSize;
//        /*if (titleSize != 0) {
//            tv_sale_title.setTextSize(titleSize);
//        }*/
//    }
//
//    public Typeface getTitleFont() {
//        return titleFont;
//
//    }
//
//    public void setTitleFont(Typeface titleFont) {
//        this.titleFont = titleFont;
//        /*if (titleFont != null) {
//            tv_sale_title.setTypeface(titleFont);
//        }*/
//    }
//
//    private void init() {
//
////        mAppPreferences = new AppPreferences();
////        moduleConfiguration = ModuleConfiguration.getInstance();
////
////        setCalendarData(mAppPreferences.getPreferences(mcontext, Constants.FROM_DATE), mAppPreferences.getPreferences(mcontext, Constants.TO_DATE));
//
//        //Sales
//        setTitle(title);
//        setTitleColor(titleColor);
//        setTitleSize(titleSize);
//        setTitleFont(titleFont);
//
//
//        binding.tidTv.setOnClickListener(v -> {
//            BTM_SelectLocationFragment btm_selectLocationFragment = new BTM_SelectLocationFragment(new LocationFilterApplyCallBack() {
//
//                @Override
//                public void allSelectedData(CidLevelModel cidLevelModel, List<DbaLevelModel> dbaLevelModelList, List<CityLevelModel> cityLevelModelList, List<MidLevelModel> midLevelModelList, List<TerminalLevelModel> terminalLevelModelList) {
//
//                    if (com.mintoak.corelib.Util.isNetworkConnected(mcontext)) {
////                        showInternetError();
//                        onMidCidClicked(midLevelModelList, cidLevelModel);
//                        setCidMidName(cidLevelModel.getCompanyName(), midLevelModelList.size() + "");
//                        LoggerUtils.E("allSelectedData: ", "Size of dba : " + dbaLevelModelList.size());
//                    } else {
////                        showInternetError();
//                        //Utils.popUpDialog(MainActivity.this, "", getString(R.string.no_internet_connection_n_please_try_again_later));
//                    }
//
////                            onmidcidselected.onMidCidClicked(midLevelModelList, cidLevelModel);
////                            setCidMidName(cidLevelModel.getCompanyName(), midLevelModelList.size() + "");
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + dbaLevelModelList.size());
//
//                    try {
//                        LoggerUtils.E("LocationFilterDebug", "cidLevelModel:" + cidLevelModel.getCid());
//                        LoggerUtils.E("LocationFilterDebug", "cidLevelModel.getDbaLevelModels:" + cidLevelModel.getDbaLevelModels().size());
//                        LoggerUtils.E("LocationFilterDebug", "midLevelModelList:" + midLevelModelList.size());
//                        for (int i = 0; i < midLevelModelList.size(); i++) {
//                            MidLevelModel model = midLevelModelList.get(i);
//                            LoggerUtils.E("LocationFilterDebug", "mid:" + model.getMid() + " ===========>tidList:" + model.getTerminalLevelModels().size());
//                            for (int j = 0; j < model.getTerminalLevelModels().size(); j++) {
//                                TerminalLevelModel model1 = model.getTerminalLevelModels().get(j);
//                                LoggerUtils.E("LocationFilterDebug", "tid:" + model1.getTid());
//                            }
//
//                        }
//
//                    } catch (Exception e) {
//                        LoggerUtils.E("LocationFilterDebug", "Exception:" + e.toString());
//                    }
//                    /////////////////////////////// end debug purpose///////////////////////////////////
//
//                    ArrayList<String> stringArrayList = new ArrayList<>();
//                    for (int i = 0; i < midLevelModelList.size(); i++) {
//                        stringArrayList.add(midLevelModelList.get(i).getMid());
//                    }
//
///*
//                    requestParameters = null;
//                    requestParameters = new RequestParameters.Builder(mcontext, 6)
//                            .setBaseUrl("https://dev-4.mintoak.com/").setCid(cidLevelModel.getCid()).setMidList(stringArrayList).setType(Type.TYPE_MERC).build();
//                    ModuleConfiguration.getInstance().setRequestParameters(requestParameters);
//                    com.mintoak.corelib.Util.storeRequestValue(mcontext, requestParameters);
//*/
//
//                }
//
//                @Override
//                public void onCidSelected(CidLevelModel cidLevelModel) {
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + cidLevelModel);
//
//                }
//
//                @Override
//                public void onDBASelected(List<DbaLevelModel> dbaLevelModelList) {
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + dbaLevelModelList.size());
//
//                }
//
//                @Override
//                public void onCitySelected(List<CityLevelModel> cityLevelModelList) {
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + cityLevelModelList.size());
//
//                }
//
//                @Override
//                public void onMidSelected(List<MidLevelModel> midLevelModelList) {
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + midLevelModelList.size());
//
////                            onmidcidselected.onMidCidClicked();
//                }
//
//                @Override
//                public void onTidSelected(List<TerminalLevelModel> terminalLevelModelList) {
//                    LoggerUtils.E("allSelectedData: ", "Size of dba : " + terminalLevelModelList.size());
//
//                }
//            });
//            btm_selectLocationFragment.show(((AppCompatActivity) mcontext).getSupportFragmentManager(), "locationBottomSheet");
//
//        });
//
//    }
//
//    private void onMidCidClicked(List<MidLevelModel> midLevelModelList, CidLevelModel cidLevelModel) {
//        try {
//            LoggerUtils.E("LocationFilterDebug", "cidLevelModel:" + cidLevelModel.getCid());
//            LoggerUtils.E("LocationFilterDebug", "cidLevelModel.getDbaLevelModels:" + cidLevelModel.getDbaLevelModels().size());
//            LoggerUtils.E("LocationFilterDebug", "midLevelModelList:" + midLevelModelList.size());
//            for (int i = 0; i < midLevelModelList.size(); i++) {
//                MidLevelModel model = midLevelModelList.get(i);
//                LoggerUtils.E("LocationFilterDebug", "mid:" + model.getMid() + " ===========>tidList:" + model.getTerminalLevelModels().size());
//                for (int j = 0; j < model.getTerminalLevelModels().size(); j++) {
//                    TerminalLevelModel model1 = model.getTerminalLevelModels().get(j);
//                    LoggerUtils.E("LocationFilterDebug", "tid:" + model1.getTid());
//                }
//
//            }
//
//        } catch (Exception e) {
//            LoggerUtils.E("LocationFilterDebug", "Exception:" + e.toString());
//        }
//        /////////////////////////////// end debug purpose///////////////////////////////////
//        ArrayList<String> stringArrayList = new ArrayList<>();
//        for (int i = 0; i < midLevelModelList.size(); i++) {
//            stringArrayList.add(midLevelModelList.get(i).getMid());
//        }
//        setCidMidName(cidLevelModel.getCompanyName(), midLevelModelList.size() + "");
////        requestParameters = new RequestParameters.Builder(mcontext, ProjectType.TYPE_ONE_APP_BIZVIEW)
////                .setBaseUrl("URL_ONE_APP").setCid(cidLevelModel.getCid()).setMidList(stringArrayList).setType(Type.TYPE_MERC).build();
///*
//        requestParameters = new RequestParameters.Builder(mcontext, TYPE_ONE_APP_BIZVIEW)
//                .setBaseUrl("https://dev-4.mintoak.com/").setCid(cidLevelModel.getCid()).setMidList(stringArrayList).setType(Type.TYPE_MERC).build();
//
//        ModuleConfiguration.getInstance().setRequestParameters(requestParameters);
//        com.mintoak.corelib.Util.storeRequestValue(mcontext, requestParameters);
//        callDashboardAPIs();
//*/
//    }
//
//    public void setCidMidName(String cidName, String midSize) {
//        binding.tidTv.setText(cidName + " (" + midSize + ")");
//    }
//
//    private float getRawSizeValue(TypedArray a, int index, float defValue) {
//        TypedValue outValue = new TypedValue();
//        boolean result = a.getValue(index, outValue);
//        if (!result) {
//            return defValue;
//        }
//        return TypedValue.complexToFloat(outValue.data);
//    }
//
//}
