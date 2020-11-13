package com.hjg.base.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.webkit.MimeTypeMap;

import java.io.File;

public class AppUtils {

    /**
     * 禁止构造
     */
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * 判断App是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return {@code true}: 已安装<br>{@code false}: 未安装
     */
    public static boolean isInstallApp(Context context, String packageName) {
        return !StrUtil.isEmpty(packageName) && IntentUtils.getLaunchAppIntent(context, packageName) != null;
    }


    /**
     * 卸载App
     * 安卓9以上需要权限 <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES" />
     * 只需要声明，不需要动态申请
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void uninstallApp(Context context, String packageName) {
        context.startActivity(IntentUtils.getUninstallAppIntent(packageName));
    }


    /**
     * 安装App(支持6.0)
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void installApp(Context context, String filePath) {
        installApp(context, FileUtils.getFileByPath(filePath));
    }

    /**
     * 安装App（支持6.0）
     *
     * @param context 上下文
     * @param file    文件
     */
    public static void installApp(Context context, File file) {
        if (!FileUtils.isFileExists(file)) return;
//        context.startActivity(IntentUtils.getInstallAppIntent(file));
    }


}
