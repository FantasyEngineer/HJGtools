package com.hjg.hjgtools.activity.widget;

import android.os.Bundle;

import com.hjg.hjgtools.activity.widget.recyclerVIew.RecyclerViewActivity;
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
        recyclerListBeans.add(new RecyclerListBean("RecyclerView", RecyclerViewActivity.class));
        recyclerListBeans.add(new RecyclerListBean("EditText", EdittextActivity.class));
        recyclerListBeans.add(new RecyclerListBean("FAQ", WidgetActivity.class));
        recyclerListBeans.add(new RecyclerListBean("九格密码输入", WidgetActivity.class));
        recyclerListBeans.add(new RecyclerListBean("自定义数字键盘", WidgetActivity.class));
        return recyclerListBeans;
    }
}