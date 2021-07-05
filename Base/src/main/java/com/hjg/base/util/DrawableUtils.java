package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
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


    public static Drawable cornerDrawable(final int bgColor, float cornerradius) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadius(cornerradius);
        bg.setColor(bgColor);

        return bg;
    }

    public static Drawable cornerDrawable(final int bgColor, float[] cornerradius) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setColor(bgColor);

        return bg;
    }

    public static Drawable cornerDrawable(final int bgColor, float[] cornerradius, int borderwidth, int bordercolor) {
        final GradientDrawable bg = new GradientDrawable();
        bg.setCornerRadii(cornerradius);
        bg.setStroke(borderwidth, bordercolor);
        bg.setColor(bgColor);

        return bg;
    }

    /**
     * set btn selector with corner drawable for special position
     */
    public static StateListDrawable btnSelector(float radius, int normalColor, int pressColor, int postion) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (postion == 0) {// left btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, 0, 0, radius, radius});
        } else if (postion == 1) {// right btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, 0, 0});
        } else if (postion == -1) {// only one btn
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        } else if (postion == -2) {// for material dialog
            normal = cornerDrawable(normalColor, radius);
            pressed = cornerDrawable(pressColor, radius);
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }

    /**
     * set ListView item selector with corner drawable for the last position
     * (ListView的item点击效果,只处理最后一项圆角处理)
     */
    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, boolean isLastPostion) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (!isLastPostion) {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        } else {
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }

    /**
     * set ListView item selector with corner drawable for the first and the last position
     * (ListView的item点击效果,第一项和最后一项圆角处理)
     */
    public static StateListDrawable listItemSelector(float radius, int normalColor, int pressColor, int itemTotalSize,
                                                     int itemPosition) {
        StateListDrawable bg = new StateListDrawable();
        Drawable normal = null;
        Drawable pressed = null;

        if (itemPosition == 0 && itemPosition == itemTotalSize - 1) {// 只有一项
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, radius, radius, radius,
                    radius});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, radius, radius, radius,
                    radius});
        } else if (itemPosition == 0) {
            normal = cornerDrawable(normalColor, new float[]{radius, radius, radius, radius, 0, 0, 0, 0,});
            pressed = cornerDrawable(pressColor, new float[]{radius, radius, radius, radius, 0, 0, 0, 0});
        } else if (itemPosition == itemTotalSize - 1) {
            normal = cornerDrawable(normalColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
            pressed = cornerDrawable(pressColor, new float[]{0, 0, 0, 0, radius, radius, radius, radius});
        } else {
            normal = new ColorDrawable(normalColor);
            pressed = new ColorDrawable(pressColor);
        }

        bg.addState(new int[]{-android.R.attr.state_pressed}, normal);
        bg.addState(new int[]{android.R.attr.state_pressed}, pressed);
        return bg;
    }


}
