package com.hjg.hjgtools.activity.mvvm;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import android.app.FragmentBreadCrumbs;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjg.hjgtools.R;
import com.hjg.hjgtools.activity.mvvm.adapter.CarsListAdapter;
import com.hjg.hjgtools.databinding.BlankFragmentBinding;

import java.util.ArrayList;

public class BlankFragment extends Fragment {

    private BlankViewModel mViewModel;
    private BlankFragmentBinding binding;

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.blank_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(BlankViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        ArrayList<Car> cars = new ArrayList();
        cars.add(new Car("福睿斯"));
        cars.add(new Car("福克斯猎装版"));
        cars.add(new Car("福克斯2021版"));
        CarsListAdapter carsListAdapter = new CarsListAdapter(getActivity(), cars);
        binding.recyclerView.setAdapter(carsListAdapter);
    }

}