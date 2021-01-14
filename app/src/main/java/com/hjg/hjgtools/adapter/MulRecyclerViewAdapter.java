package com.hjg.hjgtools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.listener.OnEasyItemClickListener;
import com.hjg.base.util.NetUtil;
import com.hjg.base.util.ResUtils;
import com.hjg.base.util.StrUtil;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.entity.RecyclerListBean;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

/**
 * 多布局RecycerView
 */
public class MulRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RecyclerListBean> list;
    private Context context;

    private OnEasyItemClickListener onItemClickListener;

    public MulRecyclerViewAdapter(Context context, List<RecyclerListBean> list) {
        this.list = list;
        this.context = context;
    }

    View emptyView, labelView, functionView;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == RecyclerListBean.TYPE_EMPTY) {
            emptyView = layoutInflater.inflate(R.layout.layout_empty, parent, false);
            EmptyViewHolder viewHolder = new EmptyViewHolder(emptyView);
            return viewHolder;
        } else if (viewType == RecyclerListBean.TYPE_LABER) {
            labelView = layoutInflater.inflate(R.layout.item_tv_category, parent, false);
            LaberViewHolder viewHolder = new LaberViewHolder(labelView);
            return viewHolder;
        } else {
            functionView = layoutInflater.inflate(R.layout.item_function, parent, false);
            FunctionViewHolder viewHolder = new FunctionViewHolder(functionView);
            return viewHolder;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == RecyclerListBean.TYPE_LABER) {
            return RecyclerListBean.TYPE_LABER;
        }
        if (list.get(position).getType() == RecyclerListBean.TYPE_FUNCTION) {
            return RecyclerListBean.TYPE_FUNCTION;
        }
        return RecyclerListBean.TYPE_EMPTY;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerListBean recyclerListBean = list.get(position);
        if (holder instanceof LaberViewHolder) {//标签布局
            LaberViewHolder laberViewHolder = (LaberViewHolder) holder;
            laberViewHolder.tvCategroy.setText(recyclerListBean.getTitle());
        } else if (holder instanceof FunctionViewHolder) {//功能布局
            FunctionViewHolder functionViewHolder = (FunctionViewHolder) holder;
            RxView.clicks(functionViewHolder.itemView).throttleFirst(1500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(functionViewHolder.itemView, recyclerListBean, position);
                    }
                }
            });
            //图片是否展示
            if (recyclerListBean.getIntDrawable() != 0) {
                functionViewHolder.tvIcon.setImageDrawable(ResUtils.getDrawable(recyclerListBean.getIntDrawable()));
                functionViewHolder.tvIcon.setVisibility(View.VISIBLE);
            } else {
                functionViewHolder.tvIcon.setVisibility(View.GONE);
            }
            functionViewHolder.tvTitle.setText(recyclerListBean.getTitle());
            functionViewHolder.tvContent.setText(recyclerListBean.getContent());
            functionViewHolder.tvContent.setVisibility(StrUtil.isNotEmpty(recyclerListBean.getContent()) ? View.VISIBLE : View.GONE);

        } else {//空布局
            //这里没有任何的处理
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LaberViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCategroy;

        public LaberViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategroy = itemView.findViewById(R.id.tvCategroy);
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class FunctionViewHolder extends RecyclerView.ViewHolder {
        public ImageView tvIcon;
        public TextView tvTitle, tvContent;

        public FunctionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIcon = itemView.findViewById(R.id.tvIcon);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }

    }


    public void setOnItemClickListener(OnEasyItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
