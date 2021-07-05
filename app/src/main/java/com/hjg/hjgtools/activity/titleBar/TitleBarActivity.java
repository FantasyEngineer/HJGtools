package com.hjg.hjgtools.activity.titleBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.BarUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityTitleBarBinding;

import java.util.ArrayList;
import java.util.List;

public class TitleBarActivity extends HJGDatabindingBaseActivity<ActivityTitleBarBinding> {

    private ArrayList<String> titlesList;

    @Override
    protected int getContentID() {
        return R.layout.activity_title_bar;
    }

    @Override
    protected void initViewAction() {

        //设置透明
        BarUtils.setColorNoTranslucent(this, ResUtils.getColor(R.color.black));

        //支持toolbar
        initToolBar();

        //初始化选项卡
        initTabs();

        //初始化viewpager以及绑定tab选项卡
        initViewPager();
    }

    private void initToolBar() {
        //将toolbar替换原本的actionbar
        setSupportActionBar(databinding.toolBar);
//        BarUtils.setTitleCenter(databinding.toolBar, "头部布局的联动");
        databinding.left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void initTabs() {
        titlesList = ArrayListUtils.newArrayList("精选", "新闻", "巴萨", "购物", "明星", "视频", "健康", "励志", "图文", "本地");
        for (int i = 0; i < titlesList.size(); i++) {
            databinding.tabLayout.addTab(databinding.tabLayout.newTab().setText(titlesList.get(i)));
        }
    }


    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titlesList.size(); i++) {
            fragments.add(ItemFragment.newInstance(1));
        }

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titlesList);
        databinding.viewPager.setAdapter(fragmentAdapter);
        databinding.tabLayout.setupWithViewPager(databinding.viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}