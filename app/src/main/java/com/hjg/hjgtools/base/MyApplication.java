package com.hjg.hjgtools.base;

import com.hjg.base.base.HJGBaseApplication;
import com.hjg.base.util.HJGUtils;
import com.hjg.base.util.log.androidlog.L;

public class MyApplication extends HJGBaseApplication {
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        HJGUtils.init(this);
        application = this;
        L.getLogConfig().configAllowLog(true);
    }
}
