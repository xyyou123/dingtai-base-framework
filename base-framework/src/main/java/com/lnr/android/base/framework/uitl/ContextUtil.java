package com.lnr.android.base.framework.uitl;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lnr.android.base.framework.Framework;
import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/9/14
 */
public class ContextUtil {

    public static Context getContext() {
        return Framework.getInstance().getApplication();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static int getColor(int id) {
        return getResources().getColor(id);
    }

    public static int getDimen(int id) {
        return getResources().getDimensionPixelOffset(id);
    }

    public static GradientDrawable createShape(int r, int color) {
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(r);
        gd.setStroke(getDimen(R.dimen.dp_1), color);
        return gd;
    }

    public static int parseColor(String color, int def) {
        try {
            return Color.parseColor(color);
        }catch (Exception e) {
            return def;
        }
    }

    public static void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static String getVersionName() {
        try {
            Context context = getContext();
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }
}
