package com.hjg.base.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.hjg.base.R;
import com.hjg.base.manager.ActivityManager;
import com.hjg.base.receiver.NetworkChangeReceiver;
import com.hjg.base.util.BarUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.view.flyco.dialog.listener.OnBtnClickL;
import com.hjg.base.view.flyco.dialog.widget.MaterialDialog;

public abstract class HBaseActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    protected Activity activity;
    public static String TITLE = "title";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.get().onCreate(this);
        activity = this;

        try {
            //注册网络监听
            if (isOpenNetListener()) {// 需要该<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
                registerNetChangerReceiver();
            }
            //强制竖屏或者横屏或者用户控制
            setRequestedOrientation(setScreenOrientation());
            if (!isShowActionBar()) {
                supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            if (!isShowBottomNavigation()) {
                ScreenUtils.hideBottomUIMenu(activity);
            }
            if (isFullScreen()) {
                ScreenUtils.setFullScreen(activity);
            }
        } catch (Exception e) {

        }


    }

    /**
     * 是否全屏
     *
     * @return 默认不全屏
     */
    protected boolean isFullScreen() {
        return false;
    }

    //是否展示底部导航
    protected boolean isShowBottomNavigation() {
        return true;
    }

    //是否展示ActionBar
    protected boolean isShowActionBar() {
        return true;
    }

    //设置屏幕旋转
    protected int setScreenOrientation() {
//        return ActivityInfo.SCREEN_ORIENTATION_USER;//用户控制
//        return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;//强制横屏
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;//强制竖屏
    }

    protected boolean isOpenNetListener() {
        return false;
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

    /**
     * 展示dialog
     *
     * @param content
     */
    public void showDialog(String content) {
        showDialog(content, null);
    }

    public void showDialog(String content, OnBtnClickL onBtnClickL) {
        MaterialDialog materialDialog = new MaterialDialog(activity);
        materialDialog.setCanceledOnTouchOutside(true);
        materialDialog.setCancelable(true);
        materialDialog.titleTextColor(ResUtils.getColor(R.color.black));
        materialDialog.btnNum(1);
        materialDialog.setOnBtnClickL(onBtnClickL);
        materialDialog.content(content);
        materialDialog.setTitle("说明：");
        materialDialog.show();
    }

}
