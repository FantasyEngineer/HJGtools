package com.hjg.hjgtools.test后台服务弹窗;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.WindowManager;

import com.hjg.base.manager.ActivityManager;
import com.hjg.base.util.D;
import com.hjg.base.util.NetUtil;
import com.hjg.hjgtools.MainActivity;

public class NetworkChangeReceiverNew extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent1111) {
        if (NetUtil.isOnline()) {
            D.showShort("当前网络已连接");
//            , "com.hjg.hjgtools.MainActivity"
//            ActivityUtils.openApp2(context, "com.hjg.hjgtools");
//
//            Intent intent1 = new Intent("com.hjg.openapp");
//            intent1.setFlags(FLAG_INCLUDE_STOPPED_PACKAGES);
//            context.sendBroadcast(intent1);
            if (ActivityManager.get().getActivityList() == null || ActivityManager.get().getActivityList().size() == 0) {
//                Intent intent = new Intent();
//                intent.setClass(context, MainActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

                AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
                builder.setTitle("提示");
                builder.setMessage("service弹框");
                builder.setNegativeButton("明白了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setClass(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                Dialog dialog = builder.create();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0 　　　　　　
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
                } else {
                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                }
                dialog.show();
            }


        } else {
            D.showShort("当前网络不可用");
        }
    }
}
