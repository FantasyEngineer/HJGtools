package com.hjg.hjgtools.activity.widget.recyclerVIew;

import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.activity.conflict.ScrollViewRecyclerViewActivity;
import com.hjg.hjgtools.activity.dialog.DialogActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class RecyclerViewActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RecyclerView基本"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "头部底部检测", RecyclerViewScrollActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "滑动检测", RecyclerViewUpwardDownActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "满行展示不全，换行展示", AutoLineActivity.class));


        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RecyclerView单布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "纵向", ScrollViewRecyclerViewActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "横向", HorizontalRecyclerViewActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "表格布局", TabActivity.class));


        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "RecyclerView多布局"));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "普通纵向多布局使用", DialogActivity.class));
        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "RecyclerView粘性标签", StickyActivity.class));
        return recyclerListBeans;
    }
}