package com.hjg.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hjg.mvc.R;
import com.hjg.mvp.base.BaseActivity;
import com.hjg.mvp.main.MainPresenter;
import com.hjg.mvp.main.MainView;

public class MvpActivity extends BaseActivity implements MainView {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        textView = findViewById(R.id.text);


        MainPresenter mainPresenter = new MainPresenter(this);
        //发起请求
        mainPresenter.request();
        textView.setText("正在发起请求");

    }


    @Override
    public void setOK() {
        //得到请求结果
        textView.setText("得到请求结果");

    }

    @Override
    public void setNOTOK() {

    }
}