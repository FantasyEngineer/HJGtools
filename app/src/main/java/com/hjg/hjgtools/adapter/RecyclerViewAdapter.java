package com.hjg.hjgtools.adapter;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.listener.OnRvClickListener;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.SizeUtils;
import com.hjg.base.util.ViewUtils;
import com.hjg.hjgtools.R;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<String> list;
    private OnRvClickListener onRvClickListener;

    public RecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        textView.setPadding(SizeUtils.dp2px(20), SizeUtils.dp2px(20), SizeUtils.dp2px(20), SizeUtils.dp2px(20));
        textView.setTextSize(SizeUtils.px2sp(38));
        textView.setTextColor(ResUtils.getColor(R.color.black));
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        MyViewHolder viewHolder = new MyViewHolder(textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ((TextView) holder.itemView).setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnRvClickListener(OnRvClickListener onRvClickListener) {
        this.onRvClickListener = onRvClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull TextView itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (onRvClickListener != null) {
                    onRvClickListener.onItemClick(itemView, getAdapterPosition());
                }
            });
        }
    }


}
