package com.hjg.hjgtools.activity.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Log;

public class DownloadThread extends HandlerThread {

    private static final String TAG = "DownloadThread";

    public static final int TYPE_START = 2;//通知主线程任务开始
    public static final int TYPE_FINISHED = 3;//通知主线程任务结束

    private Handler mUIHandler;//主线程的Handler


    public DownloadThread(String name) {
        super(name);
    }

    /*
     * 执行初始化任务
     * */
    @Override
    protected void onLooperPrepared() {
        Log.e(TAG, "onLooperPrepared: 1.Download线程开始准备");
        super.onLooperPrepared();
    }

    //注入主线程Handler
    public void setUIHandler(Handler UIhandler) {
        mUIHandler = UIhandler;
        Log.e(TAG, "setUIHandler: 2.主线程的handler传入到Download线程");
    }

    /**
     * Download线程开始下载，消耗时间的方法
     */
    public void startDownload() {
        Log.e(TAG, "startDownload: 3.通知主线程,此时Download线程开始下载");
        mUIHandler.sendEmptyMessage(TYPE_START);

        //模拟下载
        Log.e(TAG, "startDownload: 5.Download线程下载中...");
        SystemClock.sleep(2000);

        Log.e(TAG, "startDownload: 6.通知主线程,此时Download线程下载完成");
        mUIHandler.sendEmptyMessage(TYPE_FINISHED);
    }


    @Override
    public synchronized void start() {
        super.start();
    }
}
