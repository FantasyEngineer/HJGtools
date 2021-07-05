package com.hjg.hjgtools.activity.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.RandomUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;

import java.util.List;

public class AIdlServerActivity extends HJGDatabindingBaseActivity {
    private ServiceConnection connection;
    private boolean connected;//连接状态
    private Myaidl myAidl;//获取到aidl对象

    @Override
    protected int getContentID() {
        return R.layout.activity_a_idl_server;
    }

    @Override
    protected void initViewAction() {
        connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                D.showLong("连接成功");
                myAidl = Myaidl.Stub.asInterface(service);
                connected = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                D.showLong("断开连接");
                myAidl = null;
                connected = false;
            }

        };

    }

    /**
     * 打开客户端app
     *
     * @param view
     */
    public void openClientAPP(View view) {
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.aidlclient");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 连接
     *
     * @param view
     */
    public void bind(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AidlService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 增加数据
     *
     * @param view
     */
    public void add(View view) {
        if (connected) {
            try {
                Person person = new Person("张三" + RandomUtils.getRandomDuringTwoNum(1, 100), RandomUtils.getRandomDuringTwoNum(1, 100));
                L.d(person);
                myAidl.addPerson(person);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            D.showLong("服务未连接");
        }
    }

    /**
     * 获取数据
     *
     * @param view
     */
    public void get(View view) {
        if (connected) {
            try {
                List<Person> personList = myAidl.getPersonList();
                L.d(personList);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            D.showLong("服务未连接");
        }
    }

}