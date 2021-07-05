package com.hjg.hjgtools;

import android.os.Bundle;

import com.hjg.base.base.BaseFragment;
import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.FragmentUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.animation.FrameFragment;
import com.hjg.hjgtools.activity.animation.ObjectAnimatorFragment;
import com.hjg.hjgtools.activity.animation.SVGFragment;
import com.hjg.hjgtools.activity.animation.TweenFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TiebianFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TransResultActivity;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TransTopFragmentScroller;
import com.hjg.hjgtools.activity.animation.translate.trans.transfragment.GestureFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.transfragment.LayoutFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.transfragment.ScrollToFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.transfragment.ScrollerFragment;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TransTopFragmentMargin;
import com.hjg.hjgtools.activity.animation.translate.trans.result.TransTopFragmentTransX;
import com.hjg.hjgtools.databinding.ActivityLayoutBinding;

/**
 * fragment功能的使用类
 */
public class CommonFragmentActivity extends HJGDatabindingBaseActivity<ActivityLayoutBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_layout;
    }

    @Override
    protected void initViewAction() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String title = bundle.getString(TITLE);
        BaseFragment fragment = null;
        /*效果页面*/
        if (title.contains("scrollView与头部的联动,margin方式")) {
            fragment = new TransTopFragmentMargin();
        }
        if (title.contains("scrollView与头部的联动,transY方式")) {
            fragment = new TransTopFragmentTransX();
        }
        if (title.contains("scrollView与头部的联动,Scroller方式")) {
            fragment = new TransTopFragmentScroller();
        }
        if (title.contains("贴边效果")) {
            fragment = new TiebianFragment();
        }


        /*补间动画*/
        if (title.contains("xml中的补间动画")) {
            fragment = new TweenFragment();
        }
        /*帧动画*/
        if (title.contains("帧动画")) {
            fragment = new FrameFragment();
        }

        /*属性动画*/
        if (title.contains("属性动画")) {
            fragment = new ObjectAnimatorFragment();
        }
        if (title.contains("SVG播放动画")) {
            fragment = new SVGFragment();
        }







        /*动画页面的处理*/
        if (title.contains("layout") || title.contains("layotParams") || title.contains("offset") || title.contains("ScrollBy")) {
            fragment = new LayoutFragment();
        } else if (title.contains("scrollto")) {//scroll to的用法
            fragment = new ScrollToFragment();
        } else if (title.contains("scroller")) {//scroll to的用法
            fragment = new ScrollerFragment();
        } else if (title.contains("GestureDetector")) {//手势gesture控制页面
            fragment = new GestureFragment();
        }
        FragmentUtils.addFragment(getSupportFragmentManager(), fragment, R.id.container);

    }

}