package com.hjg.base.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class HJGDatabindingBaseFragment<T extends ViewDataBinding> extends BaseFragment {
    protected Activity mActivity;
    protected T databinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        databinding = (T) DataBindingUtil.inflate(inflater, getContentID(), container, false);
        initViewAction(databinding.getRoot());
        return databinding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }

    protected abstract int getContentID();

    protected abstract void initViewAction(View view);

}
