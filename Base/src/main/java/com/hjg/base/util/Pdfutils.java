package com.hjg.base.util;

import android.webkit.WebView;

/**
 * desc 展示pdf
 * 使用webview展示
 */
public class Pdfutils {

    /**
     * 预览pdf
     * 使用的是webview作为容器，使用asset目录下的js文件进行解析
     *
     * @param webView
     * @param url
     */
    public static void showPdf(WebView webView, String url) {
        webView.loadUrl("file:///android_asset/pdf.html?" + url);
    }
}
