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

import com.hjg.base.R;
import com.hjg.base.manager.ActivityManager;
import com.hjg.base.util.log.androidlog.L;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


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
            getTopActivity().overridePendingTransition(R.anim.enter_trans_from_right, 0);
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


    /**
     * 根本包名打开应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean openApp(Context context, String packageName) {
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
    public static void openApp2(Context context, String packageName) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES);
            ActivityInfo[] activityInfos = info.activities;
            ActivityInfo activityInfo = activityInfos[activityInfos.length - 1];
            Intent intent = new Intent();
            intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
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
    public static void openAppActivity(Context context, String packageName, String activityPackage) {
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


}
