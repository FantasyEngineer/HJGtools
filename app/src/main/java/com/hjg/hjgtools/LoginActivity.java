package com.hjg.hjgtools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.TimeUtils;
import com.hjg.base.view.LoadingDialog;
import com.hjg.hjgtools.databinding.ActivityLoginBinding;
import com.hjg.hjgtools.entity.BaseBean;
import com.hjg.hjgtools.entity.LoginBean;
import com.hjg.hjgtools.request.Loginbiz;
import com.hjg.hjgtools.rx.MyException;
import com.hjg.hjgtools.rx.MyOberver;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxCheckedTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class LoginActivity extends HJGDatabindingBaseActivity<ActivityLoginBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewAction() {
        LoadingDialog loadingDialog = new LoadingDialog(this);


        //监听两个按钮是否都不为空
        InitialValueObservable<CharSequence> nameObservable = RxTextView.textChanges(databinding.username);
        InitialValueObservable<CharSequence> passwordObservable = RxTextView.textChanges(databinding.password);
        Observable.combineLatest(nameObservable, passwordObservable, (charSequence, charSequence2) ->
                StrUtil.isNotEmpty(charSequence.toString()) & StrUtil.isNotEmpty(charSequence2.toString())).subscribe(o -> databinding.login.setEnabled(o));


        //规定时间内只响应第一个
        RxView.clicks(databinding.login).throttleFirst(200, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(o -> {
            loadingDialog.show();
            Loginbiz.getAppbiz().login().subscribe(new MyOberver<LoginBean>() {
                @Override
                public void onNewNext(LoginBean loginBean) {
                    loadingDialog.dismiss();
                    String inputUserName = databinding.username.getText().toString();
                    String inputPassword = databinding.password.getText().toString();
                    if (loginBean.getName().equals(inputUserName) && loginBean.getPassword().equals(inputPassword)) {
                        D.showShort("登录成功");
                        ActivityUtils.startActivity(MainActivity.class);
                        overridePendingTransition(0, 0);
                    } else {
                        D.showShort("账户或密码错误");
                    }
                }

                @Override
                public void onNewError(MyException myException) {
                    loadingDialog.dismiss();
                    D.showShort(myException.getMessage());
                }
            });
        });


    }
}