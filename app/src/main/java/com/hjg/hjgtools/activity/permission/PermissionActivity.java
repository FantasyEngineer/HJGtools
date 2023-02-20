package com.hjg.hjgtools.activity.permission;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.IntentUtils;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class PermissionActivity extends HJGBaseRecyclerMulItemActivity {
    public static final String SMS = "短信权限";
    public static final String CAMERA = "相机权限";
    public static final String CALL_PHONE = "电话权限";
    public static final String STORAGE = "内存卡读写权限";
    public static final String CONTACTS = "联系人";
    public static final String VIDEO = "录像";

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
            case SMS:
                requestPermission(SMS, Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS);
                break;
            case CAMERA:
                requestPermission(CAMERA, Manifest.permission.CAMERA);
                break;
            case CALL_PHONE:
                requestPermission(CALL_PHONE, Manifest.permission.CALL_PHONE);
                break;
            case STORAGE:
                requestPermission(STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case CONTACTS:
                requestPermission(CONTACTS, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS);
                break;
        }
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(SMS));
        recyclerListBeans.add(new RecyclerListBean(CAMERA));
        recyclerListBeans.add(new RecyclerListBean(CALL_PHONE));
        recyclerListBeans.add(new RecyclerListBean(STORAGE));
        recyclerListBeans.add(new RecyclerListBean(CONTACTS));
        recyclerListBeans.add(new RecyclerListBean(VIDEO));
        return recyclerListBeans;
    }

    public void requestPermission(String permissionTag, String... permissonName) {
        new RxPermissions(this).request(permissonName).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    D.showShort(permissionTag + "权限获取----->成功");
                    switch (permissionTag) {
                        case SMS:
                            ActivityUtils.sendSMSS("10086", "预先写入的短信");
                            break;
                        case CAMERA:
                            ActivityUtils.openCamera(activity);
                            break;
                        case CALL_PHONE:
                            ActivityUtils.callPhone(activity, "10086");
                            break;
                        case STORAGE:
                            break;
                        case CONTACTS:
                            break;
                    }
                } else {
                    D.showShort(permissionTag + "权限获取----->失败");
                }
            }
        });

    }
}