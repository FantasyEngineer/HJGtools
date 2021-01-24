package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.hjg.base.util.log.androidlog.L;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    /**
     * 格式化时间到年月日没有横线
     */
    public static final String dateFormatYMDNoLine = "yyyyMMdd";

    /**
     * 时间日期格式化到年月日时分秒.
     */
    public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHMNew = "yyyy.MM.dd(HH:mm)";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM_New = "yyyy.MM.dd HH:mm";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMD = "yyyy-MM-dd";

    /**
     * 时间日期格式化到年月日.
     */
    public static final String dateFormatYMDNew = "yyyy.MM.dd";

    /**
     * 时间日期格式化到年月.
     */
    public static final String dateFormatYM = "yyyy-MM";

    /**
     * 时间日期格式化到年月日时分.
     */
    public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 时间日期格式化到月日.
     */
    public static final String dateFormatMD = "MM/dd";

    /**
     * 时分秒.
     */
    public static final String dateFormatHMS = "HH:mm:ss";

    /**
     * 时分.
     */
    public static final String dateFormatHM = "HH:mm";

    /**
     * 上午.
     */
    public static final String AM = "AM";

    /**
     * 下午.
     */
    public static final String PM = "PM";

    public static final int MSEC = 1;
    public static final int SEC  = 1000;
    public static final int MIN  = 60000;
    public static final int HOUR = 3600000;
    public static final int DAY  = 86400000;
    public static final long MONTH  = 2592000000L;
    public static final long YEAR  = 31104000000L;



    /**
     * 描述：获取milliseconds表示的日期时间的字符串.
     *
     * @param milliseconds the milliseconds
     * @param format       格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 日期时间字符串
     */
    public static String getStringByFormat(long milliseconds, String format) {
        String thisDateTime = null;
        try {
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            thisDateTime = mSimpleDateFormat.format(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thisDateTime;
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 描述：将时间String转换成Date类型
     *
     * @param dateString 日期字符串
     * @param dateFormat 对应的日期字符串格式
     * @return date
     */
    public static Date getDatebyFormat(String dateString, String dateFormat) {
        try {
            return new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (ParseException e) {
            L.d(e.getMessage() + "");
        }
        return new Date();
    }

    /**
     * 描述：获取指定日期时间的字符串(可偏移).
     *
     * @param strDate       String形式的日期时间
     * @param format        格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @param calendarField Calendar属性，对应offset的值，
     *                      如(Calendar.DATE,表示+offset天,Calendar.HOUR_OF_DAY,表示＋offset小时)
     * @param offset        偏移(值大于0,表示+,值小于0,表示－)
     * @return String String类型的日期时间
     */
    public static String getStringByOffset(String strDate, String format,
                                           int calendarField, int offset) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(mSimpleDateFormat.parse(strDate));
            c.add(calendarField, offset);
            mDateTime = mSimpleDateFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：Date类型转化为String类型(可偏移).
     *
     * @param date          the date
     * @param format        the format
     * @param calendarField the calendar field
     * @param offset        the offset
     * @return String String类型日期时间
     */
    public static String getStringByOffset(Date date, String format,
                                           int calendarField, int offset) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            c.setTime(date);
            c.add(calendarField, offset);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 将long转换成date
     *
     * @param l
     * @return
     */
    public static Date getLong2Date(long l) {
        return new Date(l);
    }

    /**
     * 将data转换成long（毫秒）
     */
    public static long getDate2long(Date date) {
        return date.getTime();
    }


    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    public static long getCurrentMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 以unit为单位的时间长度转毫秒时间戳
     *
     * @param timeSpan 毫秒时间戳
     * @param unit     单位类型
     *                 <ul>
     *                 <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *                 <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *                 <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *                 <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *                 <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *                 </ul>
     * @return 毫秒时间戳
     */
    public static long timeSpan2Millis(long timeSpan, ConstUtils.TimeUnit unit) {
        switch (unit) {
            default:
            case MSEC:
                return timeSpan;
            case SEC:
                return timeSpan * ConstUtils.SEC;
            case MIN:
                return timeSpan * ConstUtils.MIN;
            case HOUR:
                return timeSpan * ConstUtils.HOUR;
            case DAY:
                return timeSpan * ConstUtils.DAY;
        }
    }

    /**
     * 毫秒时间戳转以unit为单位的时间长度
     *
     * @param millis 毫秒时间戳
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link ConstUtils.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link ConstUtils.TimeUnit#SEC }: 秒</li>
     *               <li>{@link ConstUtils.TimeUnit#MIN }: 分</li>
     *               <li>{@link ConstUtils.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link ConstUtils.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @return 以unit为单位的时间长度
     */
    public static long millis2TimeSpan(long millis, ConstUtils.TimeUnit unit) {
        switch (unit) {
            default:
            case MSEC:
                return millis;
            case SEC:
                return millis / ConstUtils.SEC;
            case MIN:
                return millis / ConstUtils.MIN;
            case HOUR:
                return millis / ConstUtils.HOUR;
            case DAY:
                return millis / ConstUtils.DAY;
        }
    }

    /**
     * 毫秒时间戳转合适时间长度
     *
     * @param millis    毫秒时间戳
     *                  <p>小于等于0，返回null</p>
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适时间长度  18583天5小时37分钟52秒765毫秒
     */
    @SuppressLint("DefaultLocale")
    public static String millis2FitTimeSpan(long millis, int precision) {
        if (millis <= 0 || precision <= 0) return null;
        StringBuilder sb = new StringBuilder();
        String[] units = {"天", "小时", "分钟", "秒", "毫秒"};
        int[] unitLen = {86400000, 3600000, 60000, 1000, 1};
        precision = Math.min(precision, 5);
        for (int i = 0; i < precision; i++) {
            if (millis >= unitLen[i]) {
                long mode = millis / unitLen[i];
                millis -= mode * unitLen[i];
                sb.append(mode).append(units[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 将特定规则的时间字符串转换成Calender
     *
     * @param text       时间字符串
     * @param dateFormat 转换格式
     * @return 日历
     */
    public static Calendar getyyyyMMdd2Calender(String text, String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat(dateFormat).parse(text);
            calendar.setTime(date);
            return calendar;
        } catch (Exception e) {
            return calendar;
        }
    }


    /**
     * 获取当前年
     *
     * @return 当前年
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }


    /**
     * 获取当前月
     *
     * @return 当前月
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日
     *
     * @return 当前日
     */
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
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
     * @return 某年某月的天数
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

    /**
     * 判断时间类型是否是yyyy-MM-dd
     */
    public static boolean isDateyyyy_MM_dd(String st) {
        try {
            Date parse = new SimpleDateFormat(dateFormatYMD).parse(st);
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
            Date parse = new SimpleDateFormat(dateFormatYMDNoLine).parse(st);
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
            Date data = new SimpleDateFormat(dateFormatYMD).parse(day);
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
    public static String getYMd2y_M_d(String date) {
        if (StrUtil.isEmpty(date)) {
            return "";
        }
        //如果数据是正常的，直接返回
        if (isDateyyyy_MM_dd(date)) {
            return date;
        }

        Date parse = null;
        String s = null;
        try {
            s = new SimpleDateFormat(dateFormatYMD).format(new SimpleDateFormat(dateFormatYMDNoLine).parse(date));
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
    public static long getDifferMonth(Date fromDate, Date toDate) {
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
     * 描述：计算两个日期所差的天数.
     *
     * @param milliseconds1 the milliseconds1
     * @param milliseconds2 the milliseconds2
     * @return int 所差的天数
     */
    public static int getOffectDay(long milliseconds1, long milliseconds2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(milliseconds1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(milliseconds2);
        // 先判断是否同年
        int y1 = calendar1.get(Calendar.YEAR);
        int y2 = calendar2.get(Calendar.YEAR);
        int d1 = calendar1.get(Calendar.DAY_OF_YEAR);
        int d2 = calendar2.get(Calendar.DAY_OF_YEAR);
        int maxDays = 0;
        int day = 0;
        if (y1 - y2 > 0) {
            maxDays = calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 + maxDays;
        } else if (y1 - y2 < 0) {
            maxDays = calendar1.getActualMaximum(Calendar.DAY_OF_YEAR);
            day = d1 - d2 - maxDays;
        } else {
            day = d1 - d2;
        }
        return day;
    }

    /**
     * 描述：计算两个日期所差的小时数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的小时数
     */
    public static int getOffectHour(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int h1 = calendar1.get(Calendar.HOUR_OF_DAY);
        int h2 = calendar2.get(Calendar.HOUR_OF_DAY);
        int h = 0;
        int day = getOffectDay(date1, date2);
        h = h1 - h2 + day * 24;
        return h;
    }

    /**
     * 描述：计算两个日期所差的分钟数.
     *
     * @param date1 第一个时间的毫秒表示
     * @param date2 第二个时间的毫秒表示
     * @return int 所差的分钟数
     */
    public static int getOffectMinutes(long date1, long date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(date2);
        int m1 = calendar1.get(Calendar.MINUTE);
        int m2 = calendar2.get(Calendar.MINUTE);
        int h = getOffectHour(date1, date2);
        int m = 0;
        m = m1 - m2 + h * 60;
        return m;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
                    dateFormatYMDHMS);
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }


    /**
     * 描述：根据时间返回格式化后的时间的描述. 小于1小时显示多少分钟前 大于1小时显示今天＋实际日期，大于今天全部显示实际时间
     *
     * @param strDate   the str date
     * @param outFormat the out format
     * @return the string
     */
    public static String formatDateStr2Desc(String strDate, String outFormat) {

        DateFormat df = new SimpleDateFormat(dateFormatYMDHMS);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c2.setTime(df.parse(strDate));
            c1.setTime(new Date());
            int d = getOffectDay(c1.getTimeInMillis(), c2.getTimeInMillis());
            if (d == 0) {
                int h = getOffectHour(c1.getTimeInMillis(),
                        c2.getTimeInMillis());
                if (h > 0) {
                    return "今天" + getStringByFormat(strDate, dateFormatHM);
                    // return h + "小时前";
                } else if (h < 0) {
                    // return Math.abs(h) + "小时后";
                } else if (h == 0) {
                    int m = getOffectMinutes(c1.getTimeInMillis(),
                            c2.getTimeInMillis());
                    if (m > 0) {
                        return m + "分钟前";
                    } else if (m < 0) {
                        // return Math.abs(m) + "分钟后";
                    } else {
                        return "刚刚";
                    }
                }

            } else if (d > 0) {
                if (d == 1) {
                    // return "昨天"+getStringByFormat(strDate,outFormat);
                } else if (d == 2) {
                    // return "前天"+getStringByFormat(strDate,outFormat);
                }
            } else if (d < 0) {
                if (d == -1) {
                    // return "明天"+getStringByFormat(strDate,outFormat);
                } else if (d == -2) {
                    // return "后天"+getStringByFormat(strDate,outFormat);
                } else {
                    // return Math.abs(d) +
                    // "天后"+getStringByFormat(strDate,outFormat);
                }
            }

            String out = getStringByFormat(strDate, outFormat);
            if (!TextUtils.isEmpty(out)) {
                return out;
            }
        } catch (Exception e) {
        }

        return strDate;
    }


    /**
     * 描述：获取本周一.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.MONDAY);
    }

    /**
     * 描述：获取本周日.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfWeek(String format) {
        return getDayOfWeek(format, Calendar.SUNDAY);
    }

    /**
     * 描述：获取本周的某一天.
     *
     * @param format        the format
     * @param calendarField the calendar field
     * @return String String类型日期时间
     */
    private static String getDayOfWeek(String format, int calendarField) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            int week = c.get(Calendar.DAY_OF_WEEK);
            if (week == calendarField) {
                strDate = mSimpleDateFormat.format(c.getTime());
            } else {
                int offectDay = calendarField - week;
                if (calendarField == Calendar.SUNDAY) {
                    offectDay = 7 - Math.abs(offectDay);
                }
                c.add(Calendar.DATE, offectDay);
                strDate = mSimpleDateFormat.format(c.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取本月第一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getFirstDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的第一天
            c.set(GregorianCalendar.DAY_OF_MONTH, 1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;

    }

    /**
     * 描述：获取本月最后一天.
     *
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getLastDayOfMonth(String format) {
        String strDate = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
            // 当前月的最后一天
            c.set(Calendar.DATE, 1);
            c.roll(Calendar.DATE, -1);
            strDate = mSimpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }


    /**
     * 计算前推几天未来
     *
     * @param days
     * @return
     */
    public static String getDate(int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(calendar.getTime());
    }

    /**
     * 计算后推几天过去
     *
     * @param days
     * @return
     */
    public static String getDateG(int days, String DateFormat) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        SimpleDateFormat sf = new SimpleDateFormat(DateFormat);
        return sf.format(calendar.getTime());
    }


    /**
     * 转换时间格式
     *
     * @param timeString      时间字符串
     * @param inFormatString  输入格式
     * @param outFormatString 输出格式
     * @return
     */
    public static String exchangeFormat(String timeString, String inFormatString, String outFormatString) {
        String str = null;
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(inFormatString);
            Calendar c = new GregorianCalendar();
            c.setTime(inFormat.parse(timeString));
            SimpleDateFormat outFormat = new SimpleDateFormat(outFormatString);
            str = outFormat.format(c.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


}

