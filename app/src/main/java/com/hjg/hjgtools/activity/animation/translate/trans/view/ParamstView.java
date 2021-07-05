package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.DateUtils;
import com.hjg.base.util.ScreenUtils;

/**
 * 使用param方式实现自身的移动
 */
public class ParamstView extends androidx.appcompat.widget.AppCompatTextView {

    private int downX, downY;//按下的坐标
    private int screenWidth, screenHeight;//父布局的宽高


    public ParamstView(@NonNull Context context) {
        super(context);
        init();
    }

    public ParamstView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParamstView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        screenWidth = ScreenUtils.getScreenWidth();
        screenHeight = ScreenUtils.getScreenHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*以父布局的大小作为限制条件*/
        ViewGroup parent = (ViewGroup) getParent();
        screenHeight = parent.getMeasuredHeight();
        screenWidth = parent.getMeasuredWidth();
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
                /*使用margin属性进行位移*/
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
                layoutParams.leftMargin = layoutParams.leftMargin + offsetX;
                layoutParams.topMargin = layoutParams.topMargin + offsetY;
                setLayoutParams(layoutParams);


//                /*不允许控件位移出父腹部*/
//                int left = getLeft();
//                int right = getRight();
//                int top = getTop();
//                int width = getWidth();
//                int height = getHeight();
//                int bottom = getBottom();
//                if (left < 0) {
//                    layout(0, top, width, bottom);
//                } else if (right > screenWidth) {
//                    layout(screenWidth - width, top, screenWidth, bottom);
//                } else if (top < 0) {
//                    layout(left, 0, right, height);
//                } else if (bottom > screenHeight) {
//                    layout(left, screenHeight - height, right, screenHeight);
//                }

                break;
            case MotionEvent.ACTION_UP:


                break;
        }
        return true;
    }
}
