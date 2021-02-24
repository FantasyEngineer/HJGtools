package com.hjg.base.util;

import android.database.Cursor;
import android.net.Uri;
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
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = HJGUtils.getContext().getContentResolver().query(uri,
                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String path = cursor.getString(columnIndex);  //获取照片路径
        cursor.close();
        return path;
    }
}
