package com.hjg.hjgtools.activity.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.ArrayListUtils;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxTransformActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("map", "Map一般用于对原始的参数进行加工处理，返回值还是基本的类型，可以在subscribe中使用(适用)的类型"));
        recyclerListBeans.add(new RecyclerListBean("flatMap", "一般用于输出一个Observable，而其随后的subscribe中的参数也跟Observable中的参数一样，是无序的"));
        recyclerListBeans.add(new RecyclerListBean("concatMap", "一般用于输出一个Observable，与flatmap相同，返回的是有序的"));
        recyclerListBeans.add(new RecyclerListBean("Buffer", "定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。"));
        return recyclerListBeans;
    }

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
            case "map":
                Observable.just(1, 2, 3).map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer) throws Exception {
                        return integer + 1;
                    } 
                }).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据1,2,3--->" + "map处理" + ArrayListUtils.listToString(integers));
                    }
                });
                break;
            case "concatMap":
                Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                        return Observable.just(integer);
                    }
                }).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据1234567890--->" + "concatMap处理(有序)" + ArrayListUtils.listToString(integers));
                    }
                });
                break;
            case "flatMap":
                Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).flatMap(new Function<Integer, ObservableSource<Integer>>() {
                    @Override
                    public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                        return Observable.just(integer);
                    }
                }).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据1234567890--->" + "flatMap处理(无序)" + ArrayListUtils.listToString(integers));
                    }
                });
                break;
            case "Buffer":
                Observable.intervalRange(1, 50, 0, 200, TimeUnit.MILLISECONDS).buffer(3).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Long>>() {
                    @Override
                    public void accept(List<Long> longs) throws Exception {
                        showOrAddBottomSheet(ArrayListUtils.listToString(longs));
                    }
                });
                break;
        }
    }
}