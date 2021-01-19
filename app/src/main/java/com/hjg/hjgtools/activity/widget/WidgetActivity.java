package com.hjg.hjgtools.activity.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class WidgetActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("EditText", EdittextActivity.class));
        recyclerListBeans.add(new RecyclerListBean("FAQ", WidgetActivity.class));
        return recyclerListBeans;
    }
}