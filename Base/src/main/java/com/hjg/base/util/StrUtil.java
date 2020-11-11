package com.hjg.base.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;

import com.hjg.base.base.HJGBaseApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 字符串处理
 *
 * @creatTime 2020年10月22
 * @autor 侯继国
 */
public class StrUtil {

    /**
     * 描述：判断一个字符串是否为null或空值.
     *
     * @param str 指定的字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str);
    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(CharSequence str) {
        return isNotEmpty(str.toString());
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int getLenght(String str) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        if (!isEmpty(str)) {
            //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                //获取一个字符
                String temp = str.substring(i, i + 1);
                //判断是否为中文字符
                if (temp.matches(chinese)) {
                    //中文字符长度为2
                    valueLength += 2;
                } else {
                    //其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int currentIndex = 0;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        //获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            //获取一个字符
            String temp = str.substring(i, i + 1);
            //判断是否为中文字符
            if (temp.matches(chinese)) {
                //中文字符长度为2
                valueLength += 2;
            } else {
                //其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 将string.xml中的数据转换成String字符串
     *
     * @param resId
     * @return
     */
    public static String strId2String(@StringRes int resId) {
        return HJGBaseApplication.getApp().getString(resId);
    }


    /**
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            //最后一个\n删除
            if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：标准化日期时间类型的数据，不足两位的补0.
     *
     * @param dateTime 预格式的时间字符串，如:2012-3-2 12:2:20
     * @return String 格式化好的时间字符串，如:2012-03-20 12:02:20
     */
    public static String dateTimeFormat(String dateTime) {
        StringBuilder sb = new StringBuilder();
        try {
            if (isEmpty(dateTime)) {
                return null;
            }
            String[] dateAndTime = dateTime.split(" ");
            if (dateAndTime.length > 0) {
                for (String str : dateAndTime) {
                    if (str.indexOf("-") != -1) {
                        String[] date = str.split("-");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append("-");
                            }
                        }
                    } else if (str.indexOf(":") != -1) {
                        sb.append(" ");
                        String[] date = str.split(":");
                        for (int i = 0; i < date.length; i++) {
                            String str1 = date[i];
                            sb.append(strFormat2(str1));
                            if (i < date.length - 1) {
                                sb.append(":");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    /**
     * 描述：不足2个字符的在前面补“0”.
     *
     * @param str 指定的字符串
     * @return 至少2个字符的字符串
     */
    public static String strFormat2(String str) {
        try {
            if (str.length() <= 1) {
                str = "0" + str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int temp = 0;
        StringBuffer sb = new StringBuffer(length);
        char[] ch = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                //size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    //size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24 | Long.valueOf(items[1]) << 16 | Long.valueOf(items[2]) << 8 | Long.valueOf(items[3]);
    }

    public static boolean isColor(String color) {
        if (!isEmpty(color)) {
            return color.matches("^#([a-f]|[A-F]|[0-9]){3}(([a-f]|[A-F]|[0-9]){3})?$");
        }
        return false;
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null || "null".equalsIgnoreCase(s1.trim()) || "".equals(s1.trim())) {
            return (s2 == null || "null".equalsIgnoreCase(s2.trim()) || "".equals(s2.trim()));
        } else if (s2 != null) {
            return s1.trim().equals((s2.trim()));
        } else {
            return false;
        }
    }

    public static boolean isUrl(String url) {
        return url.startsWith("http") || url.startsWith("www");
    }

    /*手机号码脱敏，8位 ，9位，11位*/
    public static String mobileDesensitization(String mobile) {
        if (mobile.length() < 4) {
            return mobile;
        }
        return mobile.substring(0, mobile.length() / 2 - 2) + "****" + mobile.substring(mobile.length() / 2 + 2);
    }

    /**
     *  * 关键字高亮显示
     *  *
     *  * @param text  需要显示的文字
     *  * @param color  高亮颜色
     *  * @param start  头部增加高亮文字个数
     *  * @param end   尾部增加高亮文字个数
     *  * @return 处理完后的结果
     *  
     */
    public static Spannable highlight(String text, int color, int start, int end) {
        Spannable span = new SpannableString(text);
        span.setSpan(new AbsoluteSizeSpan(80), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //粗体
        return span;
    }


    /**
     * 去除时间日期里面的-，或者.，同意成19910202
     *
     * @param date
     * @return
     */
    public static String removeDateLine(String date) {
        if (date.contains("-")) {
            date = date.replace("-", "");
        }
        if (date.contains(".")) {
            date = date.replace(".", "");
        }
        return date;
    }

    /**
     * 格式化成两位小数
     *
     * @param bigDecimal
     * @return
     */
    public static String formatPointTwo(BigDecimal bigDecimal) {
        return new DecimalFormat("#0.00").format(bigDecimal);
    }

    /**
     * 将字符串数字格式化成两位小数
     *
     * @param stringNum
     * @return
     */
    public static String formatPointTwo(String stringNum) {
        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(stringNum);
        } catch (Exception e) {
            bigDecimal = new BigDecimal("0");
        }
        return formatPointTwo(bigDecimal);
    }

    /**
     * 删除字符串结尾的逗号（英文）
     *
     * @param str
     * @return
     */
    public static String deleteEndComma(String str) {
        return deleteEndSymbol(str, ",");
    }

    /**
     * 删除字符串结尾的字符
     *
     * @param str
     * @return
     */
    public static String deleteEndSymbol(String str, String symbol) {
        if (str.endsWith(symbol)) {
            str = str.toString().substring(0, str.toString().length() - 1);
        }
        return str;
    }


    /**
     * 每隔几个字符加一个换行
     *
     * @param str
     * @return
     */
    public static String addBlank(String str) {
        if (StrUtil.isEmpty(str)) {
            return str;
        }
        StringBuffer s = new StringBuffer(str);
        for (int index = 0; index < s.length(); index++) {
            if (index % 3 == 0) {
                s.insert(index, "——");
            }
        }
        return str;
    }


}
