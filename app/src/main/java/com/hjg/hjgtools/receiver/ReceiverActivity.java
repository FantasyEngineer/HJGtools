package com.hjg.hjgtools.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityReceiverBinding;
import com.hjg.hjgtools.receiver.DynamicReceiver;
import com.hjg.hjgtools.receiver.StaticReceiver;

public class ReceiverActivity extends HJGDatabindingBaseActivity<ActivityReceiverBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_receiver;
    }

    @Override
    protected void initViewAction() {

        databinding.tvAttention.setText(new TextSpanUtils.Builder("提醒:\n")
                .setBold()
                .setForegroundColor(ResUtils.getColor(R.color.red))
                .append("要发送能静态注册接受的广播可以设置ComponentName,PackageName,或者设置FLAG_RECEIVER_INCLUDE_BACKGROUND 这个标志位\n" +
                        "设置ComponentName,PackageName就只有指定的应用能接受这个广播")
                .create());

    }


    //8.0以上发送静态广播
    public void sendBroadcast(View view) {
        Intent intent = new Intent();
        //单纯设置action会报错，8.0一下版本不会报错，正常成功
        //报错：BroadcastQueue: Background execution not allowed: receiving Intent { act=com.hjg.static.action flg=0x10 } to com.hjg.hjgtools/.receiver.StaticReceiver
        intent.setAction("com.hjg.static.action");//设置action
        intent.addFlags(0x01000000);//如果要发送静态广播，不指定固定的组件或者包名，只能设置这个隐藏的flag
        //targetversion版本在8.0以上，对于静态广播对出了限制，不允许隐式广播发送。
        //指定广播接收器，可以接收成功
        //设置了component变成了显式广播， 可以不设置action，同样能发送成功。
//        intent.setComponent(new ComponentName(this, StaticReceiver.class));//显示指定组件名称
        sendBroadcast(intent);
    }


    //操作动态广播
    DynamicReceiver dynamicReceiver;
    int i = 0;

    public void operationDynamicBroadcast(View view) {

        switch (view.getId()) {
            case R.id.btnRegister://注册
                dynamicReceiver = new DynamicReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.hjg.dynamic");
                registerReceiver(dynamicReceiver, intentFilter);
                D.showShort("注册广播");
                break;
            case R.id.btnSendDynamic://发送
                if (dynamicReceiver != null) {
                    Intent intent = new Intent("com.hjg.dynamic");
                    intent.putExtra("data", i++);
                    sendBroadcast(intent);
                } else {
                    D.showShort("没有注册广播");
                }
                break;

            case R.id.btnUnregister://反注册
                if (dynamicReceiver != null) {
                    unregisterReceiver(dynamicReceiver);
                    dynamicReceiver = null;
                    D.showShort("注销广播");
                } else {
                    D.showShort("没有注册广播");
                }
                break;
        }

    }
}