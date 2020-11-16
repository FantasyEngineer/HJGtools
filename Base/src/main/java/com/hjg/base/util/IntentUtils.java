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

import com.hjg.base.util.log.androidlog.L;

import java.util.ArrayList;
import java.util.List;

public class IntentUtils {


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


    /**
     * 选择相机
     */

    private void openCamera(Context context) {
//        // 跳转到系统照相机
//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (cameraIntent.resolveActivity(context.getPackageManager()) != null) {
//            // 设置系统相机拍照后的输出路径
//            // 创建临时文件
//            mTmpFile = OtherUtils.createFile(context.getApplicationContext());
//
//            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
//            context.(cameraIntent, REQUEST_CAMERA);
//        } else {
//        }
    }


    /**
     * 打开相册
     *
     * @param activity
     * @param REQUEST_ALBUM_OK
     */
    public void openAlbum(Activity activity, int REQUEST_ALBUM_OK) {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        activity.startActivityForResult(albumIntent, REQUEST_ALBUM_OK);
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     * 不需要call phone权限
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(Activity activity, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        activity.startActivity(intent);
    }

    /**
     * 发送短信 需要sms的权限
     */
    public void sendSMSS(String phone, String content) {
        if (!StrUtil.isEmpty(content) && !StrUtil.isEmpty(phone)) {
            SmsManager manager = SmsManager.getDefault();
            ArrayList<String> strings = manager.divideMessage(content);
            for (int i = 0; i < strings.size(); i++) {
                manager.sendTextMessage(phone, null, content, null, null);
            }
        } else {
            D.showShort("内容以及号码不能为空");
            return;
        }
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
     *
     * @param packageName 包名
     * @return intent
     */
    public static Intent getUninstallAppIntent(String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }


}
