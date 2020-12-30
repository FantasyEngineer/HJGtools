package com.hjg.base.base;

import android.app.Application;

import com.hjg.base.util.HJGUtils;

public class HJGBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HJGUtils.init(this);
    }

}
