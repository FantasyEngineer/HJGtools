package com.hjg.hjgtools.activity.handler;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.HandlerUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHandlerBinding;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.stream.Stream;

/**
 * Handler的内存泄漏的优化
 */
public class HandlerActivity extends HJGDatabindingBaseActivity<ActivityHandlerBinding> implements View.OnClickListener, HandlerUtils.OnReceiveMessageListener {
    private H h;
    HandlerUtils.HandlerHolder handlerHolder;
    private Thread thread;

    @Override
    protected int getContentID() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initViewAction() {
        //初始化Handler
        h = new H(this);
        databinding.btnStartThread.setOnClickListener(this);
        databinding.destory.setOnClickListener(this);

        //一般情况下我们使用Handler的utils来进行这块的书写
        handlerHolder = new HandlerUtils.HandlerHolder(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartThread:
                Message message = Message.obtain();
                h.sendEmptyMessageDelayed(0, 3000);

                break;
            case R.id.destory:
//                handlerHolder.sendEmptyMessageDelayed(0, 3000);
//                finish();


                //这个thread要重新创建一个继承Thread
//                thread = new Thread(new Runnable() {
//                    public Handler handler;
//
//                    @Override
//                    public void run() {
//                        Looper.prepare();
//
//                        handler = new Handler() {
//
//                            @Override
//                            public void handleMessage(@NonNull Message msg) {
//                                super.handleMessage(msg);
//                                L.d("msg.what" + msg.what);
//                                D.showShort("12312");
//                            }
//                        };
//
//                        Looper.loop();
//                    }
//
//                    public Handler getHandler() {
//                        return handler;
//                    }
//                });
//                thread.start();
                break;
        }
    }


    /*Handlerutils的处理方案*/
    @Override
    public void handlerMessage(Message msg) {
        L.d("handler-handleMessage");
        //页面销毁的过后这句话不执行，但是代码会继续往下执行。
        databinding.destory.setBackground(ResUtils.getDrawable(R.drawable.ic_icon_encryption));
        L.d("handler2-handleMessage");
    }


    /**
     * 静态内部类中弱引用
     * 这里不能使用非静态内部类形式，因为java中非静态内部类默认会持有外部类引用。你写了等于没写
     * 这种方式实现的好处是：
     * 执行了页面的ondestory之后，handler不持有activity的引用，activity被回收，消息依旧在进行，到时间后会
     * 回调到handleMessage中，有关于activity弱引用的形式执行的代码都不会执行，其他逻辑照常使用
     */
    public static class H extends Handler {
        private WeakReference<Activity> activityWeakReference;

        //如果我们的handler中需要使用activity，这里可以使用弱引用的方式
        public H(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }


        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            L.d("handler-handleMessage");
            //页面销毁的过后这句话不执行，但是代码会继续往下执行。
            ((HandlerActivity) activityWeakReference.get()).databinding.destory.setBackground(ResUtils.getDrawable(R.drawable.ic_icon_encryption));
            L.d("handler2-handleMessage");
        }
    }


    public void handlerThread(View view) {
        startActivity(new Intent(this, HandlerThreadActivity.class));

    }
}