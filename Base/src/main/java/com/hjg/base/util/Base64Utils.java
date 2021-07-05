package com.hjg.base.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * bae64工具类
 */
public class Base64Utils {


    /**
     * Base64数据转换成Bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapByte = Base64.decode(base64Data, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapByte, 0, bitmapByte.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * Bitmap转Base64数据
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * string 转 base64
     *
     * @param data
     * @return
     */
    public static String string2Base64(String data) {
        return Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
    }


    /**
     * string 转 base64
     *
     * @param data
     * @return
     */
    public static String base642String(String data) {
        byte[] stringBytes = Base64.decode(data, Base64.DEFAULT);
        return ConvertUtils.byte2String(stringBytes);
    }
}
