package com.hjg.hjgtools.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HBaseActivity;
import com.hjg.base.listener.OnEasyItemClickListener;
import com.hjg.base.util.ActivityUtils;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.base.util.log.androidlog.L;
import com.hjg.base.view.MyDividerItemDecoration;
import com.hjg.hjgtools.MainActivity;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.adapter.MulRecyclerViewAdapter;
import com.hjg.hjgtools.entity.RecyclerListBean;

import java.util.ArrayList;

import static com.hjg.hjgtools.MainActivity.TITLE;

//recyclerView布局activity基类（多个布局承载）
public abstract class HJGBaseRecyclerMulItemActivity extends HBaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration());
        MulRecyclerViewAdapter mulRecyclerViewAdapter = new MulRecyclerViewAdapter(activity, structureData());
        mulRecyclerViewAdapter.setOnItemClickListener(new OnEasyItemClickListener<RecyclerListBean>() {
            @Override
            public void onItemClick(View view, RecyclerListBean recyclerListBean, int position) {
                onActivityItemClick(position, recyclerListBean);

                if (recyclerListBean.getaClass() != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(TITLE, recyclerListBean.getTitle());
                    ActivityUtils.startActivity(recyclerListBean.getaClass(), bundle);
                }
            }
        });
        recyclerView.setAdapter(mulRecyclerViewAdapter);
    }

    protected void onActivityItemClick(int position, RecyclerListBean recyclerListBean) {

    }

    public abstract ArrayList<RecyclerListBean> structureData();

}
