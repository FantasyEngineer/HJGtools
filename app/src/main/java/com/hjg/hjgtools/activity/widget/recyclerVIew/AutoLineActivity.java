package com.hjg.hjgtools.activity.widget.recyclerVIew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.listener.OnItemClickListener;
import com.hjg.base.manager.HjgAutoLineLayoutManager;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.base.util.D;
import com.hjg.base.util.RandomUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityAutoLineBinding;

import java.util.ArrayList;
import java.util.IllformedLocaleException;

public class AutoLineActivity extends HJGDatabindingBaseActivity<ActivityAutoLineBinding> {

    ArrayList<String> datas = ArrayListUtils.newArrayList("科技", "社会", "生活", "男性生活", "女性生活", "两性生活", "电影", "小说", "恐怖电影", "歌曲", "太空", "机械制造", "软件", "小说");
    BaseAdapter<String> baseAdapter;

    @Override
    protected int getContentID() {
        return R.layout.activity_auto_line;
    }

    @Override
    protected void initViewAction() {
        HjgAutoLineLayoutManager lineLayoutManager = new HjgAutoLineLayoutManager();
        databinding.recyclerView.setLayoutManager(lineLayoutManager);
        databinding.recyclerView.setAdapter(baseAdapter = new BaseAdapter<String>(this, R.layout.item_tv_autoline, datas) {
            @Override
            public void convert(BaseViewHolder holder, String s, int position) {
                holder.setText(R.id.tv, s);
            }
        });
        baseAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                baseAdapter.removeData(position);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                return false;
            }
        });
    }


    public void add(View view) {
        ArrayList<String> datas = ArrayListUtils.newArrayList("科技", "社会", "生活", "男性生活", "女性生活", "两性生活", "电影", "小说", "恐怖电影", "歌曲", "太空", "机械制造", "软件", "小说");
        baseAdapter.addMoreData(datas.get(RandomUtils.getRandomDuringTwoNum(0, datas.size())));
    }
}