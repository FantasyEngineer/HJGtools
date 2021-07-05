package com.hjg.hjgtools.activity.system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.AppUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.DeviceUtils;
import com.hjg.base.util.IntentUtils;
import com.hjg.base.util.NetUtil;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class SystemActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        new RxPermissions(this).request(Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    D.showShort("权限获取失败，可能某些数据取不到");
                }
            }
        });


    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "系统相关"));
        recyclerListBeans.add(strucData("设备品牌", DeviceUtils.getBrand()));
        recyclerListBeans.add(strucData("设备厂商", DeviceUtils.getManufacturer()));
        recyclerListBeans.add(strucData("设备型号", DeviceUtils.getModel()));
        recyclerListBeans.add(strucData("设备名 （Device）", DeviceUtils.getDevice()));
        recyclerListBeans.add(strucData("设备分辨率", ScreenUtils.getScreenHeight() + "*" + ScreenUtils.getScreenWidth()));
        recyclerListBeans.add(strucData("设备deviceID", DeviceUtils.getDeviceId()));
        recyclerListBeans.add(strucData("设备AndroidID", DeviceUtils.getAndroidID()));
        recyclerListBeans.add(strucData("设备序列号", DeviceUtils.getSerial()));
        recyclerListBeans.add(strucData("sim卡序列号", DeviceUtils.getSimSerialNum()));
        recyclerListBeans.add(strucData("IMEI号", DeviceUtils.getIMEI(activity) + ""));
        recyclerListBeans.add(strucData("mac地址获取方式1", DeviceUtils.getMacAddress()));
        recyclerListBeans.add(strucData("mac地址获取方式2", NetUtil.getMac()));
        recyclerListBeans.add(strucData("系统安卓版本号", DeviceUtils.getSDKVersion() + ""));
        recyclerListBeans.add(strucData("系统安卓版本名称", "安卓" + DeviceUtils.getOSVersionName()));
        recyclerListBeans.add(strucData("是否root", DeviceUtils.isDeviceRooted() + ""));
        recyclerListBeans.add(strucData("内网ip地址", NetUtil.getInnerIp()));
        recyclerListBeans.add(strucData("手机是否连接网络", NetUtil.isOnline() + ""));
        recyclerListBeans.add(strucData("手机是否wifi连接", NetUtil.isWifiConnected() + ""));
        recyclerListBeans.add(strucData("手机连接wifi的名称", NetUtil.getCurWIFIName(activity) + ""));
        recyclerListBeans.add(strucData("手机是否数据连接", NetUtil.isMobileConnected() + ""));
        recyclerListBeans.add(strucData("卸载", "卸载自身"));

        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "APP相关"));
        recyclerListBeans.add(strucData("应用名称", AppUtils.getAppInfo(activity).getName() + ""));
        recyclerListBeans.add(strucData("应用包名", AppUtils.getAppInfo(activity).getPackageName() + ""));
        recyclerListBeans.add(strucData("应用包路径", AppUtils.getAppInfo(activity).getPackagePath() + ""));
        recyclerListBeans.add(strucData("应用版本号", AppUtils.getAppInfo(activity).getVersionName() + ""));
        recyclerListBeans.add(strucData("应用版本码", AppUtils.getAppInfo(activity).getVersionCode() + ""));
        recyclerListBeans.add(strucData("应用是否系统应用", AppUtils.getAppInfo(activity).isSystem() + ""));
        recyclerListBeans.add(strucData("App是否有root权限", AppUtils.isAppRoot() + ""));
        recyclerListBeans.add(strucData("应用是否是测试版本", AppUtils.isAppDebug(activity) + ""));
        recyclerListBeans.add(strucData("应用签名SHA1信息", AppUtils.getAppSignatureSHA1(activity)));

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        recyclerListBeans.add(strucData("应用开启HeapSize获取的最大内存M", activityManager.getLargeMemoryClass() + ""));
        recyclerListBeans.add(strucData("应用获取的内存M", activityManager.getMemoryClass() + ""));

        recyclerListBeans.add(strucData("应用进程ID", AppUtils.getAppProcessId() + ""));


        return recyclerListBeans;
    }

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        if (recyclerListBean.getSpannableStringBuilderTitle().toString().contains("卸载")) {
            D.showShort("卸载");
            startActivity(IntentUtils.getUninstallAppIntent(activity.getPackageName()));
        }
    }

    @NotNull
    private RecyclerListBean strucData(String cateName, String content) {
        return new RecyclerListBean(new TextSpanUtils.Builder(cateName + " : ").setBold().appendTab().append(content).setForegroundColor(ResUtils.getColor(R.color.gray)).create());
    }
}