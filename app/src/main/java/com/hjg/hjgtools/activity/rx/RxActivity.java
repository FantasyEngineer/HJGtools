package com.hjg.hjgtools.activity.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.activity.widget.ButtonActivity;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

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
        recyclerListBeans.add(new RecyclerListBean("throttleLatest", "节流，单位时间取最后一个"));
        recyclerListBeans.add(new RecyclerListBean("debounce", "防抖,当一定时间内没有触发再这个事件时，才真正去触发事件"));
        recyclerListBeans.add(new RecyclerListBean("Distinct", "去重,所有重复的数据都会被过滤掉"));
        recyclerListBeans.add(new RecyclerListBean("distinctUntilChanged", "去重,连续重复的去除"));
        recyclerListBeans.add(new RecyclerListBean("Filter", "过滤,只会返回满足过滤条件的数据"));
        recyclerListBeans.add(new RecyclerListBean("ElementAt", "返回指定位置的数据"));
        recyclerListBeans.add(new RecyclerListBean("First", "只会返回第一条数据，并且还可以返回满足条件的第一条数据"));
        recyclerListBeans.add(new RecyclerListBean("firstElement", "只会返回第一条数据"));
        recyclerListBeans.add(new RecyclerListBean("blockingFirst", "返回第一条数据后数据流不继续发送"));
        recyclerListBeans.add(new RecyclerListBean("Last", "只返回最后一条满足条件的数据。"));
        recyclerListBeans.add(new RecyclerListBean("lastElement", "只返回最后一条满足条件的数据。"));
        recyclerListBeans.add(new RecyclerListBean("Skip", "将源Observable发射的数据过滤掉前n项，跳过前面n个"));
        recyclerListBeans.add(new RecyclerListBean("Take", "只取前n项"));
        recyclerListBeans.add(new RecyclerListBean("takeUntil", "只取符合条件的数据。与Filter类似"));
        recyclerListBeans.add(new RecyclerListBean("takeLast", "只取后n项"));


        return recyclerListBeans;
    }

    Disposable throttleFirst, throttleLatest;

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
            case "throttleFirst":
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws InterruptedException {
                        emitter.onNext(1);//第一次发送
                        Thread.sleep(500);
                        emitter.onNext(2);//时间没有超过1s 不发送
                        Thread.sleep(500);
                        emitter.onNext(3); //时间为500+500 为1 s 发送
                        Thread.sleep(500);
                        emitter.onNext(4); //时间没有超过1s 不发送
                        Thread.sleep(1500);
                        emitter.onNext(5); //时间超过1 s 发送
                        Thread.sleep(500);
                        emitter.onNext(6);//时间没有超过1s 不发送
                        Thread.sleep(500);
                        emitter.onNext(7);//时间超过1 s 发送

                    }
                }).subscribeOn(Schedulers.computation())
                        .throttleFirst(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> showDialog("throttleFirst处理后：" + integer));
                break;
            case "Sample":
                Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);//第一次不发送
                    Thread.sleep(400);
                    emitter.onNext(2);
                    Thread.sleep(400);
                    emitter.onNext(3);//发送
                    Thread.sleep(900);
                    emitter.onNext(4);//发送
                    Thread.sleep(400);
                    emitter.onNext(5);//不发送
                    Thread.sleep(700);
                    emitter.onNext(6);//发送
                    Thread.sleep(900);
                    emitter.onNext(7);//发送

                })
                        .subscribeOn(Schedulers.computation())
                        .throttleLast(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> showDialog("Sample：" + integer));

                break;
            case "throttleLatest":
                Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);//第一次不发送
                    Thread.sleep(400);
                    emitter.onNext(2);
                    Thread.sleep(400);
                    emitter.onNext(3);//发送
                    Thread.sleep(900);
                    emitter.onNext(4);//发送
                    Thread.sleep(400);
                    emitter.onNext(5);//不发送
                    Thread.sleep(700);
                    emitter.onNext(6);//发送
                    Thread.sleep(900);
                    emitter.onNext(7);//发送

                })
                        .subscribeOn(Schedulers.computation())
                        .throttleLast(1000, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(integer -> showDialog("throttleLatest：" + integer));

                break;
            case "debounce":
                ActivityUtils.startActivity(ButtonActivity.class);
                break;
            case "Distinct":
                Observable.just(1, 1, 2, 1, 3).distinct().toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据1, 1, 2, 1, 3--->" + "Distinct处理" + integers.get(0) + ", " + integers.get(1) + ", " + integers.get(2));
                    }
                });
                break;
            case "distinctUntilChanged":
                Observable.just(1, 1, 1, 2, 1, 3).distinctUntilChanged().toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据1, 1, 1,2, 1, 3--->" + "distinctUntilChanged处理" + integers.get(0) + ", " + integers.get(1) + ", " + integers.get(2) + ", " + integers.get(3));
                    }
                });
                break;
            case "Filter":
                Observable.just(1, 1, 1, 2, 1, 3).filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        if (integer > 1) {
                            return true;
                        }
                        return false;
                    }
                }).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据111213，Filter规则处理大于1的输出为：" + ArrayListUtils.listToString(integers, ','));
                    }
                });
                break;

            case "ElementAt":
                Observable.just(1, 2, 3, 4, 5, 6).elementAt(2).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showDialog("原始数据123456，elementAt获取index为2的数据：" + integer);
                    }
                });
                break;
            case "First":
                Observable.just(1, 2, 3, 4, 5, 6).first(7).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showDialog("原始数据123456，默认数据为7，First操作符取第一个数据：" + integer + "; \n发送数据如果为空的话，" +
                                "返回first函数填入的默认参数7");
                    }
                });
                break;
            case "firstElement":
                Observable.just(1, 2, 3, 4, 5, 6).firstElement().subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showDialog("原始数据123456，默认数据为7，firstElement操作符取第一个数据：" + integer);
                    }
                });
                break;
            case "blockingFirst":
                showDialog("原始数据123456，blockingFirst获取到第一个数据之后，数据流发送停止--->" + Observable.just(1, 2, 3, 4, 5, 6).blockingFirst().intValue() + "");
                break;

            case "Last":
                Observable.just(1, 2, 3, 4, 5, 6).last(7).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showDialog("原始数据123456，默认数据为7，last操作符取最后一个数据：" + integer + "; \n发送数据如果为空的话，" +
                                "返回last函数填入的默认参数7");
                    }
                });
                break;

            case "lastElement":
                Observable.just(1, 2, 3, 4, 5, 6).lastElement().subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showDialog("原始数据123456，默认数据为7，lastElement操作符取最后一个数据：" + integer);
                    }
                });
                break;

            case "Skip":
                Observable.just(1, 2, 3, 4, 5, 6).skip(2).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据123456，Skip跳过2项之后：" + ArrayListUtils.listToString(integers));

                    }
                });
                break;
            case "Take":
                Observable.just(1, 2, 3, 4, 5, 6).take(2).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据123456，take取前两项数据：" + ArrayListUtils.listToString(integers));

                    }
                });
                break;
            case "takeLast":
                Observable.just(1, 2, 3, 4, 5, 6).take(2).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据123456，takeLast取最后两项数据：" + ArrayListUtils.listToString(integers));

                    }
                });
                break;
            case "takeUntil":
                Observable.just(1, 2, 3, 4, 5, 6).takeUntil(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        if (integer > 3) {
                            return true;
                        }
                        return false;
                    }
                }).toList().subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        showDialog("原始数据123456，takeUntil小于3的数据：" + ArrayListUtils.listToString(integers));

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