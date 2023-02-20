package com.hjg.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.hjg.base.R;

public class HTextView extends androidx.appcompat.widget.AppCompatTextView {
    private AttributeSet attrs = null;
    private Drawable drawableLeft, drawableTop, drawableRight, drawableBottom;
    private int width, height;

    public HTextView(Context context) {
        super(context);
    }

    public HTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
    }

    public HTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.HTextView);
        width = (int) attributes.getDimension(R.styleable.HTextView_height_img, 20);
        height = (int) attributes.getDimension(R.styleable.HTextView_height_img, 20);

        Drawable[] compoundDrawables = getCompoundDrawables();
        drawableLeft = compoundDrawables[0];
        drawableTop = compoundDrawables[1];
        drawableRight = compoundDrawables[2];
        drawableBottom = compoundDrawables[3];

        if (drawableLeft != null) {
            drawableLeft.setBounds(0, 0, width, height);
            drawCenter(canvas, drawableLeft);
        }
        if (drawableTop != null) {
            drawableTop.setBounds(0, 0, width, height);

        }
        if (drawableRight != null) {
            drawableRight.setBounds(0, 0, width, height);

        }
        if (drawableBottom != null) {
            drawableBottom.setBounds(0, 0, width, height);
        }
        setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
        attributes.recycle();
        super.onDraw(canvas);
    }

    private void drawCenter(Canvas canvas, Drawable drawable) {
        float textWidth = getPaint().measureText(getText().toString());
        int drawablePadding = getCompoundDrawablePadding();
        int drawableWidth = 0;
        drawableWidth = drawable.getIntrinsicWidth();
        float bodyWidth = textWidth + drawableWidth + drawablePadding;
        canvas.translate((getWidth() - bodyWidth) / 2, 0);
    }
}
