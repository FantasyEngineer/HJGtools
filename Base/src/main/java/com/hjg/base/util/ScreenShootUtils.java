package com.hjg.base.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

/**
 * 截图
 */
public class ScreenShootUtils {

    /**
     * 获取viewgroup的快照
     *
     * @param viewGroup
     * @return
     */
    public static Bitmap getViewGroupBitmap(ViewGroup viewGroup) {
        viewGroup.setDrawingCacheEnabled(true);
        Bitmap screenShotAsBitmap = Bitmap.createBitmap(viewGroup.getDrawingCache());
        viewGroup.setDrawingCacheEnabled(false);
        return screenShotAsBitmap;
    }


    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, 0, dm.widthPixels, dm.heightPixels);
        view.destroyDrawingCache();
        return ret;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity activity
     * @return Bitmap
     */
    public static Bitmap captureWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int statusBarHeight = BarUtils.getStatusBarHeight(activity);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Bitmap ret = Bitmap.createBitmap(bmp, 0, statusBarHeight, dm.widthPixels, dm.heightPixels - statusBarHeight);
        view.destroyDrawingCache();
        return ret;
    }

}
