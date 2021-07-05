package com.hjg.base.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;

import com.hjg.base.util.log.utils.Utils;

import java.io.File;

/**
 * uri相关操作
 */
public class UriUtils {

    /**
     * 根据uri获取到真实的path
     * <p>前提是这是一个系统生成的uri，使用privider生成的这里报错
     * 测试小米安卓11可以使用。华为安卓10可以使用
     *
     * @param uri
     * @return /storage/emulated/0/DCIM/100ANDRO/DSC_0051.JPG
     */
    public static String getFilePathByUri(Uri uri) {
        //大于安卓N的时候用该方法，测试通过
        String path = null;
        //大于7.0的时候，使用这个方式来进行
        if (DeviceUtils.getSDKVersion() >= Build.VERSION_CODES.N) {
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = HJGUtils.getContext().getContentResolver().query(uri,
                    filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);  //获取照片路径
            cursor.close();
        } else {//低于7.0的时候，大于4.4的时候
            /*低于7.0获取有问题*/
            path = uri.getPath();
        }
        return path;
    }

    /**
     * 根据文件路径来生成Uri
     * 必须要设置privider以及请求动态权限，才能使用
     * 参数：FileUtils.getExternalStorageRootDir() + "/DCIM/" + TimeUtils.getNowLongMillis() + ".jpg"
     * <p>
     * 安卓10可以用，安卓11可以使用
     *
     * @return
     */
    public static Uri creatUriByPath(String path) {
        Uri uri = null;
        File file = new File(path);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(HJGUtils.getContext(), "com.hjg.hjgtools.fileProvider", file);
        } else {
            D.showShort("小于7.0的设备可能还需要适配");
            uri = Uri.fromFile(file);
        }
        return uri;
    }


    /**
     * 获取系统的相册的uri，用于拍照时提前放入的uri参数
     * 可以不用 作为参考
     * 输入的文件夹就是根目录/picture/...
     *
     * @return
     */
    public static Uri getSystemPicUri() {
        /*以下代码是创建一个系统图库的uri，默认文件夹是根目录下的picture，要求是安卓10以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            String status = Environment.getExternalStorageState();
            // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
            if (status.equals(Environment.MEDIA_MOUNTED)) {
                return HJGUtils.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
            } else {
                return HJGUtils.getContext().getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, new ContentValues());
            }
        } else {
            //低版本还需要适配
            return null;
        }
    }

}
