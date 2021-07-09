package com.hjg.hjgtools.activity.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.HandlerUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHandlerThreadBinding;

/**
 * 如何往子线程中发送handler消息
 */
public class HandlerThreadActivity extends HJGDatabindingBaseActivity<ActivityHandlerThreadBinding> implements HandlerUtils.OnReceiveMessageListener {

    private static final String TAG = "DownloadThread";
    private DownloadThread mHandlerThread;
    private Handler threadHander;
    HandlerUtils.HandlerHolder uiHandler;

    @Override
    protected int getContentID() {
        return R.layout.activity_handler_thread;
    }

    @Override
    protected void initViewAction() {

        //创建下载线程
        mHandlerThread = new DownloadThread("DownloadThread");
        mHandlerThread.start();
        //获取下载线程中的handler
        threadHander = mHandlerThread.getThreadHander(mHandlerThread.getLooper());

        //子线程注入主线程的mUIhandler，可以在子线程执行任务的时候，随时发送消息回来主线程
        uiHandler = new HandlerUtils.HandlerHolder(this);
        mHandlerThread.setUIHandler(uiHandler);

    }

    /**
     * 点击开始下载
     *
     * @param view
     */
    public void startDownLoad(View view) {
        threadHander.sendEmptyMessage(1);
    }

    /*主线程收到消息序列*/
    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case 3:
                databinding.proces.setText(String.format(ResUtils.getString(R.string.down_process), msg.arg1));
                break;
            case 4:
                databinding.proces.setText("下载完成");
                break;
        }

    }
}