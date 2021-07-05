package com.hjg.hjgtools.activity.handler;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHandlerBinding;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.stream.Stream;

/**
 * Handler的内存泄漏的优化
 */
public class HandlerActivity extends HJGDatabindingBaseActivity<ActivityHandlerBinding> implements View.OnClickListener {
    private H h;

    @Override
    protected int getContentID() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initViewAction() {
        h = new H(this);
        databinding.btnStartThread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        h.sendEmptyMessageDelayed(0, 10000);
    }


    /**
     * 静态内部类中弱引用
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
            Toast.makeText(activityWeakReference.get(), "activity被删除了，我也可以toast", Toast.LENGTH_SHORT).show();
        }
    }


    public void handlerThread(View view) {
        startActivity(new Intent(this, HandlerThreadActivity.class));

    }
}