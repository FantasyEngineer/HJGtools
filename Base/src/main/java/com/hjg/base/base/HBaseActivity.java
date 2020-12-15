package com.hjg.base.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hjg.base.manager.ActivityManager;
import com.hjg.base.receiver.NetworkChangeReceiver;
import com.hjg.base.util.BarUtils;
import com.hjg.base.util.ScreenUtils;

public abstract class HBaseActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.get().onCreate(this);
        activity = this;
        //注册网络监听
        if (isOpenNetListener()) {// 需要该<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
            registerNetChangerReceiver();
        }
    }

    protected boolean isOpenNetListener() {
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityManager.get().onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.get().onResume(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager.get().onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        ActivityManager.get().onStop(this);
    }

    @Override
    public void finish() {
        super.finish();
        ActivityManager.get().finish(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.get().onDestroy(this);

        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);
        }
    }


    /**
     * 注册网络监听
     */
    public void registerNetChangerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }
}
