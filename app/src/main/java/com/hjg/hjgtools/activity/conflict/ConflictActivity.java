package com.hjg.hjgtools.activity.conflict;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.ActivityUtils;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class ConflictActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        ActivityUtils.startActivity(recyclerListBean.getaClass());

    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("ScrollView与recyclerView", ScrollViewRecyclerViewActivity.class, "recyclerView在其中无法自适应高度问题的解决"));
        return recyclerListBeans;
    }

}