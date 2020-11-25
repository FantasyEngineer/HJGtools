package com.hjg.hjgtools.test后台服务弹窗;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.hjg.base.util.D;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.R;

public class NetService extends Service {
    private static final String CHANNEL_ONE_ID = "121212";
    private static final CharSequence CHANNEL_ONE_NAME = "sony";
    private NetworkChangeReceiverNew networkChangeReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Intent nfIntent = new Intent(this, MainActivity.class);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext())
                .setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText("") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

//----------------  新增代码 --------------------------------------
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //修改安卓8.1以上系统报错
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_MIN);
            notificationChannel.enableLights(false);//如果使用中的设备支持通知灯，则说明此通知通道是否应显示灯
            notificationChannel.setShowBadge(false);//是否显示角标
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.createNotificationChannel(notificationChannel);
            builder.setChannelId(CHANNEL_ONE_ID);
        }


        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(1, notification);

        registerNetChangerReceiver();


    }

    /**
     * 注册网络监听
     */
    public void registerNetChangerReceiver() {
        D.showShort("注册了网络监听");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiverNew();
        intentFilter.setPriority(1000);
        registerReceiver(networkChangeReceiver, intentFilter);

        //注册监听
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.hjg.openapp");
        OpenAppReceiver openAppReceiver = new OpenAppReceiver();
        registerReceiver(openAppReceiver, intentFilter2);
    }

    @Override
    public void onDestroy() {
        D.showShort("服务被杀死");
        Intent intent = new Intent(this, NetService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            D.showShort("开启服务");
            startForegroundService(intent);
        }

        super.onDestroy();

    }
}
