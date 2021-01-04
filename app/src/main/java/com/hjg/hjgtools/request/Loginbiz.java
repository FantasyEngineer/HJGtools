package com.hjg.hjgtools.request;

import android.content.Context;

import com.hjg.hjgtools.entity.BaseBean;
import com.hjg.hjgtools.entity.LoginBean;
import com.okhttplib.BaseBiz;
import com.okhttplib.HJGHttp;
import com.okhttplib.HttpBase;
import com.okhttplib.biz.Appbiz;
import com.okhttplib.biz.ServiceApi;

import io.reactivex.Observable;

public class Loginbiz extends BaseBiz {
    private static Loginbiz appbiz;

    public Loginbiz(Context context) {
        super(context);
    }

    public static Loginbiz getAppbiz() {
        if (appbiz == null) {
            appbiz = new Loginbiz(HJGHttp.app);
        }
        return appbiz;
    }


    public Observable<BaseBean<LoginBean>> login() {
        return getRxGsonApi(LoginService.class).login().compose(HttpBase.IO_UI);
    }


}
