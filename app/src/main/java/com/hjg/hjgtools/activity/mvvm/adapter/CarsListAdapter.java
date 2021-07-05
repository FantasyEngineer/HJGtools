package com.hjg.hjgtools.activity.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.mvvm.Car;
import com.hjg.hjgtools.databinding.ItemCarBindingImpl;
import com.hjg.hjgtools.databinding.ItemMvvmRecylcerviewBinding;

import java.util.ArrayList;
import java.util.List;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.MvvmHolder> {

    private List<Car> cars;
    private Context context;

    public CarsListAdapter(Context context, List<Car> cars) {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        this.cars = cars;
        this.context = context;
    }

    @NonNull
    @Override
    public MvvmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View inflate = inflater.inflate(R.layout.item_car,parent,false);
        return new MvvmHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MvvmHolder holder, int position) {
        holder.tvCarModel.setText(cars.get(position).getModel().get());
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }


    public class MvvmHolder extends RecyclerView.ViewHolder {
        TextView tvCarModel;

        public MvvmHolder(@NonNull View itemView) {
            super(itemView);
            tvCarModel = itemView.findViewById(R.id.tvCarModel);
        }
    }
}
