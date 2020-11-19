package com.hjg.base.base;

import android.os.Build;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hjg.base.R;
import com.hjg.base.util.Pdfutils;

public class PdfWebViewActivity extends HJGBaseActivity {
    private WebView webview;
    public static final String PDF_URL = "PDF_URL";

    @Override
    protected int getContentID() {
        return R.layout.activity_pdfwebview;
    }

    @Override
    protected void initViewAction() {
        String url = getIntent().getStringExtra(PDF_URL);
        webview = findViewById(R.id.webView);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webview.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        webview.getSettings().setBuiltInZoomControls(true);// 设置WebView可触摸放大缩小
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError
                    error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        Pdfutils.showPdf(webview, url);

    }
}
