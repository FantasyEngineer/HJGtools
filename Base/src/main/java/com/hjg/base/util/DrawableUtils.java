package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.annotation.ColorInt;

public class DrawableUtils {


    /**
     * 获取四周都是圆角的gradientDrawable
     *
     * @param solidColor  填充颜色
     * @param strokeWidth 线条宽度
     * @param strokeColor 线条颜色
     * @param radius      弧度
     * @return
     */
    public static GradientDrawable getCornerRaduisDrawable(int solidColor, int strokeWidth, int strokeColor, int radius) {
        return getCornerRaduisDrawable(solidColor, strokeWidth, strokeColor, new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
    }

    /**
     * 可控四周圆角的gradientDrawable
     *
     * @param solidColor  填充颜色
     * @param strokeWidth 线条宽度
     * @param strokeColor 线条颜色
     * @param radiusArray 四周的弧度，左上右下
     * @return
     */
    public static GradientDrawable getCornerRaduisDrawable(int solidColor, int strokeWidth, int strokeColor, float[] radiusArray) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(solidColor);
        drawable.setStroke(strokeWidth, strokeColor);
        drawable.setCornerRadii(radiusArray);
        return drawable;
    }


    /**
     * 获取线的gradientDrawable
     * 设置的高度必须是strokeWidth的2倍。不然无法展示
     *
     * @param strokeWidth
     * @param strokeColor
     * @return
     */
    @SuppressLint("WrongConstant")
    public static GradientDrawable getDividerLine(int strokeWidth, int strokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setGradientType(GradientDrawable.LINE);
        drawable.setColor(strokeColor);
        drawable.setStroke(strokeWidth, strokeColor);
        return drawable;
    }


}
