package com.hjg.hjgtools.activity.conflict;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityScrollViewRecyclerViewBinding;

import java.util.ArrayList;

public class ScrollViewRecyclerViewActivity extends HJGDatabindingBaseActivity<ActivityScrollViewRecyclerViewBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_scroll_view_recycler_view;
    }

    @Override
    protected void initViewAction() {
        ArrayList list = ArrayListUtils.newArrayList("这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里recyclerView设置的高度为wrapcontent属性"
                , "这里好像没有问题，待以后复现");

        databinding.recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        databinding.recyclerView.addItemDecoration(new MyDividerItemDecoration());
        databinding.recyclerView.setHasFixedSize(false);//当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。局部刷新，而不是全局刷新
        databinding.recyclerView.setAdapter(new BaseAdapter<String>(activity, R.layout.item_tv, list) {
            @Override
            public void convert(BaseViewHolder holder, String s, int position) {
                TextView textView = holder.getView(R.id.tv);
                textView.setText(s);
            }
        });

    }
}