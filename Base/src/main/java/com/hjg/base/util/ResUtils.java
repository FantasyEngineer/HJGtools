package com.hjg.base.util;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;


public class ResUtils {

    /**
     * 获取res中字符串
     *
     * @param id
     * @return
     */
    public static String getString(@StringRes int id) {
        return HJGUtils.getContext().getResources().getString(id);
    }

    /**
     * 获取res中颜色
     *
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id) {
        return HJGUtils.getContext().getResources().getColor(id);
    }


    /**
     * 获取res中的drawable文件
     *
     * @param id
     * @return
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return HJGUtils.getContext().getResources().getDrawable(id);
    }
}
