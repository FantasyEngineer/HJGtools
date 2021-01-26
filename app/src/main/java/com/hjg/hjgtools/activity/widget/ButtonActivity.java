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

public class ButtonActivity extends HJGDatabindingBaseActivity<ActivityButtonBinding> implements HandlerUtils.OnReceiveMessageListener {

    @Override
    protected int getContentID() {
        return R.layout.activity_button;
    }

    int count = 0;

    HandlerUtils.HandlerHolder handler;

    @Override
    protected void initViewAction() {
        handler = new HandlerUtils.HandlerHolder(this);
//        RxView.clicks(databinding.btnDuringTimeClickCount).buffer(5, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Object>>() {
//            @Override
//            public void accept(List<Object> objects) throws Exception {
//                if (objects.size() > 0) {
//                    D.showShort("5s内共点击了" + objects.size() + "次");
//                }
//            }
//        });


        RxView.clicks(databinding.btnDuringTimeClickCount)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                handler.sendEmptyMessage(1);
            }
        });

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

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what){
            case 1:
                break;
        }

    }
}