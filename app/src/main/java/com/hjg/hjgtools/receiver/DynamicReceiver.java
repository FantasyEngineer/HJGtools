package com.hjg.hjgtools.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hjg.base.util.D;

public class DynamicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        D.showShort("接受到动态注册广播" + intent.getIntExtra("data", 0));
    }
}
