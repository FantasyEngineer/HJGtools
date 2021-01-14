package com.hjg.hjgtools.base;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.base.HTitleActivity;
import com.hjg.base.listener.OnEasyItemClickListener;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.adapter.MulRecyclerViewAdapter;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

//recyclerView布局activity基类（多个布局承载）
public abstract class HJGBaseRecyclerMulItemActivity extends HTitleActivity {

    private RecyclerView recyclerView;
    private TextView tvDes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);

        recyclerView = findViewById(R.id.recyclerView);
        tvDes = findViewById(R.id.tv_des);
        tvDes.setText(setDesString());
        tvDes.setVisibility(StrUtil.isEmpty(setDesString().toString()) ? View.GONE : View.VISIBLE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        MulRecyclerViewAdapter mulRecyclerViewAdapter = new MulRecyclerViewAdapter(activity, structureData());
        mulRecyclerViewAdapter.setOnItemClickListener((OnEasyItemClickListener<RecyclerListBean>) (view, recyclerListBean, position) -> {
            onActivityItemClick(position, recyclerListBean);
            if (recyclerListBean.getaClass() != null) {
                Bundle bundle = new Bundle();
                bundle.putString(TITLE, recyclerListBean.getTitle());
                ActivityUtils.startActivity(recyclerListBean.getaClass(), bundle);
            }
        });
        recyclerView.setAdapter(mulRecyclerViewAdapter);
    }

    /**
     * 初始化页面的描述语句
     *
     * @return
     */
    protected CharSequence setDesString() {
        return "null";
    }

    /**
     * 获取描述文字
     *
     * @return
     */
    protected String getDesString() {
        return tvDes.toString();
    }

    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {

    }

    public abstract ArrayList<RecyclerListBean> structureData();

}
