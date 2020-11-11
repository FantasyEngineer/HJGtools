package com.hjg.base.util;

import java.util.Calendar;
import java.util.Date;

public class CalenderUtils {

    /**
     * 将yyyyMMdd转换成Calender
     *
     * @param text
     * @return
     */
    public static Calendar formatyyyyMMdd2Calender(String text) {
        Calendar calendar = Calendar.getInstance();
        if (StrUtil.isEmpty(text)) {
            text = DataUtils.nFormatNOline.format(new Date());
        }
        try {
            Date date = DataUtils.nFormatNOline.parse(text);
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            return calendar;
        }
    }
}
