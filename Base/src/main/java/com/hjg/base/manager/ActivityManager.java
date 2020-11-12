package com.hjg.base.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Activity管理
 */
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
