package com.hjg.hjgtools.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
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
    }
}