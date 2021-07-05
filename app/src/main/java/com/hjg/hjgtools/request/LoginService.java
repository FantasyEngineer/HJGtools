package com.hjg.hjgtools.request;

import com.hjg.hjgtools.entity.BaseBean;
import com.hjg.hjgtools.entity.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {
    /**
     * 校验
     *
     * @return
     */
    @GET("/jimmy_hou/hjginter-face/raw/master/1login/login.txt")
    Observable<BaseBean<LoginBean>> login();

}
