package com.hjg.hjgtools.activity.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.HandlerUtils;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityButtonBinding;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ButtonActivity extends HJGDatabindingBaseActivity<ActivityButtonBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_button;
    }

    int count = 0;
    boolean startCheck = true;

    @Override
    protected void initViewAction() {

        //单位时间获取
        RxView.clicks(databinding.btnDuringTimeClickCount)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(@NonNull Object o) throws Exception {
                        return ++count;
                    }
                })
                .debounce(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        L.d(integer);
                        count = 0;//重置
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        L.d(e.getMessage());
                        count = 0;//重置
                    }

                    @Override
                    public void onComplete() {


                    }
                });


//        RxView.clicks(databinding.btnDuringTimeClickCount)
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                ++count;
//                calcuClickNumDuringTime(2);
//            }
//        });

        RxView.clicks(databinding.btnPreventMulClick).throttleFirst(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                D.showShort("操作函数接收到第一个数据后，5s内不再接受数据");
            }
        });

        RxView.clicks(databinding.btnPreventMulClick2).throttleLatest(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                D.showShort("接收到操作函数5s内的最后一个数据" + (++count));
            }
        });
    }

    /**
     * 计算时间内点击了多少次
     *
     * @param second 单位时间秒
     */
    public void calcuClickNumDuringTime(long second) {
        if (!startCheck) {
            return;
        }
        startCheck = false;
        Observable.timer(second, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                D.showShort("5s内共点击了" + count + "次");
                startCheck = true;
                count = 0;
            }
        });
    }
}