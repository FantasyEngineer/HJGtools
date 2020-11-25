package com.hjg.hjgtools.base;

import com.hjg.base.base.HJGBaseApplication;
import com.hjg.base.util.Utils;
import com.hjg.base.util.log.androidlog.L;

public class MyApplication extends HJGBaseApplication {
    public static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        application = this;
        L.getLogConfig().configAllowLog(true);
    }
}
