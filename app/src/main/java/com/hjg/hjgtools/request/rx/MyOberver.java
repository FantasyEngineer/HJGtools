package com.hjg.hjgtools.request.rx;

import com.hjg.base.util.StrUtil;
import com.hjg.hjgtools.entity.BaseBean;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class MyOberver<T> implements Observer<BaseBean<T>> {
    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseBean<T> baseBean) {
        if (null != baseBean && baseBean.isSuccess() && baseBean.getData() != null) {
            onNewNext(baseBean.getData());
        } else {
            onError(new Throwable(baseBean.getCode() + "||" + baseBean.getMsg()));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (StrUtil.isNotEmpty(e.getMessage()) && e.getMessage().contains("=")) {
            onNewError(new MyException(e.getMessage().split("=")[0], e.getMessage().split("=")[1]));
        } else {
            //网络部分异常直接吐司
            onNewError(new MyException("9999", e.getMessage()));
        }
    }

    @Override
    public void onComplete() {

    }


    //新的报错next
    public abstract void onNewNext(T t);

    public abstract void onNewError(MyException myException);
}
