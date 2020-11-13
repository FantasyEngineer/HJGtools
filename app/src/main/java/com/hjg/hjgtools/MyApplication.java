package com.hjg.hjgtools;

import android.app.Application;

import com.hjg.base.util.Utils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
