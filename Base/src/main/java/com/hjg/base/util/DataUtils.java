package com.hjg.base.util;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataUtils {

    public static SimpleDateFormat nFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat nFormatNOline = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    public static SimpleDateFormat nFormatDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    //有横线间隔的时间
    public static final String TYPE_LINE = "add transverse line";
    //没有横线间隔的时间
    public static final String TYPE_NO_LINE = "no transverse line";

    /**
     * 获取现在的时间字符串（详细）
     *
     * @return
     */
    public static String getNowDataString(String type) {
        Date now = new Date();
        switch (type) {
            case TYPE_LINE:
                return nFormat.format(now);
            case TYPE_NO_LINE:
                return nFormatNOline.format(now);
            default:
                return nFormatDetail.format(now);
        }
    }

    /**
     * 获取具体的时间字符串
     *
     * @return
     */
    public static String getNowDataString() {
        return getNowDataString("");
    }


    /**
     * 判断时间类型是否是yyyy-MM-dd
     */
    public static boolean isDateyyyyMMddline(String st) {
        try {
            Date parse = nFormat.parse(st);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 判断时间类型是否是yyyyMMdd
     */
    public static boolean isDateyyyyMMdd(String st) {
        try {
            Date parse = nFormatNOline.parse(st);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }


    /**
     * 是否是今天以前
     * 要求day的输入必须是yyyy-MM-dd格式
     */
    public static boolean isAfter(@Nullable String day) {
        if (!isDateyyyyMMdd(day)) {
            throw new IllegalArgumentException("输入参数应该是yyyy-MM-dd格式");
        }

        try {
            Date data = nFormat.parse(day);
            Date now = new Date();
            if (data.after(now)) {//是否超过了今天
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 删除yyyy-HH-dd中的横线
     */
    public static String deleteDataStringLine(String dataString) {
        return dataString.replace("-", "");
    }


    /**
     * 增加时间的-,讲19990909变成1999-09-09
     *
     * @param date
     * @return
     */
    public static String addDateLine(String date) {
        if (StrUtil.isEmpty(date)) {
            return "";
        }
        //如果数据是正常的，直接返回
        if (isDateyyyyMMddline(date)) {
            return date;
        }

        Date parse = null;
        String s = null;
        try {
            s = nFormat.format(nFormatNOline.parse(date));
        } catch (ParseException e) {
            s = "";//异常情况，返回空
        }
        return s;
    }


}

