package com.hjg.hjgtools.base;

import android.os.Build;
import android.os.StrictMode;

import com.hjg.base.base.HJGBaseApplication;
import com.hjg.base.util.HJGUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.BuildConfig;
import com.hjg.hjgtools.activity.coin.LiteOrmDBUtil;
import com.okhttplib.HJGHttp;
import com.opensource.svgaplayer.SVGAParser;

public class MyApplication extends HJGBaseApplication {
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        HJGUtils.init(this);
        application = this;
        L.getLogConfig().configAllowLog(true);
        HJGHttp.init(BuildConfig.BASE_SAFE_URL, this, true);
        LiteOrmDBUtil.createCascadeDB(this);

    }
}
