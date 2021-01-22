package com.hjg.hjgtools.activity.widget.recyclerVIew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityHorizontalRecyclerViewBinding;

import java.util.ArrayList;

public class HorizontalRecyclerViewActivity extends HJGDatabindingBaseActivity<ActivityHorizontalRecyclerViewBinding> {


    @Override
    protected int getContentID() {
        return R.layout.activity_horizontal_recycler_view;
    }

    @Override
    protected void initViewAction() {

        //创建横向滑动的recyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        databinding.recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList data = ArrayListUtils.newArrayList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
        databinding.recyclerView.setAdapter(new BaseAdapter<String>(activity, R.layout.item_image, data) {

            @Override
            public void convert(BaseViewHolder holder, String s, int position) {

            }
        });


    }
}