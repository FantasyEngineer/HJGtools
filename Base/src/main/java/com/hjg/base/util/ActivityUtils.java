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
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;

import com.hjg.base.manager.ActivityManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 活动页面操作类
 */
public enum ActivityUtils {

    //枚举单例
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
     * 跳转activity
     *
     * @param cla
     * @return
     */
    public static boolean startActivity(Class cla) {
        return startActivity(cla, null);
    }


    /**
     * 跳转activity
     *
     * @param cla
     * @return
     */
    public static boolean startActivity(Class cla, Bundle bundle) {
        try {

            Intent intent = new Intent(getTopActivity(), cla);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            getTopActivity().startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    /**
     * 关闭所有activity
     */
    public void finishAllActivity() {
        checkIsExtendsHJGBaseActivity();

        while (ActivityManager.get().getActivityList().size() > 0) {
            ActivityManager.get().getActivityList().get(0).finish();
        }
    }

    /**
     * 获取launcher activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @return launcher activity
     */
    public static String getLauncherActivity(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : infos) {
            if (info.activityInfo.packageName.equals(packageName)) {
                return info.activityInfo.name;
            }
        }
        return "no " + packageName;
    }

    /**
     * 退出所有activity并杀死应用
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

    /**
     * 获取最栈顶activity
     *
     * @return
     */
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
     * 检查是否继承了HJGbaseactivity
     */
    private static void checkIsExtendsHJGBaseActivity() {
        if (ActivityManager.get().getActivityList().size() == 0) {
            throw new IllegalArgumentException("请把所有的Activity都继承HJGBaseActivity");
        }
    }


    /**
     * 判断是否存在Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isActivityExists(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     */
    public static void launchActivity(Context context, String packageName, String className) {
        launchActivity(context, packageName, className, null);
    }

    /**
     * 打开Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     */
    public static void launchActivity(Context context, String packageName, String className, Bundle bundle) {
        context.startActivity(IntentUtils.getComponentIntent(packageName, className, bundle));
    }


}
