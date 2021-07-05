package com.hjg.hjgtools.activity.task;


import android.os.Bundle;

import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class TaskActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("JobScheduler", JobSchedulerActivity.class));
        return recyclerListBeans;
    }
}