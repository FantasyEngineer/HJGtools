package com.hjg.hjgtools;

import android.app.Application;

import com.hjg.base.util.Utils;
import com.hjg.base.util.log.androidlog.L;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        L.getLogConfig().configAllowLog(true);
    }
}
