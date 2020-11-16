package com.hjg.base.base;

import android.app.Application;

import com.hjg.base.util.Utils;

public class HJGBaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }

}
