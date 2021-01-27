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
        recyclerListBeans.add(new RecyclerListBean("map", "Map一般用于对原始的参数进行加工处理，返回值还是基本的类型，可以在subscribe中使用(适用)的类型"));
        recyclerListBeans.add(new RecyclerListBean("flatMap", "一般用于输出一个Observable，而其随后的subscribe中的参数也跟Observable中的参数一样，是无序的"));
        recyclerListBeans.add(new RecyclerListBean("concatMap", "一般用于输出一个Observable，与flatmap相同，返回的是有序的"));
        recyclerListBeans.add(new RecyclerListBean("throttleFirst", "节流，允许设置一个时间长度，之后它会发送固定时间长度内的第一个事件，而屏蔽其它事件，在间隔达到设置的时间后，可以再发送下一个事件。"));
        recyclerListBeans.add(new RecyclerListBean("Sample", "定时查看一个Observable，然后发射自上次采样以来它最近发射的数据。在某些实现中，有一个 ThrottleFirst 操作符的功能类似，但不是发射采样期间的最近的数据，而是发射在那段时间内的第一项数据。" +
                "RxJava将这个操作符实现为 sample 和 throttleLast"));
        recyclerListBeans.add(new RecyclerListBean("throttleLatest", "节流，一"));
        recyclerListBeans.add(new RecyclerListBean("debounce", "防抖,当一定时间内没有触发再这个事件时，才真正去触发事件"));
        recyclerListBeans.add(new RecyclerListBean("Distinct", "去重,所有重复的数据都会被过滤掉"));
        recyclerListBeans.add(new RecyclerListBean("Filter", "过滤,只会返回满足过滤条件的数据"));
        recyclerListBeans.add(new RecyclerListBean("ElementAt", "返回指定位置的数据"));
        recyclerListBeans.add(new RecyclerListBean("First", "只会返回第一条数据，并且还可以返回满足条件的第一条数据"));
        recyclerListBeans.add(new RecyclerListBean("Last", "只返回最后一条满足条件的数据。"));
        recyclerListBeans.add(new RecyclerListBean("Skip", "将源Observable发射的数据过滤掉前n项。"));
        recyclerListBeans.add(new RecyclerListBean("Take", "只取前n项。"));


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

            case "debounce":
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