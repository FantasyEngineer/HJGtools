
package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.log.androidlog.L;

/**
 * 可以平滑移动的scrollerlayout
 */
public class ScrollerFrameLayout extends FrameLayout {
    public ScrollerFrameLayout(@NonNull Context context) {
        super(context);
    }

    public ScrollerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public ScrollerFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /*负责滚动视图*/
    private Scroller scroller;

    private void init() {
        scroller = new Scroller(getContext());
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        L.d("computeScroll");
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 移动到目的坐标
     *
     * @param destX
     * @param destY
     */
    public void smoothScrollTo(int destX, int destY) {

        /*这儿是父布局的left和top，总是0,0*/
        int left = getLeft();
        int top = getTop();

        /*那么根据目标的值，我们算出来当前的差值*/
        int dx = destX - left;
        int dy = destY - top;

        /*只是赋值一些属性*/
        scroller.startScroll(0, 0, dx, dy, 1000);
        invalidate();
    }
}
