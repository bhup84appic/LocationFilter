/*
 *
 *  Created by Pooran Kharol on 30/1/21 1:56 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 30/1/21 12:59 PM
 *
 */

package com.aim.locationfilter.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Keep;

import com.aim.locationfilter.R;

import org.json.JSONException;
import org.json.JSONObject;
@Keep
public class Utils {
    public static void avoidDoubleClicks(final View view) {
        final long DELAY_IN_MS = 900;
        if (!view.isClickable()) {
            return;
        }
        view.setClickable(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setClickable(true);
            }
        }, DELAY_IN_MS);
    }

    public static boolean isJSONValid(String s) {
        try {
            new JSONObject(s);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }

    public static String getMonthTextName(Context context, String monthName) {

        if (monthName.contains("Jan")) {
            monthName = monthName.replace("Jan", context.getResources().getString(R.string.month_january));
        }

        if (monthName.contains("Feb")) {
            monthName = monthName.replace("Feb", context.getResources().getString(R.string.month_february));
        }

        if (monthName.contains("Mar")) {
            monthName = monthName.replace("Mar", context.getResources().getString(R.string.month_march));
        }

        if (monthName.contains("Apr")) {
            monthName = monthName.replace("Apr", context.getResources().getString(R.string.month_april));
        }

        if (monthName.contains("May")) {
            monthName = monthName.replace("May", context.getResources().getString(R.string.month_may));
        }

        if (monthName.contains("Jun")) {
            monthName = monthName.replace("Jun", context.getResources().getString(R.string.month_june));
        }

        if (monthName.contains("Jul")) {
            monthName = monthName.replace("Jul", context.getResources().getString(R.string.month_july));
        }

        if (monthName.contains("Aug")) {
            monthName = monthName.replace("Aug", context.getResources().getString(R.string.month_august));
        }

        if (monthName.contains("Sept")) {
            monthName = monthName.replace("Sept", context.getResources().getString(R.string.month_september));
        }

        if (monthName.contains("Oct")) {
            monthName = monthName.replace("Oct", context.getResources().getString(R.string.month_october));
        }

        if (monthName.contains("Nov")) {
            monthName = monthName.replace("Nov", context.getResources().getString(R.string.month_november));
        }

        if (monthName.contains("Dec")) {
            monthName = monthName.replace("Dec", context.getResources().getString(R.string.month_december));
        }

        return monthName;
    }

    public static boolean isMosambee() {
        boolean ismosambee = false;
        try {
            String model = Build.MODEL;

            ismosambee = model.equals("Mosambee");
            if (!ismosambee) {
                ismosambee = model.toUpperCase().contains("SPPF");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ismosambee = false;
        }
        return ismosambee;
    }

    public static void popUpDialog(final Activity activity, final String flag,
                                   final String s) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.popup_dialog);
        dialog.setCancelable(false);
        TextView text = dialog.findViewById(R.id.tv_Message);
        text.setText(s);

        ImageView no_internet_image = dialog.findViewById(R.id.no_internet_image);
        if (!TextUtils.isEmpty(s) && (s.equalsIgnoreCase(activity.getResources().getString(R.string.no_internet)) || s.equalsIgnoreCase(activity.getResources().getString(R.string.no_internet_connection_n_please_try_again_later)))) {
            no_internet_image.setVisibility(View.VISIBLE);
        } else {
            no_internet_image.setVisibility(View.GONE);
        }

        TextView tv_Ok = dialog.findViewById(R.id.tv_Ok);
        tv_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        if (!TextUtils.isEmpty(s)) {
            try {
                if (!activity.isFinishing()) {
                    dialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawablesRelative()) {
            if (drawable != null) {
//                LoggerUtils.E("FUNNEL_COLOR_DEBUG", "color applied");
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    public static void checkAndSetDot(CharSequence s, EditText edtvamt) {
        if (!s.toString().contains(".")) {
            String input = s.toString();     //input string
            String beforeDec = "";
            String lastTwoDigits = "";

            if (input.length() > 2) {
                lastTwoDigits = input.substring(input.length() - 2);
                beforeDec = input.substring(0, input.length() - 2);

                edtvamt.setText(beforeDec + "." + lastTwoDigits);
                edtvamt.setSelection(edtvamt.getText().toString().length());
            } else {
                if (input.length() == 2) {
                    edtvamt.setText("0." + input);
                    edtvamt.setSelection(edtvamt.getText().toString().length());
                } else if (input.length() == 1) {
                    edtvamt.setText("0.0" + input);
                    edtvamt.setSelection(edtvamt.getText().toString().length());
                } else {
                    edtvamt.setText("0.00");
                    edtvamt.setSelection(edtvamt.getText().toString().length());
                }

            }
        }
    }


}
