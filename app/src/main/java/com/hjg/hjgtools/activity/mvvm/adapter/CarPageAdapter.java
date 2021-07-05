package com.hjg.hjgtools.activity.mvvm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.hjg.hjgtools.R;

import java.util.List;

public class CarPageAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;

    public CarPageAdapter(Context context, List<String> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_viewpager_car, null);
        ImageView ivCar = (ImageView) view.findViewById(R.id.ivCar);
        Glide.with(mContext).load(mData.get(position)).into(ivCar);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

}
