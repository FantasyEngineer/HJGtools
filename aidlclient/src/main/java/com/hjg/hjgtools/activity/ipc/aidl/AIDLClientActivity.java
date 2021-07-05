package com.hjg.hjgtools.activity.ipc.aidl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.aidlclient.R;
import com.hjg.base.util.D;
import com.hjg.base.util.HJGUtils;
import com.hjg.base.util.RandomUtils;
import com.hjg.base.util.log.androidlog.L;

import java.util.List;

public class AIDLClientActivity extends AppCompatActivity {

    private ServiceConnection connection;
    private boolean connected;//连接状态
    private Myaidl myAidl;//获取到aidl对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HJGUtils.init(this.getApplicationContext());

        connection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                D.showLong("连接成功");
                L.d("MainActivity===" + "onServiceConnected");
                try {
                    service.linkToDeath(new IBinder.DeathRecipient() {
                        @Override
                        public void binderDied() {

                        }
                    }, 1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                myAidl = Myaidl.Stub.asInterface(service);
                connected = true;

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                D.showLong("意外断开连接,手动断开连接的不回调该方法");
                L.d("MainActivity===" + "断开连接");
                myAidl = null;
                connected = false;
            }
        };
    }


    /**
     * 连接
     *
     * @param view
     */
    public void bind(View view) {
        Intent intent = new Intent();
        intent.setPackage("com.hjg.hjgtools");
        intent.setAction("myAidlService");
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


    /**
     * 断开连接
     *
     * @param view
     */
    public void disconnect(View view) {
        try {
            unbindService(connection);
        } catch (Exception e) {
            D.showLong(e.getMessage());
        }
    }
}