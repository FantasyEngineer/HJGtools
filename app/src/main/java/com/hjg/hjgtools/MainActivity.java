package com.hjg.hjgtools;

import android.Manifest;
import android.view.View;

import com.hjg.base.base.HJGBaseActivity;
import com.hjg.base.listener.OnSoftKeyBoardChangeListener;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.AppUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.FileUtils;
import com.hjg.base.util.KeyBoardUtils;
import com.hjg.base.util.L;
import com.hjg.base.util.NetUtil;
import com.hjg.base.util.P;
import com.tbruyelle.rxpermissions3.RxPermissions;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;


public class MainActivity extends HJGBaseActivity {


    @Override
    protected int getContentID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAction() {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        //请求权限
        final RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity or Fragment instance
        // Must be done during an initialization phase like onCreate
        Observable<Boolean> observableRequest = rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        observableRequest.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {

            }
        });

//        KeyBoardUtils.setListener(this, new OnSoftKeyBoardChangeListener() {
//            @Override
//            public void keyBoardShow(int height) {
//                L.d("键盘弹起" + height);
//
//                L.d(ActivityUtils.getTopActivity());
//
//            }
//
//            @Override
//            public void keyBoardHide(int height) {
//                L.d("键盘收回" + height);
//            }
//        });

//        L.d(DataUtils.isDateyyyyMMdd("1900-00-0"));
//        L.d(DataUtils.isAfter("1900-00-0"));
//
//        L.d(DataUtils.getNowDataString());
//
//
//        L.d(DataUtils.getNowDataString(DataUtils.TYPE_LINE));
//
//
//        L.d(DataUtils.deleteDataStringLine("1900-00-0"));
//
//        L.d(DataUtils.nFormat.format(CalenderUtils.formatyyyyMMdd2Calender("19000101").getTime()));
//
//
//        L.d(DataUtils.addDateLine("19000101"));

        P.putString("nihao", "nnn");

        L.d(P.getString("nihao"));

        D.showShort(P.getString("nihao"));
//
//        ArrayList list = ArrayListUtils.newArrayList();
//        list.add("1");
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("1");
//        ArrayList<String> resultList = (ArrayList) ArrayListUtils.deleteSpecialElement(list);
//        for (String o : resultList) {
//            L.d(o);
//        }


//        L.d(list);
//        Map<Integer, String> map = MapUtils.newHashMap();
//        Map<Integer, String> map = new TreeMap<>();
//
//        //添加元素
//        map.put(20134316, "尚振伟");
//        map.put(20134314, "牛建伟");
//        map.put(20134313, "刘中林");
//        map.put(20134311, "刘     江");
//
//        L.d(map);
//        L.d(MapUtils.getValue(map));
//        L.d(MapUtils.getKey(map));
//
//        String[] arryayString = ArrayUtils.newArrayString(4);
//        arryayString[0] = "0";
//        arryayString[1] = "1";
//        arryayString[2] = "2";
//        arryayString[3] = "3";
//
//        L.d(arryayString);
//
//        List list1 = ArrayUtils.array2List(arryayString);
//        list1.remove(1);
//        list1.add(1000);
//        L.d(list1);
//
//
//        L.d(DateUtils.getYear());
//        L.d(DateUtils.getMonth());
//        L.d(DateUtils.getDay());
//
//
//        L.d(DateUtils.getDaysNum(2018, 2));
//        L.d(DateUtils.getSundays(2018, 3));
//
//
//        L.d(StrUtil.getLenght("1900-00-00"));


    }

    public void open(View view) {
//        ActivityUtils.INSTANCE.openApp2(this, "com.hjg.locationproject");
//        ActivityUtils.INSTANCE.openAppActivity(this, "com.hjg.locationproject", "com.hjg.locationproject.MainActivity");
//        ActivityUtils.INSTANCE.goExplore(MainActivity.this, "http://www.baidu.com");

//        L.d( ActivityUtils.INSTANCE.hasBrowser(MainActivity.this));

//        ActivityUtils.INSTANCE.openAlbum(MainActivity.this, 2);
//        ActivityUtils.INSTANCE.callPhone(MainActivity.this, "10086"
//        L.d(NetUtil.getLocalIpAddress(MainActivity.this));

//        L.d(NetUtil.isOnline());
//        L.d(NetUtil.isWifiConnected());
//        L.d(NetUtil.isMobileConnected());
//        L.d(ActivityUtils.getLauncherActivity(this, "com.hjg.hjgtools"));

//        L.d(ActivityUtils.isActivityAlive(SecondActivity.class));
//        L.d(ActivityUtils.isActivityAlive(MainActivity.class));

//        L.d(AppUtils.isInstallApp(MainActivity.this, "com.hjg.hjgtools"));
//        AppUtils.uninstallApp(MainActivity.this, "com.hjg.locationproject");

//        FileUtils.createFileByDeleteOldFile()

//        FileUtils.getCachePath()


        L.d(NetUtil.getMac());
    }


}