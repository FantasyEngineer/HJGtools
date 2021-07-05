package com.hjg.hjgtools.activity.animation.translate.trans.result;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.CommonFragmentActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;


/**
 * 项目中可以使用的，头部位移的方法
 */
public class TransResultActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {

        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("scrollView与头部的联动,margin方式", CommonFragmentActivity.class, "滑动时，头部出现和消失，这是一个失败的例子。因为无法解决抖动问题", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("scrollView与头部的联动,transY方式", CommonFragmentActivity.class, "滑动时，头部出现和消失，动画的方式", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("scrollView与头部的联动,Scroller方式", CommonFragmentActivity.class, "滑动时，头部出现和消失，scroll to的方式", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("贴边效果", CommonFragmentActivity.class, "滑动时，只能在父布局贴边滑动", R.drawable.ic_icon_task));
        return listBeans;
    }
}