package com.hjg.hjgtools.activity.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.hjg.base.listener.OnItemClickListener;
import com.hjg.base.util.D;
import com.hjg.base.util.RandomUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.mvvm.User;
import com.hjg.hjgtools.databinding.ActivityDatabindingBinding;

import java.util.ArrayList;

public class DatabindingActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityDatabindingBinding activityDatabindingBinding;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDatabindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
        activityDatabindingBinding.setOnclickListener(this);

        initRecyclerView();


    }

    private void initRecyclerView() {
        activityDatabindingBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityDatabindingBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());

        //包装数据
        initData();
        //添加数据adater
        DatabindAdapter databindAdapter = new DatabindAdapter(this, users);
        activityDatabindingBinding.recyclerView.setAdapter(databindAdapter);
        databindAdapter.setOnItemClickListener(new OnItemClickListener<User>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, User user, int position) {
                D.showShort(user.name.get() + "==" + user.age.get() + "==位置==" + position);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, User user, int position) {
                return false;
            }

        });
    }

    private void initData() {
        users = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            User user = new User("侯继国" + RandomUtils.generateLowerString(3), i);
            users.add(user);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                D.showLong("获取数据");
                activityDatabindingBinding.setProduct(new ProductBean("裤子", "超级大号"));
                break;
        }

    }
}