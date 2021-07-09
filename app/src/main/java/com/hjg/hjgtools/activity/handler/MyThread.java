package com.hjg.hjgtools.activity.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

/**
 * 线程中如何使用Handler，这里需要使用同步锁
 */
public class MyThread extends Thread {

    private Handler threadHander;

    public MyThread() {
        super();
        L.d("构造子线程" + Thread.currentThread().getName());
//        Looper.prepare();//这里不能用来执行prepare。因为当前还是在调用线程中。 该线程与run中的线程不是同一个
    }


    @Override
    public void run() {
        super.run();
        Looper.prepare();
        synchronized (this){
            notifyAll();
        }
        Looper.loop();
    }


    public Looper getLooper() {
        synchronized (this) {
            if (Looper.myLooper() == null) {
                try {
                    L.d("Looper.wait();");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        L.d("获取到了Looper();");
        return Looper.myLooper();

    }

    /**
     * 获取子线程handler
     *
     * @return
     */
    public Handler getThreadHander() {
        threadHander = new Handler(getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        D.showShort("准备数据+开始下载");
                        threadHander.sendEmptyMessageDelayed(2, 2000);
                        break;
                    case 2:
                        D.showShort("下载成功");
                        break;
                }
            }
        };
        return threadHander;
    }
}
