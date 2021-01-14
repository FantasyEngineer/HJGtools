package com.hjg.hjgtools.activity.recyclerVIew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.ActivityUtils;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.activity.conflict.ScrollViewRecyclerViewActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class RecyclerViewStyleActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RecyclerView单布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "普通纵向RecyclerView的使用", ScrollViewRecyclerViewActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "普通横向RecyclerView的使用", ScrollViewRecyclerViewActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RecyclerView多布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "普通纵向多布局使用", MainActivity.class));
        return recyclerListBeans;
    }
}