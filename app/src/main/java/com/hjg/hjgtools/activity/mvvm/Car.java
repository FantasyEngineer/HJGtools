package com.hjg.hjgtools.activity.mvvm;

import androidx.databinding.ObservableField;

public class Car {


    public ObservableField<String> model = new ObservableField<>();

    public Car(String model) {
        this.model.set(model);
    }

    public ObservableField<String> getModel() {
        return model;
    }

    public void setModel(ObservableField<String> model) {
        this.model = model;
    }


}
