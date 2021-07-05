package com.hjg.base.util;

import com.hjg.base.util.log.androidlog.L;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {

    /**
     * 获取assets目录下的流
     *
     * @param name
     * @return
     */
    public static InputStream getAsset(String name) {
        try {
            return HJGUtils.getContext().getAssets().open(name);
        } catch (IOException e) {
            L.e("文件未找到");
            return null;
        }
    }


    /**
     * 保存asset的文件到本地目录
     *
     * @return
     */
    public static boolean saveAssetFile() {
        return Boolean.parseBoolean(null);
    }
}
