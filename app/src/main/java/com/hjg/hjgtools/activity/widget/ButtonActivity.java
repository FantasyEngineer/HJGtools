package com.hjg.hjgtools.activity.widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.DateUtils;
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

    int count = 1;

    @Override
    protected void initViewAction() {

        //单位时间获取总的点击数
        RxView.clicks(databinding.btnDuringTimeClickCount)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(@NonNull Object o) throws Exception {
                        return ++count;
                    }
                })
                .debounce(2, TimeUnit.SECONDS)//5秒之后震动一次
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        D.showShort("2s点击了" + integer + "次");
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

        //防抖,在某段时间内，只发送该段时间内第1次事件(假如一个按钮1秒内点了3次 ,第一次显示,后2次不显示)
        RxView.clicks(databinding.btnPreventMulClick)
                .map(new Function<Object, Integer>() {
                    @Override
                    public Integer apply(@NonNull Object o) throws Exception {
                        return ++count;
                    }
                }).throttleFirst(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                D.showShort("操作函数接收到第一个数据后，5s内不再接受数据");
            }
        });

        //防抖,在某段时间内，只发送该段时间内最后1次事件(假如一个按钮1秒内点了3次 ,最后第一次显示,前两次不显示)
        RxView.clicks(databinding.btnPreventMulClick2).throttleLatest(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                D.showShort("接收到操作函数5s内的最后一个数据" + (++count));
            }
        });


        //连击功能
        RxView.clicks(databinding.btnContinueClick).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                if (DateUtils.getCurrentMillis() - preClickTime[0] < 2000) {
                    ++count;//每点击一次加一次
                } else {
                    count = 1;
                }
                D.showShort("连击" + count + "次");
                preClickTime[0] = DateUtils.getCurrentMillis();
            }
        });


    }

    final long[] preClickTime = {0};


//        RxView.clicks(databinding.btnDuringTimeClickCount)
//                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                ++count;
//                calcuClickNumDuringTime(2);
//            }
//        });
//    /**
//     * 单位时间内计算点击数
//     *
//     * @param second 单位时间秒
//     */
//    boolean startCheck = true;
//
//    public void calcuClickNumDuringTime(long second) {
//        if (!startCheck) {
//            return;
//        }
//        startCheck = false;
//        Observable.timer(second, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//                D.showShort("5s内共点击了" + count + "次");
//                startCheck = true;
//                count = 0;
//            }
//        });
//    }
}