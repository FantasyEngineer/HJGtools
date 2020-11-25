package com.hjg.hjgtools.test后台服务弹窗;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.MainActivity;

public class OpenAppReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        L.d("OpenAppReceiver");
        intent.setClass(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}


