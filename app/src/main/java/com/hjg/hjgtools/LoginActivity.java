package com.hjg.hjgtools;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.DrawableUtils;
import com.hjg.base.util.P;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.LoadingDialog;
import com.hjg.base.view.flyco.dialog.widget.NormalDialog;
import com.hjg.hjgtools.databinding.ActivityLoginBinding;
import com.hjg.hjgtools.entity.LoginBean;
import com.hjg.hjgtools.request.Loginbiz;
import com.hjg.hjgtools.request.rx.MyException;
import com.hjg.hjgtools.request.rx.MyOberver;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

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
                        showDialog();
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


    //登录提示
    public void showDialog() {
        NormalDialog normalDialog = new NormalDialog(this);
        normalDialog.title("温馨提示");
        normalDialog.titleTextColor(ResUtils.getColor(R.color.purple_500));
        normalDialog.dividerColor(ResUtils.getColor(R.color.purple_500));
        normalDialog.content("欢迎用户" + databinding.username.getText().toString() + "进入本系统");
        normalDialog.btnNum(1);
        normalDialog.setCanceledOnTouchOutside(false);
        normalDialog.setCancelable(false);
        normalDialog.btnText("确认");
        normalDialog.style(NormalDialog.STYLE_TWO);
        normalDialog.show();
        normalDialog.setOnBtnClickL(() -> {
            normalDialog.dismiss();
            finish();
            P.putString(Config.LOGIN_STATUS, "logined");
        });
    }


}