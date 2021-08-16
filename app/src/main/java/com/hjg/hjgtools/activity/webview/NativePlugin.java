package com.hjg.hjgtools.activity.webview;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.hjg.base.util.D;
import com.hjg.base.view.dialog.LoadingDialog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class NativePlugin {


    public static final String NativePluginFlag = "android";
    private final Activity activity;
    private final LoadingDialog loadingDialog;
    private WebView webView;

    public NativePlugin(Activity activity, WebView webView) {
        this.activity = activity;
        loadingDialog = new LoadingDialog(activity);
        this.webView = webView;
    }


    @JavascriptInterface
    public void finish() {
        D.showLong("调用了finish方法，无参数");
        activity.finish();
    }

    @JavascriptInterface
    public void finish(String s) {
        D.showShort("调用了finish方法,有参数：" + s);
        activity.finish();
    }

    @JavascriptInterface
    public void selectPhoto() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:get(" + ")");

            }
        });
    }


}
