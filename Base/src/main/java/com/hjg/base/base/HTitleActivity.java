package com.hjg.base.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;

import com.hjg.base.R;
import com.hjg.base.util.StrUtil;


/**
 * 原生ActionBar头部title以及返回按钮的基类
 */
public abstract class HTitleActivity extends HBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //是否添加返回按钮
        if (isShowBackArrow()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            }
        }

        //添加Title
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle != null) {
            String title = bundle.getString(TITLE);
            if (StrUtil.isNotEmpty(title))
                setTitle(title);
        }

    }

    protected boolean isShowBackArrow() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
