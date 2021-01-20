package com.hjg.base.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class ArrayListUtils<T> {

    public static ArrayList newArrayList() {
        return new ArrayList();
    }


    public static ArrayList newArrayList(String... data) {
        ArrayList list = new ArrayList();
        for (String s : data) {
            list.add(s);
        }
        return list;
    }

    public static LinkedList newLinkList() {
        return new LinkedList();
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


    /**
     * 删除list中特定条件的元素,具体数据类型可以参照学习
     *
     * @param list
     * @return
     */
    public static List<String> deleteSpecialElement(List<String> list) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.removeIf(new Predicate<String>() {
                @Override
                public boolean test(String o) {
                    if (o.equals("1"))
                        return true;
                    return false;
                }

            });
        } else {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String a = iterator.next();
                // if(删除元素条件)
                if (a.equals("1"))
                    iterator.remove();
            }
        }
        return list;
    }


    /**
     * 将list转为数组
     *
     * @param list
     * @return
     */
    public static String[] list2Array(Collection<String> list) {
        String[] strings = new String[list.size()];
        list.toArray(strings);
        return strings;

    }

}
