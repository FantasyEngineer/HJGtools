package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.log.androidlog.L;

/**
 * 贴边view
 * 根据是否在屏幕的一半的位置来进行归边
 * 归边的过程是valueAnimator过渡实现
 */
public class TiebianViewAuto extends androidx.appcompat.widget.AppCompatTextView {

    private int downY, downX;
    private int screenWidth, screenHeight;
    private ValueAnimator valueAnimator;

    public TiebianViewAuto(@NonNull Context context) {
        super(context);
        init();
    }

    public TiebianViewAuto(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TiebianViewAuto(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
                downY = currentY;
                downX = currentX;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    L.d("取消动画");
                    valueAnimator.cancel();
                }
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

                if (tiebianLocation == LEFT) {/*左边*/
                    layout(0, top, getWidth(), bottom);
                } else if (tiebianLocation == RIGHT) {/*右边*/
                    layout(screenWidth - getWidth(), top, screenWidth, bottom);
                } else {/*可以随意拖动*/
                    layout(left, top, right, bottom);
                }
                break;
            case MotionEvent.ACTION_UP:
                int width = getWidth();
                int centerX = getLeft() + width / 2;

                if (centerX > screenWidth / 2) {//归到右边
                    //使用valueAnimator
                    int leftOrigin = getLeft();//抬手的时候控件距离父布局的距离
                    int distanceRight = screenWidth - getRight();//距离最右边的距离
                    valueAnimator = ValueAnimator.ofInt(0, distanceRight);
                    valueAnimator.setDuration(200);
                    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer animatedValue = (Integer) animation.getAnimatedValue();
                            layout(leftOrigin + animatedValue, getTop(), leftOrigin + getWidth() + animatedValue, getBottom());
                        }
                    });
                    valueAnimator.start();
                } else {//归到左边
                    //使用valueAnimator
                    int leftOrigin = getLeft();//抬手的时候控件距离父布局的距离
                    valueAnimator = ValueAnimator.ofInt(0, leftOrigin);//距离需要慢慢变小
                    valueAnimator.setDuration(200);
                    valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            Integer animatedValue = (Integer) animation.getAnimatedValue();
                            layout(leftOrigin - animatedValue, getTop(), leftOrigin + getWidth() - animatedValue, getBottom());
                        }
                    });
                    valueAnimator.start();

                }
                break;
        }
        return true;
    }

    public static int LEFT = 0;//左边
    public static int RIGHT = 1;//右边
    public static int AUTO = -1;//自动归边

    private int tiebianLocation = AUTO;

    public void setTieBian(int location) {
        this.tiebianLocation = location;
    }

}
