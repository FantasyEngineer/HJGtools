package com.hjg.mvp.main;

import com.hjg.mvp.base.BasePresent;
import com.hjg.mvp.base.BaseView;

public class MainPresenter extends BasePresent<MainView> {
    public MainPresenter(BaseView baseView) {
        super(baseView);
    }

    public void request(){
        //我这边进行数据的请求
        view.showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                view.getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        view.dissLoadind();
                        view.setOK();
                    }
                });
            }
        }


        ).start();
    }
}
