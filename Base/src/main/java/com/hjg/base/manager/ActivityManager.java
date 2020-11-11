package com.hjg.base.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ActivityManager {
    private static ActivityManager activitys;
    private List<Activity> activityList;

    private ActivityManager() {
        activityList = new LinkedList<>();
    }

    public static ActivityManager get() {
        if (activitys == null) {
            activitys = new ActivityManager();
        }
        return activitys;
    }

    /**
     * 添加页面到list中
     *
     * @param activity
     */
    private void addActivity(Activity activity) {
        if (!activityList.contains(activity)) {
            activityList.add(activity);
        } else {
            activityList.remove(activity);
            activityList.add(activity);
        }
    }

    /**
     * 从list中移除页面
     *
     * @param activity
     */
    private void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public void onCreate(Activity activity) {
        addActivity(activity);
    }

    public void onStart(Activity activity) {

    }

    public void onResume(Activity activity) {

    }

    public void onPause(Activity activity) {
    }

    public void onStop(Activity activity) {

    }

    public void finish(Activity activity) {
        removeActivity(activity);
    }


    /**
     * 退出
     *
     * @param activity
     */
    public void onDestroy(Activity activity) {
        removeActivity(activity);
    }


    /**
     * 关闭所有页面
     */
    public void finishAllBefore() {
        while (activityList.size() > 0) {
            activityList.get(0).finish();
        }
    }

    /**
     * 退出并杀死应用
     *
     * @param context
     */
    public void exit(Activity context) {
        try {
            while (activityList.size() > 0) {
                activityList.get(0).finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
            android.app.ActivityManager activityMgr = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
        }
    }

    public Activity getTopActivity() {
        Activity a;
        for (int i = activityList.size(); i > 0; i--) {
            a = activityList.get(i - 1);
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
        try {
            ArrayList<Activity> als = new ArrayList<>();
            for (Activity a : activityList) {
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


    /**
     * 获取所有activity的集合
     *
     * @return
     */
    public List<Activity> getActivityList() {
        return activityList;
    }

    /**
     * 除了某个页面，其他的全部关闭。
     */
    public void finishAllWithoutOneActivity(Class className) {
        Iterator<Activity> iterator = activityList.iterator();
        while (iterator.hasNext()) {
            Activity i = iterator.next();
            if (i.getClass().getSimpleName().equals(className.getSimpleName())) {
                iterator.remove();
            }
        }
    }
}
