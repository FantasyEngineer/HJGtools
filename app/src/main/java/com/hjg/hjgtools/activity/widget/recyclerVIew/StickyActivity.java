package com.hjg.hjgtools.activity.widget.recyclerVIew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjg.base.adapter.BaseAdapter;
import com.hjg.base.adapter.BaseViewHolder;
import com.hjg.base.base.HJGDatabindingBaseActivity;
import com.hjg.base.util.ArrayListUtils;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.databinding.ActivityStickyBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StickyActivity extends HJGDatabindingBaseActivity<ActivityStickyBinding> {

    @Override
    protected int getContentID() {
        return R.layout.activity_sticky;
    }

    @Override
    protected void initViewAction() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        databinding.recyclerView.setLayoutManager(linearLayoutManager);
        databinding.recyclerView.addOnScrollListener(new RvScrollListener());

        //构造每天做了的事情数据

        ArrayList<DothingInfo> dothingInfos = new ArrayList<>();
        dothingInfos.add(new DothingInfo("2021年1月1号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "7:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "8:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "9:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "10:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "11:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月1号", "12:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "13:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月2号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月15号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));
        dothingInfos.add(new DothingInfo("2021年1月16号", "6:00", "不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班不想上班"));


        databinding.recyclerView.setAdapter(new BaseAdapter<DothingInfo>(activity, R.layout.item_sticky, dothingInfos) {
            @Override
            public void convert(BaseViewHolder holder, DothingInfo s, int position) {

                LinearLayout headLayout = holder.getView(R.id.headLayout);
                TextView tvHead = holder.getView(R.id.tvHead);
                tvHead.setText(s.getYmd());


                TextView tvTime = holder.getView(R.id.tvTime);
                tvTime.setText(s.getTime());

                TextView tvThing = holder.getView(R.id.tvThing);
                tvThing.setText(s.getThing());

                holder.itemView.setContentDescription(s.getYmd());

                if (position == 0) {
                    headLayout.setVisibility(View.VISIBLE);
                    holder.itemView.setTag(FIRST_STICKY_VIEW);
                } else {
                    if (s.getYmd().equals(dothingInfos.get(position - 1).getYmd())) {//当前Item头部与上一个Item头部相同，则隐藏头部
                        headLayout.setVisibility(View.GONE);
                        holder.itemView.setTag(NONE_STICKY_VIEW);
                    } else {
                        headLayout.setVisibility(View.VISIBLE);
                        holder.itemView.setTag(HAS_STICKY_VIEW);
                    }
                }

            }
        });
        //首个粘性头部的书写
        databinding.tvStickyHeader.setText(dothingInfos.get(0).getYmd());


    }

    public static final int FIRST_STICKY_VIEW = 1;
    public static final int HAS_STICKY_VIEW = 2;
    public static final int NONE_STICKY_VIEW = 3;

    public class DothingInfo {

        private String ymd;
        private String time;
        private String thing;

        public DothingInfo(String ymd, String time, String thing) {
            this.ymd = ymd;
            this.time = time;
            this.thing = thing;
        }

        public String getYmd() {
            return ymd;
        }

        public void setYmd(String ymd) {
            this.ymd = ymd;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getThing() {
            return thing;
        }

        public void setThing(String thing) {
            this.thing = thing;
        }
    }


    /**
     * 监听RecyclerView滚动，实现粘性头部
     */
    private class RvScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            View stickyInfoView = recyclerView.getChildAt(0);//获取头部View
            if (stickyInfoView != null) {
                databinding.stickyHeader.setVisibility(View.VISIBLE);
                databinding.tvStickyHeader.setText(String.valueOf(stickyInfoView.getContentDescription()));
            }
            View transInfoView = recyclerView.findChildViewUnder(databinding.stickyHeader.getMeasuredWidth() / 2
                    , databinding.stickyHeader.getMeasuredHeight() + 1);//位于headerView下方的itemView（该坐标是否在itemView内）
            if (transInfoView != null && transInfoView.getTag() != null) {
                int tag = (int) transInfoView.getTag();
                int deltaY = transInfoView.getTop() - databinding.stickyHeader.getMeasuredHeight();
                if (tag == HAS_STICKY_VIEW) {//当Item包含粘性头部一类时
                    if (transInfoView.getTop() > 0) {//当Item还未移动出顶部时
                        databinding.stickyHeader.setTranslationY(deltaY);
                    } else {//当Item移出顶部，粘性头部复原
                        databinding.stickyHeader.setTranslationY(0);
                    }
                } else {//当Item不包含粘性头部时
                    databinding.stickyHeader.setTranslationY(0);
                }
            }
        }
    }
}