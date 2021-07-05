package com.hjg.hjgtools.activity.touchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityCustomViewBinding;

public class CustomViewActivity extends HJGDatabindingBaseActivity<ActivityCustomViewBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initViewAction() {

    }
}