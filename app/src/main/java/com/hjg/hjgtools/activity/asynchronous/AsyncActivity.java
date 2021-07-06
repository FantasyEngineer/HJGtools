package com.hjg.hjgtools.activity.asynchronous;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.handler.HandlerActivity;
import com.hjg.hjgtools.activity.ipc.aidl.AIdlServerActivity;
import com.hjg.hjgtools.activity.rx.RxActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

/**
 * 进程线程
 */
public class AsyncActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("Handler", HandlerActivity.class, "异步内存泄漏等处理方案", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("AIDL", AIdlServerActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("RxJava", RxActivity.class, "一些api的用法", R.drawable.ic_icon_task));

        return listBeans;
    }
}