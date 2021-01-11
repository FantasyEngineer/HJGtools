package com.hjg.hjgtools;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hjg.base.base.HJGBaseActivity;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.P;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.ScreenUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.databinding.ActivitySplashBinding;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends HJGDatabindingBaseActivity<ActivitySplashBinding> {

    private Disposable jumpIntervalDisposable;
    private int titalNum = 3;

    @Override
    protected int getContentID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViewAction() {
        Observable.interval(1, TimeUnit.SECONDS).take(4).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                jumpIntervalDisposable = d;
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                String backWardsCount = String.valueOf(titalNum - aLong);
                L.d(backWardsCount);
                databinding.tvJump.setVisibility(View.VISIBLE);
                databinding.tvJump.setText(String.format(ResUtils.getString(R.string.splash_jump), backWardsCount));
                if (backWardsCount.equals("0")) {
                    startActivity();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //按钮跳转
        RxView.clicks(databinding.tvJump).throttleFirst(1, TimeUnit.SECONDS).subscribe(o -> {
            if (!jumpIntervalDisposable.isDisposed()) {
                jumpIntervalDisposable.dispose();
            }
            startActivity();
        });
    }

    /**
     * 跳转页面
     */
    private void startActivity() {
        if (StrUtil.isNotEmpty(P.getString(Config.LOGIN_STATUS))) {
            ActivityUtils.startActivity(MainActivity.class);
        } else {
            ActivityUtils.startActivity(LoginActivity.class);
        }
        overridePendingTransition(R.anim.enter_zoom_in, R.anim.exit_fade);

        finish();
    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }

    @Override
    protected boolean isShowBottomNavigation() {
        return false;
    }

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}