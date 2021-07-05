package com.hjg.hjgtools.activity.databinding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.listener.OnItemClickListener;
import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.mvvm.User;
import com.hjg.hjgtools.databinding.ItemMvvmRecylcerviewBinding;

import java.util.List;

public class DatabindAdapter extends RecyclerView.Adapter<DatabindAdapter.MyHolder> {
    private OnItemClickListener<User> onItemClickListener;
    private Context context;
    private List<User> list;

    public DatabindAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DatabindAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        返回布局
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemMvvmRecylcerviewBinding itemMvvmRecylcerviewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_mvvm_recylcerview, parent, false);
        return new MyHolder(itemMvvmRecylcerviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabindAdapter.MyHolder holder, int position) {
        //添加数据
        ItemMvvmRecylcerviewBinding itemMvvmRecylcerviewBinding = holder.getItemMvvmRecylcerviewBinding();

        itemMvvmRecylcerviewBinding.tvName.setTag(position);//设置tag，保存位置

        User user = list.get(position);
        itemMvvmRecylcerviewBinding.setUser(user);

        //添加点击事件
        itemMvvmRecylcerviewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick((ViewGroup) itemMvvmRecylcerviewBinding.getRoot()
                            , itemMvvmRecylcerviewBinding.getRoot(), user, (Integer) itemMvvmRecylcerviewBinding.tvName.getTag());
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    public static class MyHolder extends RecyclerView.ViewHolder {
        private ItemMvvmRecylcerviewBinding itemMvvmRecylcerviewBinding;

        public MyHolder(@NonNull ItemMvvmRecylcerviewBinding itemMvvmRecylcerviewBinding) {
            super(itemMvvmRecylcerviewBinding.getRoot());
            this.itemMvvmRecylcerviewBinding = itemMvvmRecylcerviewBinding;
        }


        public ItemMvvmRecylcerviewBinding getItemMvvmRecylcerviewBinding() {
            return itemMvvmRecylcerviewBinding;
        }
    }


    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<User> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
