package com.hjg.base.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;

public abstract class HJGDatabindingBaseActivity<T> extends HTitleActivity {


    protected T databinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = (T) DataBindingUtil.setContentView(this, getContentID());
        try {
            initViewAction();
        } catch (Exception e) {
//            D.showShort(e.getMessage());
            L.d(e.getMessage());
        }
    }

    protected abstract int getContentID();


    /**
     * 初始化view，以及data
     */
    protected abstract void initViewAction();

}
