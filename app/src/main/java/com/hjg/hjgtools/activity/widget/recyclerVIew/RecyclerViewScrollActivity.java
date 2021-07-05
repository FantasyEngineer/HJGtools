package com.hjg.hjgtools.activity.widget.recyclerVIew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hjg.base.util.D;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.hjgtools.base.HJGBaseRecyclerMulItemActivity;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

/**
 * 头部检测
 */
public class RecyclerViewScrollActivity extends HJGBaseRecyclerMulItemActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                L.d("newState" + newState);
                //newState为0说明是滑动结束 ，1是手指按住滑动，2是脱离手指惯性滑动

                //方法1
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) { //当前状态为停止滑动
//                    if (!recyclerView.canScrollVertically(1)) { // 到达底部
//                        D.showShort("到达底部");
//                    } else if (!recyclerView.canScrollVertically(-1)) { // 到达顶部
//                        D.showShort("到达顶部");
//                    }
//                }

                //方法2 完全可见的下标+1 ？= totalItem ，如果不等于，说明没有到底 如果第一个可见的item的序号是0，那么就是到头

                if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0) {
                    D.showShort("到达顶部");
                } else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == linearLayoutManager.getItemCount()) {
                    D.showShort("到达底部");
                }

                L.d(" linearLayoutManager.findFirstCompletelyVisibleItemPosition()--" + linearLayoutManager.findFirstCompletelyVisibleItemPosition());
                L.d(" linearLayoutManager.findLastCompletelyVisibleItemPosition()--" + linearLayoutManager.findLastCompletelyVisibleItemPosition());
                L.d(" linearLayoutManager.findFirstVisibleItemPosition()--" + linearLayoutManager.findFirstVisibleItemPosition());
                L.d(" linearLayoutManager.findLastVisibleItemPosition()--" + linearLayoutManager.findLastVisibleItemPosition());
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public ArrayList<RecyclerListBean> structureData() {
        return null;
    }

}