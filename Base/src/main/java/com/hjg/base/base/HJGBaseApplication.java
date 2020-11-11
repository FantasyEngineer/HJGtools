package com.hjg.base.base;

import android.app.Application;

import com.hjg.base.util.D;
import com.hjg.base.util.L;

public class HJGBaseApplication extends Application {

    private static Application app;

    public static Application getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //toast
        D.init(this);
        //sharepreference

    }

}
