package com.hjg.hjgtools.activity.widget;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityEdittextBinding;

public class EdittextActivity extends HJGDatabindingBaseActivity<ActivityEdittextBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_edittext;
    }

    @Override
    protected void initViewAction() {
        //代码设置edittext的drawble背景，通用于其他控件
        databinding.etCodeSetDrawble.setBackground(DrawableUtils.getCornerRaduisDrawable(ResUtils.getColor(R.color.translucent),
                SizeUtils.dp2px(1), ResUtils.getColor(R.color.purple_500), SizeUtils.dp2px(5)));


        float[] raduisii = new float[]{SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), SizeUtils.dp2px(5), 0, 0, 0, 0};
        databinding.etCodeSetDrawbleControl.setBackground(DrawableUtils.getCornerRaduisDrawable(ResUtils.getColor(R.color.translucent),
                SizeUtils.dp2px(1), ResUtils.getColor(R.color.purple_500), raduisii));


        //设置线背景
        databinding.tvLine.setBackground(DrawableUtils.getDividerLine(SizeUtils.dp2px(0.5f), ResUtils.getColor(R.color.purple_500)));


        databinding.tvDes.setText(new TextSpanUtils.Builder("一般情况下，一进入一个页面, EditText默认就会自动获取焦点。")
                .appendNewLine().append("解决之道：在EditText的父级控件中找一个，添加这两个属性：")
                .appendNewLine().append("android:focusable=\"true\"\nandroid:focusableInTouchMode=\"true\"").setForegroundColor(ResUtils.getColor(R.color.red)).create());
    }
}