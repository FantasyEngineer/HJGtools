package com.hjg.hjgtools.activity.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.util.log.utils.ArrayUtil;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableFromCallable;
import io.reactivex.schedulers.Schedulers;

public class RxCreatActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    public ArrayList<RecyclerListBean> structureData() {
        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(new RecyclerListBean("Create", "使用一个函数从头开始创建一个Observable"));
        recyclerListBeans.add(new RecyclerListBean("Swith", ""));
        recyclerListBeans.add(new RecyclerListBean("Just", "创建一个发射指定值的Observable"));

        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "Range操作符"));
        recyclerListBeans.add(new RecyclerListBean("Range", "创建一个发射特定整数序列的Observable"));
        recyclerListBeans.add(new RecyclerListBean("intervalRange", "创建一个发射特定整数序列的Observable,可以指定延迟时间，发送周期"));

        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "From操作符"));
        recyclerListBeans.add(new RecyclerListBean("fromIterable", "遍历Iterable,list等，类似于for循环输出每一个内容"));
        recyclerListBeans.add(new RecyclerListBean("fromArray", "遍历数组，输出每一个数组的内容"));
        recyclerListBeans.add(new RecyclerListBean("fromCallable", "方便线程中获取到返回值"));
        recyclerListBeans.add(new RecyclerListBean("fromFuture", "方便线程中获取到返回值"));

        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "时间相关"));
        recyclerListBeans.add(new RecyclerListBean("Interval", "创建一个按固定时间间隔发射整数序列的Observable"));
        recyclerListBeans.add(new RecyclerListBean("timer", "创建一个延时发射整数序列的Observable"));

        recyclerListBeans.add(new RecyclerListBean(RecyclerListBean.TYPE_LABER, "以下三个操作符生成的Observable行为非常特殊和受限。测试的时候很有用，有时候也用于结\n" +
                "合其它的Observables，或者作为其它需要Observable的操作符的参数。"));
        recyclerListBeans.add(new RecyclerListBean("Empty", "创建一个不发射任何数据但是正常终止的Observable"));
        recyclerListBeans.add(new RecyclerListBean("Never", "创建一个不发射数据也不终止的Observable"));
        recyclerListBeans.add(new RecyclerListBean("error", "创建一个不发射数据以一个错误终止的Observable"));
        return recyclerListBeans;
    }

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
            case "Create":
                showOrAddBottomSheet("creat函数被订阅之后，才会发送数据流");
                showOrAddBottomSheet("一个形式正确的有限Observable必须尝试调用观察者的onCompleted正好一次或者它的\n" +
                        "onError正好一次，而且此后不能再调用观察者的任何其它方法。");
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("1");
                        Thread.sleep(500);
                        emitter.onNext("2");
                        Thread.sleep(500);
                        emitter.onNext("3");
                        Thread.sleep(500);
                        emitter.onError(new IllegalArgumentException("错误"));
                        emitter.onComplete();
                        emitter.onNext("4");
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String o) {
                        L.d(o);
                        showOrAddBottomSheet(o);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showOrAddBottomSheet("onError");
                    }

                    @Override
                    public void onComplete() {
                        showOrAddBottomSheet("onComplete");
                    }
                });
                break;

            case "Just":
                showOrAddBottomSheet("Just操作符参数：1, 2, 3, 4, 5, 6, 7, 8，将依次输出原始内容，内部调用的为fromArray");
                Observable.just(1, 2, 3, 4, 5, 6, 7, 8).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showOrAddBottomSheet("Just操作符执行结果：" + integer);
                    }
                });
                break;

            case "Range":
                Observable.range(1, 100).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showOrAddBottomSheet("Range操作符执行结果：" + integer);
                    }
                });
                break;

            case "intervalRange":
                showOrAddBottomSheet("intervalRange操作符参数从1到5，首次延时1秒，以后每0.5s输出1次，直到20");
                Observable.intervalRange(1, 5, 1000, 500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        showOrAddBottomSheet("onNext-->" + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        showOrAddBottomSheet("onComplete");
                    }
                });
                break;

            case "fromIterable":
                Observable.fromIterable(ArrayListUtils.newArrayList("1", "2", "3")).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        showOrAddBottomSheet(o);
                    }
                });
                break;
            case "fromArray":
                String[] array = new String[]{"1", "2"};
                Observable.fromArray(array).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String ay) throws Exception {
                        showOrAddBottomSheet(ay);
                    }
                });
                break;
            case "fromCallable":
                showOrAddBottomSheet("fromCallable的参数实现了Callable接口，方法内部返回值为线程返回值。");
                showOrAddBottomSheet("想查看具体情况，请跳转到ThreadActivity中");
                Observable.fromCallable(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        int sum = 0;
                        for (int i = 0; i <= 100; i++) {
                            sum += i;
                        }
                        return sum;
                    }
                }).subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        showOrAddBottomSheet("Callable线程返回值为：" + integer);
                    }
                });

                break;
            case "fromFuture":
                showOrAddBottomSheet("fromFuture的参数实现了Future接口，方法内部返回值为线程返回值。");
                showOrAddBottomSheet("想查看具体情况，请跳转到ThreadActivity中");
                Observable.fromFuture(new Future<Integer>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        return false;
                    }

                    @Override
                    public boolean isCancelled() {
                        return false;
                    }

                    @Override
                    public boolean isDone() {
                        return false;
                    }

                    @Override
                    public Integer get() throws ExecutionException, InterruptedException {
                        int sum = 0;
                        for (int i = 0; i <= 1000; i++) {
                            sum += i;
                        }
                        return sum;
                    }

                    @Override
                    public Integer get(long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
                        return null;
                    }
                }).subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object integer) throws Exception {
                        showOrAddBottomSheet("Future线程返回值为：" + integer);
                    }
                });

                break;

            case "Interval":
                interval = Observable.interval(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                D.showShort("interval循环" + aLong);
                            }
                        });
                break;
            case "timer":
                Observable.timer(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long aLong) throws Exception {
                                showOrAddBottomSheet("timer延时一秒被执行：" + aLong);
                            }
                        });
                break;
            case "6":

                break;
            case "2":

                break;
        }
    }

    Disposable interval;

    @Override
    protected void onDestroy() {
        if (interval != null && !interval.isDisposed()) {
            interval.dispose();
        }

        super.onDestroy();
    }


}