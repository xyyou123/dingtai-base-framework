package com.lnr.android.base.framework.uitl;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import com.lnr.android.base.framework.R;

/**
 * author:lnr
 * date:2018/10/12
 */
public class StringUtil {

    public static String[] spit(String str, String regex) {
        if(str == null || str.length() == 0) return new String[0];
        return str.split(regex);
    }

    public static SpannableStringBuilder setThemeSpan(String span, String str) {
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        int start = str.indexOf(span);
        builder.setSpan(new ForegroundColorSpan(ContextUtil.getColor(R.color.theme)), start, start + span.length(),
                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    public static String formatPhone(String phone) {
        if(phone == null || phone.length() < 7) return phone;
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}
