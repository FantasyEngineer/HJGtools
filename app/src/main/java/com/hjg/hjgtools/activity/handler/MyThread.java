package com.hjg.hjgtools.activity.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

public class MyThread extends Thread {
    private Handler handler;

    @Override
    public void run() {
        super.run();
        Looper.prepare();
        handler = new Handler() {

            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                D.showShort("线程中收到了消息");
            }
        };
        Looper.loop();
    }


    public Handler getHandler() {
        return handler;
    }
}
