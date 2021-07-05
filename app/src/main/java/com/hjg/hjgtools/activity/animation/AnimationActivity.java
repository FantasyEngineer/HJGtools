package com.hjg.hjgtools.activity.animation;

import com.hjg.hjgtools.CommonFragmentActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.TestActivity;
import com.hjg.hjgtools.activity.animation.translate.ViewTransActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

public class AnimationActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> listBeans = new ArrayList<>();
        listBeans.add(new RecyclerListBean("view的移动", ViewTransActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("xml中的补间动画", CommonFragmentActivity.class, "对应的是xml/anim下的动画文件，标签是从外层set标签开始(也可直接试用具体动画)，内部有translate，scale，alpha，rotate", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("帧动画", CommonFragmentActivity.class, "一组图片集合而成，对应的xml标签是animation-list，通常用于做loading效果，或者gif效果", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("属性动画", CommonFragmentActivity.class, "", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("CircularReveal", CommonFragmentActivity.class, "activity的转场使用", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("SVG播放动画", CommonFragmentActivity.class, "主要是播放", R.drawable.ic_icon_task));
        listBeans.add(new RecyclerListBean("lotti播放动画", CommonFragmentActivity.class, "主要是播放类型动画，安卓14上支持，对某些AE属性不支持", R.drawable.ic_icon_task));
        return listBeans;
    }
}