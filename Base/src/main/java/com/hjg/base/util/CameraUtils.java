package com.hjg.base.util;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import java.io.File;

public class CameraUtils {


    /**
     * 打开照相机拍照，返回uri
     * 安卓10，安卓11，安卓5.0 均可使用
     *
     * @param activity
     * @param path        保存的图片地址
     * @param requestCode 请求码
     * @return 返回刚拍的图片的uri ，加载图片可以使用参数path也可以使用返回的uri
     * String path = FileUtils.getExternalStorageRootDir() + "/DCIM/" + TimeUtils.getNowLongMillis() + ".jpg";
     */
    public static Uri openCamera(Activity activity, String path, int requestCode) {
        PackageManager packageManager = HJGUtils.getContext().getPackageManager();
        /*是否允许了权限*/
        int granted = packageManager.checkPermission(Manifest.permission.CAMERA, AppUtils.getAppInfo(HJGUtils.getContext()).getPackageName());
        if (granted != PackageManager.PERMISSION_GRANTED) {
            throw new IllegalArgumentException("未获取到相机有关权限");
        }
        int grantedStorage = packageManager.checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, AppUtils.getAppInfo(HJGUtils.getContext()).getPackageName());
        if (grantedStorage != PackageManager.PERMISSION_GRANTED) {
            throw new IllegalArgumentException("未获取到存储有关权限");
        }

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断是否有相机
        File photoFile = null;
        Uri photoUri = null;
        /*判断是否是大于等于安卓10*/
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            // 适配android 10
            photoUri = UriUtils.creatUriByPath(path);
        } else {
            try {
                boolean isCreated = FileUtils.createFileByDeleteOldFile(path);
                if (isCreated) {
                    photoFile = new File(path);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }

            if (photoFile != null) {
                String mCameraImagePath = photoFile.getAbsolutePath();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                    photoUri = FileProvider.getUriForFile(activity, AppUtils.getAppInfo(HJGUtils.getContext()).getPackageName() + ".fileProvider", photoFile);
                } else {
                    photoUri = Uri.fromFile(photoFile);
                }
            }
        }
        if (photoUri != null) {
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            activity.startActivityForResult(captureIntent, requestCode);
        }
        return photoUri;
    }
}
