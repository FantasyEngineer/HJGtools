package com.hjg.mvc.listener;

public interface OnRequestListener {
    void onSuccess(String data);

    void onFail(Exception e);
}
