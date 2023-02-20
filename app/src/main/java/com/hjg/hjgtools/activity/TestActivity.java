package com.hjg.hjgtools.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.webview.WebViewActivity;
import com.hjg.hjgtools.databinding.ActivityTestBinding;
import com.hjg.hjgtools.databinding.ActivityWebViewBinding;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import io.reactivex.functions.Consumer;

/**
 * 给simoncof的测试工具类
 */
public class TestActivity extends HJGDatabindingBaseActivity<ActivityTestBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_test;
    }

    public static final String CAMERA = "相机权限";


    @Override
    protected void initViewAction() {
        //返回按钮隐藏
        actionBar.setDisplayHomeAsUpEnabled(false);
        //cesih

        requestPermission(CAMERA, Manifest.permission.CAMERA);

    }

    public void requestPermission(String permissionTag, String... permissonName) {
        new RxPermissions(this).request(permissonName).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    D.showShort(permissionTag + "权限获取----->成功");

                } else {
                    D.showShort(permissionTag + "权限获取----->失败");
                }
            }
        });

    }


    //申请
    public void jumpUrl(View view) {
        startWeb(databinding.etCustom.getText().toString());
    }

    public void apply(View view) {
        startWeb(databinding.etApply.getText().toString());
    }

    public void check(View view) {
        startWeb("https://busminiapp.csmc-cloud.com/face/index.html");
    }


    public void notPass(View view) {
        startWeb("file:///android_asset/h.html");
    }

    public void add(View view) {
        startWeb("https://busminiapp.csmc-cloud.com/face/index.html");
        D.showShort("待补充");
    }


    /**
     * 跳转到webview
     *
     * @param url
     */
    private void startWeb(String url) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, url);
        startActivity(intent);
    }
}