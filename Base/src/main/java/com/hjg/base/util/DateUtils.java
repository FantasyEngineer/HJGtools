package com.hjg.base.util;

import androidx.annotation.Nullable;

import com.hjg.base.util.log.androidlog.L;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static Calendar calendar;

    static {
        calendar = Calendar.getInstance();//获取日历
    }


    public static SimpleDateFormat nFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat nFormatNOline = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
    public static SimpleDateFormat nFormatDetail = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    /**
     * 将yyyyMMdd转换成Calender
     *
     * @param text
     * @return
     */
    public static Calendar formatyyyyMMdd2Calender(String text) {
        Calendar calendar = Calendar.getInstance();
        if (StrUtil.isEmpty(text)) {
            text = DateUtils.nFormatNOline.format(new Date());
        }
        try {
            Date date = DateUtils.nFormatNOline.parse(text);
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            return calendar;
        }
    }


    /**
     * 获取当前年
     *
     * @return
     */
    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 获取当前月
     *
     * @return
     */
    public static int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return
     */
    public static int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public static boolean isLeap(int year) {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    /**
     * 获取某年某月的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysNum(int year, int month) {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;

    }

    /**
     * 根据日期获得星期
     *
     * @param time
     * @return
     */
    public static String getWeekOfDate(long time) {
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    //返回当月星期天数
    public static int getSundays(int year, int month) {
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        //从第一天开始
        int day;
        for (day = 1; day <= getDaysNum(year, month); day++) {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")) {
                sundays++;
            }
        }
        return sundays;
    }

    public enum Type {
        TYPE_LINE,
        TYPE_NO_LINE,
        TYPE_DETAIL;
    }


    /**
     * 获取现在的时间字符串（详细）
     *
     * @return
     */
    public static String getNowDataString(Type type) {
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
     * 获取当前具体的时间 年月日时分秒
     *
     * @return
     */
    public static String getNowDataString() {
        return getNowDataString(Type.TYPE_DETAIL);
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


    /**
     * 获取两个日期的月数差
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public long getDifferMonth(Date fromDate, Date toDate) {
        Calendar fromDateCal = Calendar.getInstance();
        Calendar toDateCal = Calendar.getInstance();
        fromDateCal.setTime(fromDate);
        toDateCal.setTime(toDate);

        int fromYear = fromDateCal.get(Calendar.YEAR);
        int toYear = toDateCal.get((Calendar.YEAR));
        if (fromYear == toYear) {
            return Math.abs(fromDateCal.get(Calendar.MONTH) - toDateCal.get(Calendar.MONTH));
        } else {
            int fromMonth = 12 - (fromDateCal.get(Calendar.MONTH) + 1);
            int toMonth = toDateCal.get(Calendar.MONTH) + 1;
            return Math.abs(toYear - fromYear - 1) * 12 + fromMonth + toMonth;
        }
    }


    /**
     * 获取两个时间的天数差
     */
    public int getDifferDay(Date fromDate, Date toDate) {
        return (int) ((fromDate.getTime() - toDate.getTime()) / (1000 * 3600 * 24));
    }


    /**
     * 将字符串转换成date
     * 要求时间格式是1900-00-00或者19000101两种格式
     *
     * @return
     */
    public static Date string2Date(String dateString) {
        try {
            if (isDateyyyyMMdd(dateString)) {
                return nFormatNOline.parse(dateString);
            }
            if (isDateyyyyMMddline(dateString)) {
                return nFormat.parse(dateString);
            }
        } catch (ParseException e) {
            L.d(e.getMessage() + "");
        }
        return new Date();
    }

    /**
     * 将long转换成date
     *
     * @param l
     * @return
     */
    public static Date long2Date(long l) {
        return new Date(l);
    }

    /**
     * 将data转换成long（毫秒）
     */
    public static long Date2long(Date date) {
        return date.getTime();
    }
}

