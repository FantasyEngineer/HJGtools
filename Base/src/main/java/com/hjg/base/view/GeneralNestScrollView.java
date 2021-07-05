package com.hjg.base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

public class GeneralNestScrollView extends NestedScrollView {
    public GeneralNestScrollView(Context context) {
        super(context);
    }

    public GeneralNestScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GeneralNestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 20;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        /*到顶检测*/
        if (t == 0) {
            if (null != onGeneralScrollChangedListener) {
                onGeneralScrollChangedListener.onTop();
            }
        }

        /*到底检测*/
        View childAt = getChildAt(0);
        if (childAt != null) {
            int i = childAt.getHeight() - getHeight();
            if (i == t) {
                if (null != onGeneralScrollChangedListener) {
                    onGeneralScrollChangedListener.onBottom();
                }
            }
        }

        /*上滑下滑操作识别*/
        if (oldt > t && oldt - t > SCROLLLIMIT) {
            if (null != onGeneralScrollChangedListener) {
                onGeneralScrollChangedListener.onDownOpt();
            }
        } else if (oldt < t && t - oldt > SCROLLLIMIT) {
            if (null != onGeneralScrollChangedListener) {
                onGeneralScrollChangedListener.onUpOpt();
            }
        }


        if (null != onGeneralScrollChangedListener) {
            onGeneralScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);

    }

    public OnGeneralScrollChangedListener onGeneralScrollChangedListener;

    public void setOnGeneralScrollChangedListener(OnGeneralScrollChangedListener onGeneralScrollChangedListener) {
        this.onGeneralScrollChangedListener = onGeneralScrollChangedListener;
    }

    public abstract static class OnGeneralScrollChangedListener {
        public abstract void onScrollChanged(int l, int t, int oldl, int oldt);


        public void onTop() {
        }/*到头了*/

        public void onBottom() {
        }/*到底部了*/

        public void onUpOpt() {
        }/*上滑操作*/

        public void onDownOpt() {
        }/*下滑操作*/
    }


    /*滑动到头部*/
    public void smoothUp() {
        this.post(new Runnable() {
            @Override
            public void run() {
                fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }


    /*滑动到底部*/
    public void smoothDown() {
        fullScroll(ScrollView.FOCUS_DOWN);
    }


    /**
     * 阻尼：1000为将惯性滚动速度缩小1000倍，近似drag操作。
     */
//    @Override
//    public void fling(int velocity) {
//        super.fling(0);
//    }

    private float firstDownY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstDownY = ev.getY();
                break;
        }
        return super.onTouchEvent(ev);
    }
}
