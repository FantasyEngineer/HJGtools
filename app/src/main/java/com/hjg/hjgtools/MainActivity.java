package com.hjg.hjgtools;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.hjg.base.base.HJGBaseActivity;
import com.hjg.base.listener.OnSoftKeyBoardChangeListener;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.ArrayUtils;
import com.hjg.base.util.DateUtils;
import com.hjg.base.util.KeyBoardUtils;
import com.hjg.base.util.L;
import com.hjg.base.util.MapUtils;
import com.hjg.base.util.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


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
        KeyBoardUtils.setListener(this, new OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                L.d("键盘弹起" + height);

                L.d(ActivityUtils.getTopActivity());

            }

            @Override
            public void keyBoardHide(int height) {
                L.d("键盘收回" + height);
            }
        });

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

//        P.putString("nihao", "nnn");
//
//        L.d(P.getString("nihao"));
//
        ArrayList list = ArrayListUtils.newArrayList();
        list.add("1");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("1");
//        ArrayList<String> resultList = (ArrayList) ArrayListUtils.deleteSpecialElement(list);
//        for (String o : resultList) {
//            L.d(o);
//        }


        L.d(list);
//        Map<Integer, String> map = MapUtils.newHashMap();
        Map<Integer, String> map = new TreeMap<>();

        //添加元素
        map.put(20134316, "尚振伟");
        map.put(20134314, "牛建伟");
        map.put(20134313, "刘中林");
        map.put(20134311, "刘     江");

        L.d(map);
        L.d(MapUtils.getValue(map));
        L.d(MapUtils.getKey(map));

        String[] arryayString = ArrayUtils.newArrayString(4);
        arryayString[0] = "0";
        arryayString[1] = "1";
        arryayString[2] = "2";
        arryayString[3] = "3";

        L.d(arryayString);

        List list1 = ArrayUtils.array2List(arryayString);
        list1.remove(1);
        list1.add(1000);
        L.d(list1);


        L.d(DateUtils.getYear());
        L.d(DateUtils.getMonth());
        L.d(DateUtils.getDay());


        L.d(DateUtils.getDaysNum(2018, 2));
        L.d(DateUtils.getSundays(2018, 3));


        L.d(StrUtil.getLenght("1900-00-00"));


        L.d("seconed是否还活着" + ActivityUtils.isActivityAlive(SecondActivity.class));


    }

    public void open(View view) {
//        ActivityUtils.INSTANCE.openApp2(this, "com.hjg.locationproject");
//        ActivityUtils.INSTANCE.openAppActivity(this, "com.hjg.locationproject", "com.hjg.locationproject.MainActivity");
//        ActivityUtils.INSTANCE.goExplore(MainActivity.this, "http://www.baidu.com");

//        L.d( ActivityUtils.INSTANCE.hasBrowser(MainActivity.this));

        ActivityUtils.INSTANCE.openAlbum(MainActivity.this, 2);
    }


}