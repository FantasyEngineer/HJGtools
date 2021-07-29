package com.hjg.mvc.model;

import com.hjg.base.util.ConvertUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.mvc.listener.OnRequestListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainModelImp implements MainModel {
    private OnRequestListener listener;


    @Override
    public void login() {
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=shenzhen&mode=json&APPID=6c113432fd84a6e28268af291821db16");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                String string = ConvertUtils.inputStream2String(inputStream, "utf-8");
                if (listener != null) {
                    listener.onSuccess(string);
                }
            } else {
                listener.onFail(new IllegalArgumentException("1231"));
            }


        } catch (Exception e) {
            listener.onFail(e);
        }
    }


    public void setListener(OnRequestListener listener) {
        this.listener = listener;
    }
}
