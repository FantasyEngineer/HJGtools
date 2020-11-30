package com.hjg.hjgtools.dialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityDialogBinding;

import java.util.ArrayList;

public class DialogActivity extends HJGDatabindingBaseActivity<ActivityDialogBinding> {

    private ArrayList dataList = ArrayListUtils.newArrayList("普通dialog");

    @Override
    protected int getContentID() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void initViewAction() {
        databinding.recyclerView.setAdapter(new BaseAdapter<String>(this, R.layout.item_textview, dataList) {

            @Override
            public void convert(BaseViewHolder holder, String s, int position) {
                TextView textView = holder.getView(R.id.tvContent);
                textView.setText(s);
            }
        });

        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databinding.recyclerView.setHasFixedSize(false);
        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration(this));


    }
}