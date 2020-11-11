package com.hjg.hjgtools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.CalenderUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.DataUtils;
import com.hjg.base.util.L;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L.d(DataUtils.isDateyyyyMMdd("1900-00-0"));
        L.d(DataUtils.isAfter("1900-00-0"));

        L.d(DataUtils.getNowDataString());


        L.d(DataUtils.getNowDataString(DataUtils.TYPE_LINE));


        L.d(DataUtils.deleteDataStringLine("1900-00-0"));

        L.d(DataUtils.nFormat.format(CalenderUtils.formatyyyyMMdd2Calender("19000101").getTime()));



        L.d(DataUtils.addDateLine("19000101"));

    }
}