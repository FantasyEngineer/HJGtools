package com.hjg.hjgtools.activity.widget.recyclerVIew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityTabBinding;

import java.util.ArrayList;

public class TabActivity extends HJGDatabindingBaseActivity<ActivityTabBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_tab;
    }

    @Override
    protected void initViewAction() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 4);
        databinding.recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList data = ArrayListUtils.newArrayList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
        databinding.recyclerView.setAdapter(new BaseAdapter<String>(activity, R.layout.item_image_tab, data) {
            @Override
            public void convert(BaseViewHolder holder, String s, int position) {

            }
        });

    }
}