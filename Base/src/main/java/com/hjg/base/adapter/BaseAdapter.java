package com.hjg.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.hjg.base.listener.OnItemClickListener;

import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected RecyclerView recyclerView;

    protected List<T> mDatas;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    public BaseAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = BaseViewHolder.get(mContext, null, parent,
                mLayoutId, -1);
        setListener(parent, viewHolder);
        return viewHolder;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }


    protected void setListener(final ViewGroup parent,
                               final BaseViewHolder viewHolder) {
        viewHolder.getConvertView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            int position = getPosition(viewHolder);
                            try {
                                mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                            } catch (Exception e) {

                            }
                        }
                    }
                });

        viewHolder.getConvertView().setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mOnItemClickListener != null) {
                            int position = getPosition(viewHolder);
                            return mOnItemClickListener.onItemLongClick(parent,
                                    v, mDatas.get(position), position);
                        }
                        return false;
                    }
                });
    }


    public void setNewData(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    /**
     * 插入数据
     *
     * @param t
     */
    public void addMoreData(T t) {
        mDatas.add(t);
        notifyItemChanged(mDatas.size() - 1);
//      if (recyclerView != null) {//自动滑动到底部
//            recyclerView.scrollToPosition(mDatas.size());
//        }
    }

    public void removeData(int pos) {
        mDatas.remove(pos);
        notifyItemRangeChanged(pos, mDatas.size() + 1);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position), position);
    }

    public abstract void convert(BaseViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void bindRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

}
