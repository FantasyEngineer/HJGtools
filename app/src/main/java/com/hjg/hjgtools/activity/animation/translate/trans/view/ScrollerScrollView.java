package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.hjg.base.view.GeneralNestScrollView;

public class ScrollerScrollView extends GeneralNestScrollView {
    private Scroller scroller;
    private int mTouchSlop;

    public ScrollerScrollView(Context context) {
        super(context);
        init();
    }


    public ScrollerScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ScrollerScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        scroller = new Scroller(getContext());
        ViewConfiguration configuration= ViewConfiguration.get(getContext());
        //能够识别的最小滑动举例 ViewConfiguration.getScaledTouchSlop();
        //获取TouchSlop值
        mTouchSlop= configuration.getScaledTouchSlop();
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

//        if (t < 200) {
//            scroller.startScroll(getScrollX(), getScrollY(), 0, t);
//        }
    }

    float mXDown, mXMove, mXLastMove;

//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                //获取绝对坐标，相对于屏幕的X坐标
//                mXDown = ev.getRawX();
//                mXLastMove = mXDown;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mXMove = ev.getRawX();
//                float diff = Math.abs(mXMove - mXDown);
//                mXLastMove = mXMove;
//                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
//                if (diff > mTouchSlop) {
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//        return super.onTouchEvent(ev);
//    }

    /*这里执行父布局的滚动*/
    @Override
    public void computeScroll() {
        super.computeScroll();
        /*说明scroller进行中*/
//        if (scroller.computeScrollOffset()) {
//            ((View) getParent()).scrollTo(scroller.getCurrX(), scroller.getCurrY());
//            ((View) getParent()).invalidate();
//        }


    }
}
