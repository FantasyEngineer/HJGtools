package com.hjg.mvc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hjg.base.util.D;
import com.hjg.mvc.listener.OnRequestListener;
import com.hjg.mvc.model.MainModelImp;

public class MainActivity extends AppCompatActivity implements OnRequestListener {

    private MainModelImp mainModelImp;
    TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);
        mainModelImp = new MainModelImp();
        mainModelImp.setListener(this);

    }

    /*发起请求*/
    public void request(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mainModelImp.login();
            }
        }).start();
    }

    //得到返回结果，这里也会有逻辑部分，比如切换线程，activity的职能不仅仅是view，还有control的功能
    @Override
    public void onSuccess(String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                viewById.setText(data);
            }
        });

    }

    @Override
    public void onFail(Exception e) {

    }
}