package com.hjg.base.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.hjg.base.R;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.log.androidlog.L;

@SuppressLint("AppCompatCustomView")
public class SmartEditText extends EditText implements View.OnFocusChangeListener {
    public static final int TYPE_EVER = -1;//所有类型都可以输入
    public static final int TYPE_PHONE = 0;//电话号码类型，前面可以多输入几个0
    public static final int TYPE_NUMBER = 1;//可以整数可以小数
    public static final int TYPE_ENGLISH = 2;//英文大小写
    public static final int TYPE_ENGLISH_AND_NUM = 6;//英文大小写与数字
    public static final int TYPE_ENGLISH_CAPITAL = 3;//英文大写
    public static final int TYPE_ENGLISH_LOWCASE = 4;//英文小写
    public static final int TYPE_CHINESE = 5;//中文

    private int inputType;//类型


    private Drawable drawable;
    private Context mContext;
    private boolean focused = false;
    private float delIconSize;//删除图标的大小
    private boolean delIconShow;//是否展示删除图标

    public SmartEditText(Context context) {
        super(context);
        init();
    }

    public SmartEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SmartEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.SmartEditText);
        inputType = attributes.getInt(R.styleable.SmartEditText_input_type_smart, TYPE_EVER);
        drawable = attributes.getDrawable(R.styleable.SmartEditText_del_icon);
        delIconSize = attributes.getDimension(R.styleable.SmartEditText_del_icon_size, 20);
        delIconShow = attributes.getBoolean(R.styleable.SmartEditText_del_icon_show, true);
        attributes.recycle();
        init();
    }

    private void init() {
        switch (inputType) {
            case TYPE_PHONE:
                setInputType(InputType.TYPE_CLASS_NUMBER);//0001213123
                break;
            case TYPE_NUMBER:
                setInputType(8194);//0001213123
                break;
            case TYPE_ENGLISH:
                break;
            case TYPE_ENGLISH_AND_NUM:
                break;
            case TYPE_ENGLISH_CAPITAL:
                break;
            case TYPE_ENGLISH_LOWCASE:
                break;
            case TYPE_CHINESE:
                break;

            default:
                break;
        }

        if (drawable == null) {
            drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_cancel);
        }
        if (!delIconShow) {//如果不展示del，那么drawable要为空
            drawable = null;
        }

        setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDelDrawable();
                if (inputType == TYPE_NUMBER) {//如果是数字类型
                    int posDot = s.toString().indexOf(".");
                    //输入以 “ . ”开头的情况，自动在.前面补0
                    if (s.toString().startsWith(".") && posDot == 0) {
                        s.insert(0, "0");
                        return;
                    }

                    //输入以0开头，后面只能允许输入小数点
                    int length = s.length();
                    if (length == 2) {
                        int posDot0 = s.toString().indexOf("0");
                        if (s.toString().startsWith("0") && posDot0 == 0) {//0开头
                            char char2 = s.charAt(1);
                            if (char2 != '.') {//第二个数不为小数点的话，return掉，不允许输入
                                L.d("第二个数字不为小数点");
                                s.delete(1, 2);//从第1个位置开始，2截止，不包含2，替换的字符为""；
                                return;
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.focused = hasFocus;
        if (focused && length() > 0) {
            setDrawable();
        } else {
            setDrawableNull();
        }
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        this.focused = focused;
        if (focused && length() > 0) {
            setDrawable();
        } else {
            setDrawableNull();
        }
    }

    private void setDelDrawable() {
        if (length() <= 0 || !focused) {
            setDrawableNull();
        } else {
            setDrawable();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判定点到了del按钮
        if (drawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            //判断触摸点是否在水平范围内
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight())) && (x < (getWidth() - getPaddingRight()));
            //获取删除图标的边界，返回一个Rect对象
            Rect rect = drawable.getBounds();
            //获取删除图标的高度
            int height = rect.height();
            int y = (int) event.getY();
            //计算图标底部到控件底部的距离
            int distance = (getHeight() - height) / 2;
            //判断触摸点是否在竖直范围内(可能会有点误差)
            //触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerWidth && isInnerHeight) {
                setText("");
//                if (tmpEditText != null)
//                    tmpEditText.setText("");
            }
        }
        return super.onTouchEvent(event);
    }

//    public void setTmpEditText(EditText tmpEditText) {
//        this.tmpEditText = tmpEditText;
//    }

    private void setDrawable() {
        if (drawable != null) {
            drawable.setBounds(0, 0, SizeUtils.dp2px(delIconSize), SizeUtils.dp2px(delIconSize));
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        }
    }

    private void setDrawableNull() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
    }
}
