package com.hjg.hjgtools.activity.animation.translate.trans.result;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.base.HJGBaseFragment;
import com.hjg.base.listener.OnEasyItemClickListener;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.ClipboardUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.EmptyUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.GeneralNestScrollView;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.adapter.MulRecyclerViewAdapter;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;


/**
 * 真实项目位移展示，采用margin方式，通过缩小margin和放大margin达到效果
 * 但是margin效果特别差，因为手指会抖动，还有滚动布局的fling会导致margin的异常
 */
public class TransTopFragmentMargin extends HJGBaseFragment {

    private TextView tvTitle;
    private boolean isDown = true, isUp = true;

    @Override
    protected int getContentID() {
        return R.layout.fragment_tran_result;
    }

    int lHeight = 200;

    @Override
    protected void initViewAction(View view) {
        GeneralNestScrollView nestedScrollView = view.findViewById(R.id.scrollView);
        tvTitle = view.findViewById(R.id.tvTitle);
        initValueAnimator();


        nestedScrollView.setOnGeneralScrollChangedListener(new GeneralNestScrollView.OnGeneralScrollChangedListener() {

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                /*上滑*/
//                if (t <= lHeight) {
//                    tvTitle.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tvTitle.getLayoutParams();
//                            layoutParams.topMargin = -t;
//                            tvTitle.setLayoutParams(layoutParams);
//                            tvTitle.requestLayout();
//                        }
//                    });
//                }
            }

            @Override
            public void onDownOpt() {
                super.onDownOpt();
                L.d("往下滑动");
                //要出现
                if (!valueAnimatorDown.isRunning() && isDown && !valueAnimator.isRunning()) {
                    isDown = false;
                    isUp = true;
                    valueAnimatorDown.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer animatedValue = (Integer) animation.getAnimatedValue();
                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tvTitle.getLayoutParams();
                            layoutParams.topMargin = -animatedValue;
                            tvTitle.setLayoutParams(layoutParams);
                            tvTitle.requestLayout();
                        }
                    });
                    valueAnimatorDown.setDuration(200);
                    valueAnimatorDown.start();
                }


            }

            @Override
            public void onUpOpt() {
                super.onUpOpt();
                L.d("上滑动");

                //要消失，当view时出现的时候，并且不在动画过程中才能够开始动画
                if (!valueAnimator.isRunning() && isUp && !valueAnimatorDown.isRunning()) {
                    isDown = true;
                    isUp = false;
                    if (valueAnimatorDown.isRunning())
                        valueAnimatorDown.cancel();
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer animatedValue = (Integer) animation.getAnimatedValue();
                            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) tvTitle.getLayoutParams();
                            layoutParams.topMargin = -animatedValue;
                            tvTitle.setLayoutParams(layoutParams);
                            tvTitle.requestLayout();
                        }
                    });
                    valueAnimator.setDuration(200);
                    valueAnimator.start();

                }

            }
        });
    }

    ValueAnimator valueAnimator, valueAnimatorDown;

    private void initValueAnimator() {
        valueAnimator = ValueAnimator.ofInt(0, lHeight);
        valueAnimatorDown = ValueAnimator.ofInt(lHeight, 0);

    }


}