package com.hjg.hjgtools;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.hjg.base.base.HJGBaseActivity;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.CodeUtils;
import com.hjg.base.util.ViewUtils;
import com.hjg.hjgtools.databinding.ActivitySecondBinding;

public class SecondActivity extends HJGDatabindingBaseActivity<ActivitySecondBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_second;
    }

    @Override
    protected void initViewAction() {
//        Bitmap bitmap = ViewUtils.getBitmapFromView(databinding.viewLine);
//
//
//        databinding.image.setImageBitmap(bitmap)

        databinding.image.setImageBitmap(CodeUtils.getInstance().createBitmap());
        databinding.showCode.setText(CodeUtils.getInstance().getCode());

    }
}