package com.hjg.hjgtools.activity;

import android.content.Intent;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.webview.WebViewActivity;
import com.hjg.hjgtools.databinding.ActivityTestBinding;
import com.hjg.hjgtools.databinding.ActivityWebViewBinding;

/**
 * 给simoncof的测试工具类
 */
public class TestActivity extends HJGDatabindingBaseActivity<ActivityTestBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_test;
    }

    @Override
    protected void initViewAction() {
        //返回按钮隐藏
        actionBar.setDisplayHomeAsUpEnabled(false);
    }


    //申请
    public void jumpUrl(View view) {
        startWeb(databinding.etCustom.getText().toString());
    }

    public void apply(View view) {
        startWeb(databinding.etApply.getText().toString());
    }

    public void check(View view) {
        startWeb("file:///android_asset/picChooser.html");
    }


    public void notPass(View view) {
        startWeb("file:///android_asset/h.html");
    }

    public void add(View view) {
        startWeb("https://cofdev.csmc-cloud.com/evapp/727app.html#/");
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