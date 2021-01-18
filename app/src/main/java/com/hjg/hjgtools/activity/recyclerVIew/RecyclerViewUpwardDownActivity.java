package com.hjg.hjgtools.activity.recyclerVIew;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

/**
 * 滑动检测
 */
public class RecyclerViewUpwardDownActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fab.setVisibility(View.VISIBLE);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                L.d("dx----" + dx);
                L.d("dy----" + dy);
                if (Math.abs(dy) < 30) {//手指颤抖距离
                    return;
                }

                if (dy < 0 && fab.isHidden()) { // 当前处于上滑状态
                    D.showShort("当前处于上滑状态");
                    fab.show(true);
                } else if (dy > 0 && fab.isShown()) { // 当前处于下滑状态
                    D.showShort("当前处于下滑状态");
                    fab.hide(true);
                }
            }
        });
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        return null;
    }
}