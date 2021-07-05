package com.hjg.hjgtools.activity.jni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.R;

/**
 * NDK开发工具包，实现jni调用实现java虚拟机与linux内核方法调用（通信）
 */
public class JniActivity extends HJGDatabindingBaseActivity {

    @Override
    protected int getContentID() {
        return R.layout.activity_jni;
    }

    @Override
    protected void initViewAction() {


    }

    public void callJni(View view) {
        D.showLong(HelloJni.helloC());
        L.e("HelloJni.helloC()" + HelloJni.helloC());
    }


}