package com.hjg.hjgtools.activity.mvvm;

import androidx.fragment.app.Fragment;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.mvvm.adapter.CarPageAdapter;
import com.hjg.hjgtools.activity.titleBar.FragmentAdapter;
import com.hjg.hjgtools.activity.titleBar.ItemFragment;
import com.hjg.hjgtools.databinding.ActivityMvvmBinding;

import java.util.ArrayList;

public class MvvmActivity extends HJGDatabindingBaseActivity<ActivityMvvmBinding> {

    private ArrayList<String> tabTitles;

    @Override
    protected int getContentID() {
        return R.layout.activity_mvvm;
    }

    @Override
    protected void initViewAction() {
        initViewPager();
        initTabLayout();
        initViewPagerFragmet();
    }

    private void initViewPager() {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        cars.add("https://gitee.com/jimmy_hou/hjginter-face/raw/d6f772b53738ded7805fa9c3182e952ef42e9234/1login/%E5%9B%BE%E5%B1%82%2023.png");
        CarPageAdapter carPageAdapter = new CarPageAdapter(this, cars);
        //设置ViewPager切换效果，即实现画廊效果
        databinding.viewPager.setPageTransformer(false, new ScalePageTransformer());
        databinding.viewPager.setAdapter(carPageAdapter);
        databinding.viewPager.setOffscreenPageLimit(100);
    }


    private void initTabLayout() {
        tabTitles = new ArrayList<>();
        tabTitles.add("SUV");
        tabTitles.add("轿车");
        tabTitles.add("性能车");
        tabTitles.add("新能源车");
        tabTitles.add("皮卡");

        for (int i = 0; i < tabTitles.size(); i++) {
            databinding.tabLayout.addTab(databinding.tabLayout.newTab().setText(tabTitles.get(i)));
        }

    }

    private void initViewPagerFragmet() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        fragments.add(BlankFragment.newInstance());
        databinding.viewPagerFragmet.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments, tabTitles));
        databinding.tabLayout.setupWithViewPager(databinding.viewPagerFragmet);
    }


    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }
}