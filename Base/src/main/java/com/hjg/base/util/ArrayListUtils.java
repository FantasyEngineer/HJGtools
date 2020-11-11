package com.hjg.base.util;

import java.util.ArrayList;

public class ArrayListUtils {

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
     * 获取字符串在字符串List中所在的位置
     *
     * @param list
     * @param defaultData
     * @return
     */
    public static int getListPos(ArrayList<String> list, String defaultData) {
        if (StrUtil.isEmpty(defaultData)) {
            defaultData = "";
        }
        if (list == null || list.size() == 0) {
            return 0;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(defaultData)) {
                    return i;
                }
            }
        }
        return 0;
    }

}
