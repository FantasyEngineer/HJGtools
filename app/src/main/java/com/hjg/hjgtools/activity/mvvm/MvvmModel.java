package com.hjg.hjgtools.activity.mvvm;

import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.hjg.base.util.D;
import com.hjg.hjgtools.BR;

public class MvvmModel extends BaseObservable {

    private int level;
    private String name;


    public void getData(View view) {
        D.showShort("点击了按钮");
        setName("cece");
    }

    @Bindable
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
