package com.hjg.hjgtools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class HJGViewGroup extends LinearLayout {
    public HJGViewGroup(Context context) {
        super(context);
    }

    public HJGViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HJGViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HJGViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
    }
}
