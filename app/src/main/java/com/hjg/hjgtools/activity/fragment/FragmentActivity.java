package com.hjg.hjgtools.activity.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.hjg.hjgtools.activity.conflict.ScrollViewRecyclerViewActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class FragmentActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "碎片布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "碎片化布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "横竖屏变换布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "仿照Activity"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "加载单独fragment"));
        return recyclerListBeans;
    }
}