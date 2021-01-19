package com.hjg.hjgtools.activity.system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;

import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.TextSpanUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SystemActivity extends HJGBaseRecyclerMulItemActivity {

    @Override
    public ArrayList<RecyclerListBean> structureData() {

        ArrayList<RecyclerListBean> recyclerListBeans = new ArrayList<>();
        recyclerListBeans.add(strucData("手机系统版本", "1111"));
        recyclerListBeans.add(strucData("分辨路", "1111"));
        recyclerListBeans.add(strucData("手机系统版本", "1111"));
        recyclerListBeans.add(strucData("手机系统版本", "1111"));
        return recyclerListBeans;
    }

    @NotNull
    private RecyclerListBean strucData(String cateName, String content) {
        return new RecyclerListBean(new TextSpanUtils.Builder(cateName + " : ").setBold().appendTab().append(content).setForegroundColor(ResUtils.getColor(R.color.gray)).create());
    }
}