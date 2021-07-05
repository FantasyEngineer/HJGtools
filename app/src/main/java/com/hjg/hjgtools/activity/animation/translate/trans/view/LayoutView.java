package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.D;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.log.androidlog.L;

/**
 * 使用layout方式实现自身的移动
 * 只能在当前view中移动，移动之后的点击事件可以触发
 */
public class LayoutView extends androidx.appcompat.widget.AppCompatTextView {

    private int downX, downY;
    private int screenWidth, screenHeight;

    public LayoutView(@NonNull Context context) {
        super(context);
        init();
    }

    public LayoutView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

                /*根据差值进行位移*/
                int left = getLeft() + offsetX;
                int top = getTop() + offsetY;
                int right = getRight() + offsetX;
                int bottom = getBottom() + offsetY;
                /*对上下左右做限制，不允许超出屏幕之外*/
                if (left <= 0) {
                    left = 0;
                    right = getWidth();
                }
                if (right >= screenWidth) {
                    right = screenWidth;
                    left = screenWidth - getWidth();
                }
                if (top <= 0) {
                    top = 0;
                    bottom = getHeight();
                }
                if (bottom >= screenHeight) {
                    bottom = screenHeight;
                    top = screenHeight - getHeight();
                }

                layout(left, top, right, bottom);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
