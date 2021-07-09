package com.hjg.hjgtools.activity.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

/**
 * HandlerThread
 * 使用线程为我们创建好的looper，我们就可以创建该线程的handler
 * 好处:HandlerThread对应的工作线程因为使用了handler机制，所以当message中没有消息的时候，该工作线程会被挂起，完全不占用cpu的时间片，所以我们可以不将HandlerThread退出，这样在一个app进程中就有一个随时待命的工作线程来执行耗时任务
 * 随需而用。
 */
public class DownloadThread extends HandlerThread {

    private static final String TAG = "DownloadThread";

    public static final int TYPE_START = 2;//通知主线程任务开始
    public static final int TYPE_FINISHED = 3;//通知主线程任务结束

    private Handler mUIHandler;//主线程的Handler
    private Handler threadHander;

    public DownloadThread(String name) {
        super(name);
    }

    //注入主线程Handler
    public void setUIHandler(Handler UIhandler) {
        mUIHandler = UIhandler;
    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        super.run();
        //这looper已经准备好了。
    }


    /**
     * 获取子线程handler
     *
     * @return
     */
    public Handler getThreadHander(Looper looper) {
        threadHander = new Handler(looper) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        D.showShort("准备数据+开始下载");
                        threadHander.sendEmptyMessageDelayed(2, 2000);
                        break;
                    case 2:
                        for (int i = 0; i < 100; i++) {
                            Message message = Message.obtain();
                            message.what = 3;
                            message.arg1 = i;
                            mUIHandler.sendMessage(message);
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        mUIHandler.sendEmptyMessage(4);
                        D.showShort("下载成功");
                        break;
                }
            }
        };
        return threadHander;
    }
}
