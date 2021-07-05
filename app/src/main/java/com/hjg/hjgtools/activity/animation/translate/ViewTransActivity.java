package com.hjg.hjgtools.activity.animation.translate;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.CommonFragmentActivity;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TransResultActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class ViewTransActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("位移项目可使用效果", TransResultActivity.class, "成品可以使用的效果", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("layout方式", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("offset方式", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("layotParams方式", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("ScrollBy方式", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("scrollto实现位移", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("scroller实现流畅方移动", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("GestureDetector控制", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        return listBeans;
    }
}