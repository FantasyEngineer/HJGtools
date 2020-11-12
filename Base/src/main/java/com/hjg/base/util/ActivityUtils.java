package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.hjg.base.base.HJGBaseApplication;
import com.hjg.base.manager.ActivityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 活动页面操作类
 */
public enum ActivityUtils {
    INSTANCE;

    /**
     * 判断当前应用某个activity是否存活
     *
     * @param c
     * @return
     */
    public static boolean isActivityAlive(Class c) {
        checkIsExtendsHJGBaseActivity();

        for (Activity activity : ActivityManager.get().getActivityList()) {
            if (c.getSimpleName().equals(activity.getClass().getSimpleName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 关闭所有页面
     */
    public void finishAllBefore() {
        checkIsExtendsHJGBaseActivity();

        while (ActivityManager.get().getActivityList().size() > 0) {
            ActivityManager.get().getActivityList().get(0).finish();
        }
    }

    /**
     * 退出并杀死应用
     *
     * @param context
     */
    public void exit(Activity context) {
        checkIsExtendsHJGBaseActivity();

        try {
            while (ActivityManager.get().getActivityList().size() > 0) {
                ActivityManager.get().getActivityList().get(0).finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
        }
    }

    public static Activity getTopActivity() {
        checkIsExtendsHJGBaseActivity();

        Activity a;
        for (int i = ActivityManager.get().getActivityList().size(); i > 0; i--) {
            a = ActivityManager.get().getActivityList().get(i - 1);
            if (!a.isFinishing()) {
                return a;
            }
        }

        return null;
    }


    /**
     * 前往某个页面
     *
     * @param fromActivity
     * @param toClazz
     * @param itt
     */
    public void goToActivity(Activity fromActivity, Class toClazz, Intent itt) {
        checkIsExtendsHJGBaseActivity();

        try {
            ArrayList<Activity> als = new ArrayList<>();
            for (Activity a : ActivityManager.get().getActivityList()) {
                if (a.getClass().isAssignableFrom(fromActivity.getClass())) {
                    continue;
                }
                als.add(a);
            }
            while (als.size() > 0) {
                try {
                    als.remove(0).finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            itt.setClass(fromActivity, toClazz);
            fromActivity.startActivity(itt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkIsExtendsHJGBaseActivity() {
        if (ActivityManager.get().getActivityList().size() == 0) {
            throw new IllegalArgumentException("请把所有的Activity都继承HJGBaseActivity");
        }
    }


    /**
     * 根本包名打开应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public boolean openApp(Context context, String packageName) {
        try {
            PackageManager pm = context.getPackageManager();    //包管理者
            Intent it = new Intent();                           //意图
            it = pm.getLaunchIntentForPackage(packageName);   //值为应用的包名
            if (null != it) {
                context.startActivity(it);         //启动意图
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            L.d(e.getMessage());
            return false;
        }
    }


    /**
     * 根本包名打开应用，也可以直接跳转到对应的activity ， 不用设置export
     *
     * @param context
     * @param packageName
     */
    public void openApp2(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activityInfos = info.activities;
            ActivityInfo activityInfo = activityInfos[activityInfos.length - 1];
            Intent intent = new Intent();
            intent.setClassName(packageName, activityInfo.name);
            context.startActivity(intent);
        } catch (Exception e) {
            L.d(e.getMessage());
        }
    }


    /**
     * 打开某个应用的某个activity，不启动应用，
     * 可以不是lauchActivity，但是非lauchactivity，必须设置export：true属性
     *
     * @param context
     * @param packageName     com.hjg.locationproject
     * @param activityPackage com.hjg.locationproject.SecondActivity
     * @return
     */
    public void openAppActivity(Context context, String packageName, String activityPackage) {
        try {
            ComponentName localComponentName = new ComponentName(
                    packageName,
                    activityPackage);
            Intent localIntent = new Intent();
            localIntent.setComponent(localComponentName);
            context.startActivity(localIntent);
        } catch (Exception e) {
            L.d(e.getMessage());
        }
    }


    /**
     * 跳转到浏览器打开网页(网址必须以http开头，否则会报错)
     *
     * @param url
     */
    public void goExplore(Context context, String url) {
        if (hasBrowser(context)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(intent);
        } else {
            D.showShort("当前系统没有可用的浏览器");
        }

    }


    /**
     * 是否有浏览器
     *
     * @param context
     * @return
     */
    public boolean hasBrowser(Context context) {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://"));

        @SuppressLint("WrongConstant") List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.GET_INTENT_FILTERS);
        final int size = (list == null) ? 0 : list.size();
        return size > 0;
    }

}
