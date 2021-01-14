package com.hjg.hjgtools.activity.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hjg.base.util.D;

/**
 * 静态广播测试
 */
public class StaticReceiver extends BroadcastReceiver {

    public StaticReceiver() {

    }


    @Override
    public void onReceive(Context context, Intent intent) {
        D.showShort("静态广播接受成功");
    }
}
