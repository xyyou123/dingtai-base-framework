package com.lnr.android.base.framework.common.picker;

import android.app.Activity;

import com.lnr.android.base.framework.uitl.DimenUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * author:lnr
 * date:2018/10/9
 */
public class Picker {

    public static void pick(Activity activity, DatePicker.OnYearMonthDayPickListener listener) {
        final DatePicker picker = new DatePicker(activity);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(DimenUtil.dp2px(activity, 10));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        picker.setRangeEnd(calendar.get(Calendar.YEAR) + 10, 1, 1);

        picker.setRangeStart(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        picker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(listener);
        picker.show();
    }
}
