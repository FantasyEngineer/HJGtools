package com.hjg.hjgtools.activity.webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.manager.WebViewManage;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.DeviceUtils;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.HJGUtils;
import com.hjg.base.util.IntentUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TimeUtils;
import com.hjg.base.util.UriUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityWebViewBinding;

import java.io.File;

public class WebViewActivity extends HJGDatabindingBaseActivity<ActivityWebViewBinding> {

    public static final String URL = "url";
    ValueCallback<Uri[]> uploadMsg;
    Uri photoURI;

    @Override
    protected int getContentID() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initViewAction() {
        String url = getIntent().getStringExtra(URL);
        WebViewManage webViewManage = new WebViewManage(this, databinding.webView);

        webViewManage.addJavascriptInterface(new NativePlugin(this, databinding.webView), NativePlugin.NativePluginFlag);
        if (StrUtil.isNotEmpty(url))
            webViewManage.loadUrl(url);

        webViewManage.setOnPicChooserListener(new WebViewManage.OnPicChooserListener() {
            @Override
            public void onCapture(WebView webView, ValueCallback<Uri[]> upload, WebChromeClient.FileChooserParams fileChooserParams) {

                String filePath = FileUtils.getExternalStorageRootDir() + "/" + TimeUtils.getNowLongMillis() + ".jpg";
                /*创建文件*/
                File file = new File(filePath);
                FileUtils.createOrExistsFile(file);

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                if (DeviceUtils.getSDKVersion() >= Build.VERSION_CODES.N) {
                    // 将文件转换成content://Uri的形式
                    photoURI = FileProvider.getUriForFile(HJGUtils.getContext(),
                            "com.hjg.hjgtools.fileProvider",
                            file);

                    // 申请临时访问权限
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                } else {
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    photoURI = Uri.parse("file://" + file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                }
                startActivityForResult(intent, 11);
                uploadMsg = upload;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (databinding.webView.canGoBack()) {
            databinding.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


    //监听bar的返回
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (databinding.webView.canGoBack()) {
                    databinding.webView.goBack();
                } else {
                    onBackPressed();
                }
                break;
            default:
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {//拍照返回
            //小米手机
            uploadMsg.onReceiveValue(new Uri[]{photoURI});
            uploadMsg = null;
        }
    }


}