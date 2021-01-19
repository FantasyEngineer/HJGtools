package com.hjg.hjgtools.entity;

import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;

import com.hjg.hjgtools.activity.widget.EdittextActivity;

public class RecyclerListBean {
    public static final int TYPE_LABER = -1;//分类标签 不可点击
    public static final int TYPE_EMPTY = 0;//空布局
    public static final int TYPE_FUNCTION = 1;//分类下的内容展示, 可以点击

    private int type = TYPE_FUNCTION;//类型(分类标签或者可点击的标签)

    private String title;//名称
    private SpannableStringBuilder spannableStringBuilderTitle;//带有文本格式的名称

    private Class aClass;//对应跳转的activity.class
    private String content;//叙述
    private int intDrawable; //图标

    public RecyclerListBean(String title) {
        this.title = title;
    }

    public RecyclerListBean(SpannableStringBuilder spannableStringBuilderTitle) {
        this.spannableStringBuilderTitle = spannableStringBuilderTitle;
    }

    //这里是laber
    public RecyclerListBean(int type, String title) {
        this.type = type;
        this.title = title;
    }


    public RecyclerListBean(int type, String title, Class aClass) {
        this.title = title;
        this.aClass = aClass;
    }

    public RecyclerListBean(String title, Class aClass, String content) {
        this.title = title;
        this.aClass = aClass;
        this.content = content;
    }

    public RecyclerListBean(int type, String title, Class aClass, String content) {
        this.type = type;
        this.title = title;
        this.aClass = aClass;
        this.content = content;
    }

    public RecyclerListBean(String title, Class aClass, String content, int intDrawable) {
        this.title = title;
        this.aClass = aClass;
        this.content = content;
        this.intDrawable = intDrawable;
    }

    public RecyclerListBean(int type, String title, Class aClass, String content, int intDrawable) {
        this.type = type;
        this.title = title;
        this.aClass = aClass;
        this.content = content;
        this.intDrawable = intDrawable;
    }


    public RecyclerListBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public RecyclerListBean(String title, Class aClass) {
        this.title = title;
        this.aClass = aClass;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getIntDrawable() {
        return intDrawable;
    }

    public void setIntDrawable(int intDrawable) {
        this.intDrawable = intDrawable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public SpannableStringBuilder getSpannableStringBuilderTitle() {
        return spannableStringBuilderTitle;
    }

    public void setSpannableStringBuilderTitle(SpannableStringBuilder spannableStringBuilderTitle) {
        this.spannableStringBuilderTitle = spannableStringBuilderTitle;
    }
}
