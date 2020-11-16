package com.hjg.base.util;

import android.util.Log;

public class L {

    public static void d(Object o) {
//        LogUtils.d(o);
        Log.d("LogUtils", o.toString() + "");
    }

    public static void e(Object o) {
//        LogUtils.e(o);
    }

    public static void json(String o) {
//        LogUtils.json(o);
    }

    public static void xml(String xml) {
//        LogUtils.xml(xml);
    }

}
