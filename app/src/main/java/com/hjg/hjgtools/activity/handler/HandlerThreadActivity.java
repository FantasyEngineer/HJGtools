package com.hjg.hjgtools.activity.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHandlerThreadBinding;

/**
 * 如何往子线程中发送handler消息
 */
public class HandlerThreadActivity extends HJGDatabindingBaseActivity<ActivityHandlerThreadBinding> {

    private static final String TAG = "DownloadThread";
    private Handler mUIhandler;
    private DownloadThread mHandlerThread;

    @Override
    protected int getContentID() {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void initViewAction() {

        mHandlerThread = new DownloadThread("DownloadThread");
        mHandlerThread.start();

        //初始化Handler，传递mHandlerThread内部的一个looper
        mUIhandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                //判断mHandlerThread里传来的msg，根据msg进行主页面的UI更改
                switch (msg.what) {
                    case DownloadThread.TYPE_START:
                        //不是在这里更改UI哦，只是说在这个时间，你可以去做更改UI这件事情，改UI还是得在主线程。
                        Log.e(TAG, "4.主线程知道Download线程开始下载了...这时候可以更改主界面UI");
                        break;
                    case DownloadThread.TYPE_FINISHED:
                        Log.e(TAG, "7.主线程知道Download线程下载完成了...这时候可以更改主界面UI，收工");
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        //子线程注入主线程的mUIhandler，可以在子线程执行任务的时候，随时发送消息回来主线程
        mHandlerThread.setUIHandler(mUIhandler);

    }


    /**
     * 开启子线程
     *
     * @param view
     */
    public void startThread(View view) {

        //子线程开始下载
        mHandlerThread.startDownload();

    }


}