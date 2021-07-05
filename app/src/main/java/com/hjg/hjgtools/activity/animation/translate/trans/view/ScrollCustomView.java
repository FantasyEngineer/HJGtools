package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.ScreenUtils;

//scrollto scrollby
//scroll to移动到具体的一个点，
//scroll by移动的增量
public class ScrollCustomView extends androidx.appcompat.widget.AppCompatTextView {
    private int parentHeight, parentWidth;
    private int downX, downY;//按下的坐标

    public ScrollCustomView(@NonNull Context context) {
        super(context);
        init();
    }

    public ScrollCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*以父布局的大小作为限制条件*/
        ViewGroup parent = (ViewGroup) getParent();
        parentHeight = parent.getHeight();
        parentWidth = parent.getWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*实时获取滚动的x，y的坐标*/
        int currentX = (int) event.getX();
        int currentY = (int) event.getY();
        int offsetX = 0, offsetY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /*获取按下时候的坐标*/
                downX = currentX;
                downY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                /*计算差值*/
                offsetX = currentX - downX;
                offsetY = currentY - downY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:


                break;
        }
        return true;
    }
}
