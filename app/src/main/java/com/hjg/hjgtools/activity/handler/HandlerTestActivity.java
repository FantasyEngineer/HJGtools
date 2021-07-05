package com.hjg.hjgtools.activity.handler;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHandlerTestBinding;

import java.lang.ref.WeakReference;

public class HandlerTestActivity extends HJGDatabindingBaseActivity<ActivityHandlerTestBinding> {
    public Button destory;

    @Override
    protected int getContentID() {
        return R.layout.activity_handler_test;
    }

    HJGHandler handler = new HJGHandler(activity);

    @Override
    protected void initViewAction() {

        databinding.testWeakRefrence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                handler.sendEmptyMessageDelayed(2, 3000);
            }
        });

        destory = findViewById(R.id.destory);

        databinding.destory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public static class HJGHandler extends Handler {
        WeakReference<Activity> activityWeakReference;

        public HJGHandler(Activity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            L.d("handler-handleMessage");
            //页面销毁的过后这句话不执行，但是代码会继续往下执行。
            ((HandlerTestActivity) activityWeakReference.get()).destory.setBackground(ResUtils.getDrawable(R.drawable.ic_icon_encryption));
            L.d("handler2-handleMessage");

        }
    }


}