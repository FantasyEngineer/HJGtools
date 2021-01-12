package com.hjg.base.listener;

import android.view.View;
import android.view.ViewGroup;

public interface OnEasyItemClickListener<T> {
    void onItemClick(View view, T t, int position);
}