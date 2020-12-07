package com.hjg.hjgtools.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityNotificationBinding;

public class NotificationActivity extends HJGDatabindingBaseActivity<ActivityNotificationBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initViewAction() {

    }


    public void sendNotification(View view) {
        D.showShort("发送通知");
    }
}