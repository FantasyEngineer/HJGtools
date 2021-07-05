package com.hjg.base.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 数组处理工具类
 */
public class ArrayUtils {

    public static String[] newArrayString(int length) {
        return new String[length];
    }

    /**
     * 将数组格式化以"，"为间隔的字符串
     *
     * @param array
     * @return
     */
    public static String formatArray2StringWithComma(String[] array) {
        if (array == null || array.length == 0) {
            return "";
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (String bean : array) {
                stringBuffer.append(bean).append(",");
            }
            return StrUtil.deleteEndComma(stringBuffer.toString());
        }
    }


    /**
     * 将数组变成List
     *
     * @param array
     * @return
     */
    public static List array2List(String[] array) {
        return new ArrayList(Arrays.asList(array));
    }

}
