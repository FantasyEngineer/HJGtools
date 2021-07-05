package com.hjg.hjgtools.activity.animation.translate.trans.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hjg.base.util.D;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.log.androidlog.L;

/**
 * 使用手势gesture手势库
 * 点击
 * 双击
 * 拖动
 * 识别左滑右划，快速左右滑动等 见fling方法
 */
public class GestureDetectorView extends androidx.appcompat.widget.AppCompatTextView {


    public GestureDetectorView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public GestureDetectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GestureDetectorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    GestureDetector gestureDetector;

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {

        /*手势控制*/
        gestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() {
            /*
             *用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
             */
            @Override
            public boolean onDown(MotionEvent e) {
                L.d("onDown");
                return true;//这里返回true，之后才会将事件抛给滑动，抬起，快速滑动等事件中。
            }

            @Override
            public void onShowPress(MotionEvent e) {
                //用户按下的时间超过瞬间，瞬间事件是100毫秒，这里被触发，拖动了，就不执行onShowPress，随后onLongPress将会被触发
                L.d("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应
                //如果是双击的话，这里会触发两次，所以单击事件应当在onSingleTapConfirmed中写。
                //onDown->onSingleTapUp->onSingleTapConfirmed
                L.d("onSingleTapUp");

                return false;//这里返回true，代表当前控件消耗该事件，对于后续函数没有任何影响
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //在屏幕上拖动事件，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
                //（这里要求onDown必须返回true）
                //为“拖动”或“滑动”的过程中不断触发,直到动作结束，无论快慢都会触发。

//                L.d("e1   " + e1.getX() + "---" + e1.getY());
//                L.d("e2   " + e2.getX() + "---" + e2.getY());

                /*当检测是左滑还是右划的时候，应该屏蔽到拖动效果*/
                if (!openCheckLeftOrRightScroll) {
                    /*这两行可以使控件进行移动*/
                    offsetLeftAndRight((int) (e2.getX() - e1.getX()));
                    offsetTopAndBottom((int) (e2.getY() - e1.getY()));
                }

                return false;//这里返回true，代表当前控件消耗该事件，对于后续函数没有任何影响
            }

            @Override
            public void onLongPress(MotionEvent e) {
                //长按触摸屏，超过一定时长，就会触发这个事件
                // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
                //触发顺序：
                //    onDown->onShowPress->onLongPress
                L.d("onLongPress");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // 滑屏，用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
                //缓慢滑动不触发
                //    参数解释：
                //    e1：第1个ACTION_DOWN MotionEvent
                //    e2：最后一个ACTION_MOVE MotionEvent
                //    velocityX：X轴上的移动速度，像素/秒
                //    velocityY：Y轴上的移动速度，像素/秒


                L.d("onFling");

                /*自动归边，这里有问题，拖动控件是不会触发fling的，只有有速度的滑动才会走这个回调函数。*/

                autoStandEdge();

//                左滑还是右划//要监听需要把onScroll中的位移函数去掉。
                if (openCheckLeftOrRightScroll) {
                    checkFlingOriention(e1, e2, velocityX, velocityY);
                }
                return false;//这里返回true，代表当前控件消耗该事件，对于后续函数没有任何影响
            }
        });


        /*双击监听器*/
        gestureDetector.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                //单击事件。用来判定该次点击是SingleTap而不是DoubleTap，如果连续点击两次就是DoubleTap手势，如果只点击一次，系统等待一段时间后没有收到第二次点击则判定该次点击为SingleTap而不是DoubleTap
                // ，然后触发SingleTapConfirmed事件。触发顺序是：OnDown->OnsingleTapUp->OnsingleTapConfirmed
                //关于onSingleTapConfirmed和onSingleTapUp的一点区别：
                // OnGestureListener有这样的一个方法onSingleTapUp，和onSingleTapConfirmed容易混淆。二者的区别是：onSingleTapUp，只要手抬起就会执行，
                // 而对于onSingleTapConfirmed来说，如果双击的话，则onSingleTapConfirmed不会执行。
                /*创建我们的单击事件*/
                L.d("单击事件" + e.getX() + "," + e.getY());
                if (onGestureClickListener != null) {
                    onGestureClickListener.onClick();
                }
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                //双击事件
                L.d("双击事件" + e.getX() + "," + e.getY());
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                //双击间隔中发生的动作。指触发onDoubleTap以后，在双击之间发生的其它动作，包含down、up和move事件；
                return false;
            }
        });

    }

    /**
     * 自动归边
     */
    private void autoStandEdge() {
        int width = getWidth();
        int centerX = getLeft() + width / 2;
        L.d("centerX" + centerX);
        L.d("width" + width);
        int screenWidth = ScreenUtils.getScreenWidth();
        L.d("screenWidth" + screenWidth);

        if (centerX > screenWidth / 2) {//归到右边
            layout(screenWidth - width, getTop(), screenWidth, getBottom());
        } else {//归到左边
            layout(0, getTop(), width, getBottom());
        }
    }

    /*最小移动距离为100，最小的滑动速度是200px/s，才会触发滑动效果*/
    final int FLING_MIN_DISTANCE = 10, FLING_MIN_VELOCITY = 200;

    /**
     * 监听滑动的方向
     * X轴的坐标位移大于FLING_MIN_DISTANCE，且移动速度大于FLING_MIN_VELOCITY个像素/秒
     *
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     */
    private void checkFlingOriention(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float v = e1.getX() - e2.getX();
        if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {

            D.showShort("左滑");
            // Fling left
        } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
            // Fling right
            D.showShort("右滑");

        }

    }

    /*需要将点击事件抛给gesturedetector手势识别*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    private OnGestureClickListener onGestureClickListener;

    public void setOnGestureClickListener(OnGestureClickListener onGestureClickListener) {
        this.onGestureClickListener = onGestureClickListener;
    }

    public interface OnGestureClickListener {
        void onClick();
    }


    boolean openCheckLeftOrRightScroll = false;

    public void checkLeftOrRightScroll(boolean b) {
        openCheckLeftOrRightScroll = b;
    }


}
