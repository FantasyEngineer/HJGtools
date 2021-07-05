package com.hjg.base.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.webkit.MimeTypeMap;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IntentUtils {

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
     * 获取其他应用组件的意图
     *
     * @param packageName 包名
     * @param className   全类名
     * @param bundle      bundle
     * @return intent
     */
    public static Intent getComponentIntent(String packageName, String className, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (bundle != null) intent.putExtras(bundle);
        ComponentName cn = new ComponentName(packageName, className);
        intent.setComponent(cn);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取打开App的意图
     *
     * @param context     上下文
     * @param packageName 包名
     * @return intent
     */
    public static Intent getLaunchAppIntent(Context context, String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName);
    }


    /**
     * 获取卸载App的意图
     * 9。0以上的应用必须申请权限
     * 报错：UninstallerActivity: Uid 10474 does not have android.permission.REQUEST_DELETE_PACKAGES or android.permission.DELETE_PACKAGES
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * 获取安装App（支持6.0）的意图
     *
     * @param filePath 文件路径
     * @return intent
     */
    public static Intent getInstallAppIntent(String filePath) {
        return getInstallAppIntent(FileUtils.getFileByPath(filePath));
    }

    /**
     * 获取安装App(支持6.0)的意图
     *
     * @param file 文件
     * @return intent
     */
    public static Intent getInstallAppIntent(File file) {
        if (file == null) return null;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type;

        if (Build.VERSION.SDK_INT < 23) {
            type = "application/vnd.android.package-archive";
        } else {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(FileUtils.getFileExtension(file));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(HJGUtils.getContext(), "com.your.package.fileProvider", file);
            intent.setDataAndType(contentUri, type);
        }
        intent.setDataAndType(Uri.fromFile(file), type);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


    /**
     * 获取打开相册的intent
     *
     * @return
     */
    public static Intent getAlbumIntent(Context context) {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判断是否为Android N版本
            albumIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(context, "com.hjg.hjgtools" + ".fileProvider", new File(context.getExternalCacheDir() + File.separator + "1.jpg"));
        }
        albumIntent.setDataAndType(uri, "image/*");
        return albumIntent;
    }


    /**
     * 获取拍照意图
     *
     * @return
     */
    public static Intent getCaremaIntent() {
        Intent intent1 = new Intent();
        intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        return intent1;
    }

    /**
     * 获取拍照意图，但是先写到图片的位置
     *
     * @return
     */
    public static Intent getCaremaIntent(String filePath) {
        /*创建文件*/
        File file = new File(filePath);
        FileUtils.createOrExistsFile(file);

        Intent intent1 = new Intent();
        intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent1.addCategory(Intent.CATEGORY_DEFAULT);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if (DeviceUtils.getSDKVersion() >= Build.VERSION_CODES.N) {
            // 将文件转换成content://Uri的形式
            Uri photoURI = FileProvider.getUriForFile(HJGUtils.getContext(),
                    "com.hjg.hjgtools.fileProvider",
                    file);

            // 申请临时访问权限
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

        } else {
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            Uri uri = Uri.parse("file://" + file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        return intent1;
    }


}
