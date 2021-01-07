package com.hjg.hjgtools.activity.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.NotificationUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityNotificationBinding;

public class NotificationActivity extends HJGDatabindingBaseActivity<ActivityNotificationBinding> {

    public static final String CHANNEL_ID = "HOUJIGUO";
    private static final int NOTIFICATION_ID = 1001;

    @Override
    protected int getContentID() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initViewAction() {

        databinding.tvWarnning.setText(new TextSpanUtils.Builder("注意：\n").setForegroundColor(ResUtils.getColor(R.color.red)).setBold()
                .append("NotificationChannel是Android O新增的通知渠道，其允许您为要显示的每种通知类型创建用户可自定义的渠道\n" +
                        "如果你需要发送属于某个自定义渠道的通知，你需要在发送通知前创建自定义通知渠道" + "\n如果没有添加渠道则报错").append("No Channel found for pkg=com.example.xx.xx, channelId=null, id=1001, tag=null…\n").setForegroundColor(ResUtils.getColor(R.color.red)).create());

    }


    /**
     * 发送通知
     *
     * @param view
     */
    public void sendNotification(View view) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, new Intent(activity, MainActivity.class), PendingIntent.FLAG_ONE_SHOT);
        NotificationUtils.sendNotification(activity, "HOUJIGUO", 1000, "通知", "这里是内容", R.mipmap.ic_launcher, "这里是ticker", "这里是subtext", pendingIntent);

        NotificationUtils.sendNotification(activity, "HOUJIGUO2222", 1001, "通知", "这里是内容", R.mipmap.ic_launcher, "这里是ticker", "这里是subtext", pendingIntent);



    }
}