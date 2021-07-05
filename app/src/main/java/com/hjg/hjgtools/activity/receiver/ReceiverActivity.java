package com.hjg.hjgtools.activity.receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.hjg.base.util.D;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.base.view.flyco.animation.BounceEnter.BounceRightEnter;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class ReceiverActivity extends HJGBaseRecyclerMulItemActivity {


    //操作动态广播
    DynamicReceiver dynamicReceiver;
    int i = 0;

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        Intent intent = null;
        switch (recyclerListBean.getTitle()) {
            case "发送静态广播"://8.0以上发送广播
                intent = new Intent();
                //单纯设置action会报错，8.0一下版本不会报错，正常成功
                //报错：BroadcastQueue: Background execution not allowed: receiving Intent { act=com.hjg.static.action flg=0x10 } to com.hjg.hjgtools/.receiver.StaticReceiver
                intent.setAction("com.hjg.static.action");//设置action
                intent.addFlags(0x01000000);//如果要发送静态广播，不指定固定的组件或者包名，只能设置这个隐藏的flag
                //targetversion版本在8.0以上，对于静态广播对出了限制，不允许隐式广播发送。
                //指定广播接收器，可以接收成功
                //设置了component变成了显式广播， 可以不设置action，同样能发送成功。
                //intent.setComponent(new ComponentName(this, StaticReceiver.class));//显示指定组件名称
                sendBroadcast(intent);
                break;

            case "注册广播":
                dynamicReceiver = new DynamicReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.hjg.dynamic");
                registerReceiver(dynamicReceiver, intentFilter);
                D.showShort("注册广播");
                break;
            case "发送动态广播":
                if (dynamicReceiver != null) {
                    intent = new Intent("com.hjg.dynamic");
                    intent.putExtra("data", i++);
                    sendBroadcast(intent);
                } else {
                    D.showShort("没有注册广播");
                }
                break;

            case "反注册广播":
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

    @Override
    protected CharSequence setDesString() {
        return new TextSpanUtils.Builder("提醒:")
                .setBold()
                .setForegroundColor(ResUtils.getColor(R.color.red))
                .appendNewLine()
                .append("要发送能静态注册接受的广播可以设置ComponentName,PackageName,或者设置FLAG_RECEIVER_INCLUDE_BACKGROUND 这个标志位\n" +
                        "设置ComponentName,PackageName就只有指定的应用能接受这个广播")
                .create();
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "静态广播"));
        recyclerListBeans.add(new RecyclerListBean("发送静态广播"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "动态广播"));
        recyclerListBeans.add(new RecyclerListBean("注册广播"));
        recyclerListBeans.add(new RecyclerListBean("发送动态广播"));
        recyclerListBeans.add(new RecyclerListBean("反注册广播"));
        return recyclerListBeans;
    }
}