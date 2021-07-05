package com.hjg.hjgtools.activity.databinding.twoway;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.databinding.ProductBean;
import com.hjg.hjgtools.databinding.ActivityTwoWayBinding;

public class TwoWayActivity extends HJGDatabindingBaseActivity<ActivityTwoWayBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_two_way;
    }

    @Override
    protected void initViewAction() {
        databinding.setProduct(new ProductBean("侯继国", "xxl"));

    }
}