package com.hjg.hjgtools.activity.touchtest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.hjg.base.util.log.androidlog.L;

public class CustomRelativeLayout extends RelativeLayout {
    public CustomRelativeLayout(Context context) {
        super(context);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //只测试宽对于ziview的影响
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode) {
//            EXACTLY
//            明确约束对子view大小由specSize的值来决定。
//
//            AT_MOST
//            子view最多只能是specSize中指定的大小。
//
//            UNSPECIFIED
//            对子view没有约束。
            case MeasureSpec.EXACTLY://精确值或者是matchParent
                L.d("CustomRelativeLayout" + "MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.AT_MOST://度量规范模式:子节点可以想多大就多大到指定的大小。
                L.d("CustomRelativeLayout" + "MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.UNSPECIFIED://父对象没有施加任何约束的孩子。它想要什么尺寸都行。
                L.d("CustomRelativeLayout" + "MeasureSpec.UNSPECIFIED");
                break;
        }

        //由于设置的宽的属性是matchparent 或者固定值200dp，  宽的模式都是MeasureSpec.EXACTLY
        //由于设置的宽的属性是wrapContent，  宽的模式都是MeasureSpec.AT_MOST

    }
}
