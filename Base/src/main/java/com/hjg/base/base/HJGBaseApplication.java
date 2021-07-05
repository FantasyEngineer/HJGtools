package com.hjg.base.base;

import android.app.Application;

import com.hjg.base.util.HJGUtils;

public class HJGBaseApplication extends Application {


    public static HJGBaseApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        HJGUtils.init(this);
    }

}
