package com.hjg.hjgtools.entity;

public class RecyclerListBean {
    String title;//名称
    Class aClass;//对应跳转的activity.class
    String content;//叙述

    public RecyclerListBean(String title, Class aClass) {
        this.title = title;
        this.aClass = aClass;
    }

    public RecyclerListBean(String title, Class aClass, String content) {
        this.title = title;
        this.aClass = aClass;
        this.content = content;
    }

    public RecyclerListBean(String title, String content) {
        this.title = title;
        this.content = content;
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
}
