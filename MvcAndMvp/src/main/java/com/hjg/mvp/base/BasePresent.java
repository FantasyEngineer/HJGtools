package com.hjg.mvp.base;

public abstract class BasePresent<T extends BaseView> {
    public T view;

    public BasePresent(BaseView baseView) {
        this.view = (T) baseView;
    }


}
