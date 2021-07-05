package com.hjg.base.util;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.hjg.base.R;

public class NotificationUtils {

    private NotificationUtils() {

    }

    /**
     * 不设置删除的动作
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void sendNotification(Context activity, String channelId, int notificationID, String title, String content, int icon, String ticker, String subText, PendingIntent pendingIntent) {
        sendNotification(activity, channelId, notificationID, title, content, icon, ticker, subText, pendingIntent, null);
    }

    /**
     * @param channelId      渠道id
     * @param notificationID 通知id 取消的时候使用
     * @param title          通知标题
     * @param content        通知内容
     * @param icon           通知的图标
     * @param ticker         接收到通知的时候状态栏弹出来的字幕
     * @param subText        滚动的小文字
     * @param pendingIntent  目的
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public static void sendNotification(Context activity, String channelId, int notificationID, String title, String content, int icon, String ticker, String subText, PendingIntent pendingIntent, PendingIntent deletePendingIntent) {
        //1.获取到manager
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        //2.构造Notification
        //系统版本小于安卓O（8.0）的情况
        Notification.Builder builder = new Notification.Builder(activity);
        //大于或者等于8.0的情况
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //1.这里使用channel的方式，处理channel
            NotificationChannel channel = new NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_HIGH);
//            channel.enableLights(true); //是否在桌面icon右上角展示小红点
//            channel.setLightColor(Color.RED); //小红点颜色
//            channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
            notificationManager.createNotificationChannel(channel);
//            builder.setChannelId(channelId);

            //2.这里使用构造的方式，不做对桌面的处理
            builder = new Notification.Builder(activity, channelId);
        }

        //推送bug:安卓7.0新功能有将通知合并，点击合并的消息，跳转启动页(即使App正在运行)
        //解决方法1：Android 7.0以上的设备强制不合并消息
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setGroupSummary(false)
                    .setGroup("group");
        }

        builder.setContentText(content);
        builder.setContentTitle(title);
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setSmallIcon(icon);//小图标
        builder.setLargeIcon(ImageUtils.res2Bitmap(icon));//大图标
        if (StrUtil.isNotEmpty(ticker)) {
            builder.setTicker(ticker);//setTicker()设置的是通知bai时在状态栏显du示的通知zhidaozhuan，一般是一段文字，例如在状态shu栏显示内“您有一条短信，待查收”。容
        }
        if (StrUtil.isNotEmpty(subText)) {
            builder.setSubText(subText);
        }
        builder.setAutoCancel(true);//：用户点击Notification点击面板后是否让通知取消(默认不取消)
        builder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);//设置默认的三色灯与振动器//Notification.DEFAULT_SOUND默认声音
//        builder.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.kalimba));//设置自定义的提示音

        //设置被点击
        builder.setContentIntent(pendingIntent);
        //设置被删除
        builder.setDeleteIntent(deletePendingIntent);
        Notification n = builder.build();
        //3.manager.notify()
        notificationManager.notify(notificationID, n);

    }


    /**
     * 测试的弹窗
     *
     * @param context
     * @param title
     * @param content
     */
    public static void testSendNotification(Context context, String title, String content) {
        int notificationId = RandomUtils.getRandomDuringTwoNum(1, 10000);
        NotificationUtils.sendNotification(context, "1", notificationId, title + notificationId, content + notificationId, R.drawable.fab_add, notificationId + "", "我是小的提示" + notificationId, null);

    }
}
