package com.hjg.base.util;

import android.content.Context;

import com.hjg.base.R;
import com.hjg.base.util.log.androidlog.L;

public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类,sharepreferenceUtils的name是app的名称
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        P.initP(ResUtils.getString(R.string.app_name));
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }


}
