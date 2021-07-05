package com.hjg.hjgtools.activity.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


public class ProductBean extends BaseObservable {

    private String name;
    private String size;

    public ProductBean(String name, String size) {
        this.name = name;
        this.size = size;
    }

    //必须要注解，才能生成对应的BR
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);//手动刷新
    }

    @Bindable
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
        notifyPropertyChanged(BR.size);//手动刷新
    }

}
