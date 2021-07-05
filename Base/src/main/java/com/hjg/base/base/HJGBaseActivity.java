package com.hjg.base.base;

import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hjg.base.manager.ActivityManager;
import com.hjg.base.receiver.NetworkChangeReceiver;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

public abstract class HJGBaseActivity extends HTitleActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentID());
        try {
            initViewAction();
        } catch (Exception e) {
            D.showShort(e.getMessage());
            L.d(e.getMessage());
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

}
