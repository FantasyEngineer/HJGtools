package com.hjg.base.util;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.hjg.base.util.log.utils.Utils;

/**
 * uri相关操作
 */
public class UriUtils {

    /**
     * 根据uri获取到真实的path
     *
     * @param uri
     * @return /storage/emulated/0/DCIM/100ANDRO/DSC_0051.JPG
     */
    public static String getFilePathByUri(Uri uri) {
        //大于安卓N的时候用该方法，测试通过
        String path = null;
        if (DeviceUtils.getSDKVersion() >= Build.VERSION_CODES.N) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HJGUtils.getContext().getContentResolver().query(uri,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);  //获取照片路径
            cursor.close();
        } else {
            String wholeID = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                wholeID = DocumentsContract.getDocumentId(uri);
                // 使用':'分割
                String id = wholeID.split(":")[1];
                String[] projection = {MediaStore.Images.Media.DATA};
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                Cursor cursor = HJGUtils.getContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,//
                        projection, selection, selectionArgs, null);
                int columnIndex = cursor.getColumnIndex(projection[0]);
                if (cursor.moveToFirst()) path = cursor.getString(columnIndex);
                cursor.close();
            } else {
                D.showShort("版本低于19了，这里没有适配了");
            }
        }
        return path;
    }
}
