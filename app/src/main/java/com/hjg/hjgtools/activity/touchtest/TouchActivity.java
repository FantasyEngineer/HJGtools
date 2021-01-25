package com.hjg.hjgtools.activity.touchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityTouchBinding;

public class TouchActivity extends HJGDatabindingBaseActivity<ActivityTouchBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_touch;
    }

    @Override
    protected void initViewAction() {


    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }

    @Override
    protected boolean isFullScreen() {
        return false;
    }
}