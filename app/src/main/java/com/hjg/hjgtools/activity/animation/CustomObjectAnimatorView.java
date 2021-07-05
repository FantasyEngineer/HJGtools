package com.hjg.hjgtools.activity.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjg.base.util.CircleUtils;

/*自定义可以使用ObjectAnimator来控制颜色以及半径*/
public class CustomObjectAnimatorView extends View {
    private int color = Color.GREEN;
    private int radius = 100;
    private int angle = 0;//角度


    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
        invalidate();
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        if (mPaint != null) {
            mPaint.setColor(color);
        }
        invalidate();//必须调用
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();//必须调用
    }


    public CustomObjectAnimatorView(Context context) {
        super(context);
        init();
    }

    public CustomObjectAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomObjectAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomObjectAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private Paint mPaint;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//设置抗锯齿
        mPaint.setColor(color);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawCircle(getMeasuredHeight() / 2, getMeasuredHeight() / 2, radius, mPaint);

        /*根据角度获取到圆上坐标*/
        float[] xYinCircle = CircleUtils.getXYinCircle(getMeasuredHeight() / 2, getMeasuredHeight() / 2, radius, angle);
        canvas.drawLine(xYinCircle[0], xYinCircle[1], getMeasuredHeight() / 2, getMeasuredHeight() / 2, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        /*保证是个正方形，取长的那个长度*/
        if (width < height) {
            setMeasuredDimension(height, height);
        } else {
            setMeasuredDimension(width, width);
        }
    }


}
