package com.hjg.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.NetUtil;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetUtil.isOnline()) {
//            D.showShort("当前网络已连接");
        } else {
            D.showShort("当前网络不可用");
        }
    }
}
