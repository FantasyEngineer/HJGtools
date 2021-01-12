package com.hjg.hjgtools.activity.bound;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.view.viewgroup.BoundScrollView;
import com.hjg.hjgtools.base.HJGBaseRecyclerActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

/**
 * 弹性布局
 */
public class ElasticActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        ActivityUtils.startActivity(recyclerListBean.getaClass());
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<RecyclerListBean>();
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "弹性ScrollView", BoundScrollViewActivity.class));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "弹性RecyclerView"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "弹性横向ScrollView"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "弹性ScrollView"));
        listBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_FUNCTION, "弹性ScrollView"));
        return listBeans;
    }
}