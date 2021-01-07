package com.hjg.hjgtools.base;

import com.hjg.base.base.HJGBaseApplication;
import com.hjg.base.util.HJGUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.BuildConfig;
import com.okhttplib.HJGHttp;

public class MyApplication extends HJGBaseApplication {
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        HJGUtils.init(this);
        application = this;
        L.getLogConfig().configAllowLog(true);
        HJGHttp.init(BuildConfig.BASE_SAFE_URL, this, true);
    }
}
