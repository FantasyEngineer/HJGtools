package com.hjg.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static android.view.View.DRAWING_CACHE_QUALITY_HIGH;


public class ViewUtils {

    /**
     * 无效值
     */
    public static final int INVALID = Integer.MIN_VALUE;


    /**
     * 描述：获取view的bitmap  无法使用
     *
     * @param view
     * @return 反击哦voew的bitmap
     */
    public static Bitmap getBitmapFromView(View view) {
        //是ImageView直接获取
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.clearFocus();
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        if (bitmap != null) {
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            canvas.setBitmap(null);
        }
        return bitmap;
    }

    /**
     * 描述该方式原理主要是：View组件显示的内容可以通过cache机制保存为bitmap  无法使用
     *
     * @return 返回view的bitmap
     */
    public static Bitmap createBitmapFromView(View view) {
        Bitmap bitmap = null;
        //开启view缓存bitmap
        view.setDrawingCacheEnabled(true);
        //设置view缓存Bitmap质量
        view.setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);
        //获取缓存的bitmap
        Bitmap cache = view.getDrawingCache();
        if (cache != null && !cache.isRecycled()) {
            bitmap = Bitmap.createBitmap(cache);
        }
        //销毁view缓存bitmap
        view.destroyDrawingCache();
        //关闭view缓存bitmap
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }


    /**
     * 设置PX padding.
     *
     * @param view   the view
     * @param left   the left padding in pixels
     * @param top    the top padding in pixels
     * @param right  the right padding in pixels
     * @param bottom the bottom padding in pixels
     */
    public static void setPadding(View view, int left, int top, int right,
                                  int bottom) {
        view.setPadding(left, top, right, bottom);
    }


    /**
     * 设置 PX margin.
     *
     * @param view   the view
     * @param left   the left margin in pixels
     * @param top    the top margin in pixels
     * @param right  the right margin in pixels
     * @param bottom the bottom margin in pixels
     */
    public static void setMargin(View view, int left, int top, int right,
                                 int bottom) {

        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mMarginLayoutParams = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            if (mMarginLayoutParams != null) {
                if (left != INVALID) {
                    mMarginLayoutParams.leftMargin = left;
                }
                if (right != INVALID) {
                    mMarginLayoutParams.rightMargin = right;
                }
                if (top != INVALID) {
                    mMarginLayoutParams.topMargin = top;
                }
                if (bottom != INVALID) {
                    mMarginLayoutParams.bottomMargin = bottom;
                }
                view.setLayoutParams(mMarginLayoutParams);
            }
        }

    }

}
