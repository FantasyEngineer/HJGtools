package com.hjg.hjgtools.activity.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {

        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("throttleFirst"));
        recyclerListBeans.add(new RecyclerListBean("throttleLatest"));


        return recyclerListBeans;
    }

    Disposable throttleFirst, throttleLatest;

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);


        switch (recyclerListBean.getTitle()) {
            case "throttleFirst":
                throttleFirst = Observable.interval(0, 1, TimeUnit.SECONDS).throttleFirst(5, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                mulRecyclerViewAdapter.modifyData(0, new RecyclerListBean("throttleFirst----------->" + aLong));
                            }
                        });
                break;

            case "throttleLatest":
                throttleLatest = Observable.interval(0, 1, TimeUnit.SECONDS).throttleLast(5, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                mulRecyclerViewAdapter.modifyData(1, new RecyclerListBean("throttleLatest----------->" + aLong));
                            }
                        });
                break;
        }
    }


    @Override
    protected void onDestroy() {
        if (throttleFirst != null && !throttleFirst.isDisposed()) {
            throttleFirst.dispose();
        }
        if (throttleLatest != null && !throttleLatest.isDisposed()) {
            throttleLatest.dispose();
        }
        super.onDestroy();
    }
}