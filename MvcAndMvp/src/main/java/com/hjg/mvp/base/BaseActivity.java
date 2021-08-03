package com.hjg.mvp.base;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.hjg.base.util.log.androidlog.L;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {


    @Override
    public void showLoading() {
        L.d("showLoading");
    }

    @Override
    public void dissLoadind() {
        L.d("showLoading");
    }

    @Override
    public void t() {
        L.d("t");
    }

    @Override
    public void l() {
        L.d("l");
    }

    @Override
    public Activity getContext() {
        return this;
    }
}
