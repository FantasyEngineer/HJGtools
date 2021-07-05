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

        recyclerListBeans.add(new RecyclerListBean("创建操作", RxCreatActivity.class));
        recyclerListBeans.add(new RecyclerListBean("变换操作", RxTransformActivity.class));
        recyclerListBeans.add(new RecyclerListBean("过滤操作", FliterActivity.class));
        recyclerListBeans.add(new RecyclerListBean("结合操作"));
        recyclerListBeans.add(new RecyclerListBean("throttleFirst", "节流，允许设置一个时间长度，之后它会发送固定时间长度内的第一个事件，而屏蔽其它事件，在间隔达到设置的时间后，可以再发送下一个事件。"));
        recyclerListBeans.add(new RecyclerListBean("throttleLatest", "节流，单位时间取最后一个"));

        return recyclerListBeans;
    }

    Disposable throttleFirst, throttleLatest;

    @Override
    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {
        super.onActivityItemClick(position, recyclerListBean);
        switch (recyclerListBean.getTitle()) {
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
                        .subscribe(integer -> showOrAddBottomSheet("throttleFirst处理后：" + integer));
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
                        .subscribe(integer -> showOrAddBottomSheet("throttleLatest：" + integer));

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