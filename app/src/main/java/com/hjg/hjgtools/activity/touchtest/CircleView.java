package com.hjg.hjgtools.activity.touchtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjg.base.util.log.androidlog.L;

/**
 * 自定义圆形
 */
public class CircleView extends View {
    private Paint mCirclePaint;
    //圆心
    private int mCircleX, mCircleY;
    private int mRadius;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    public void init() {
        mCirclePaint = new Paint();
        //设置抗锯齿
        mCirclePaint.setAntiAlias(true);
        //设置防抖
        mCirclePaint.setDither(true);
        //设置颜色
        mCirclePaint.setColor(0xff000000);
        //设置样式为填充
        mCirclePaint.setStyle(Paint.Style.FILL);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //画圆形
        canvas.drawCircle(mCircleX, mCircleY, 100, mCirclePaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取控件的宽高实际值
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        //获取控件宽高的意图
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY://精确值或者是matchParent
                L.d("CircleView" + "MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.AT_MOST://度量规范模式:子节点可以想多大就多大到指定的大小。
                L.d("CircleView" + "MeasureSpec.AT_MOST");

                break;
            case MeasureSpec.UNSPECIFIED://父对象没有施加任何约束的孩子。它想要什么尺寸都行。
                L.d("CircleView" + "MeasureSpec.UNSPECIFIED");
                break;
        }


        //圆心取中心位置
        mCircleX = width / 2;
        mCircleY = height / 2;
        //半径取两者最小值
        mRadius = Math.min(mCircleX, mCircleY);
    }
}
