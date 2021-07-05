package com.hjg.hjgtools.activity.jni;

public class HelloJni {

    static {
        System.loadLibrary("Native-lib");
    }

    //需要在当前目录使用javac -h创建我们的头文件
//    D:\ASproject\HJGTools\app\src\main\java\com\hjg\hjgtools\activity\jni>javac -encoding utf-8 -h ./ HelloJni.java
    public static native String helloC();
}
