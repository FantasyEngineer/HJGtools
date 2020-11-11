package com.hjg.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hjg.base.manager.ActivityManager;
import com.hjg.base.util.D;

public abstract class HJGBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.get().onCreate(this);
        setContentView(getContentID());
        try {
            initViewAction();
        } catch (Exception e) {
            D.showShort(e.getMessage());
        }
    }

    /**
     * 添加布局文件
     *
     * @return
     */
    protected abstract int getContentID();


    /**
     * 初始化view，以及data
     */
    protected abstract void initViewAction();


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
    }
}
