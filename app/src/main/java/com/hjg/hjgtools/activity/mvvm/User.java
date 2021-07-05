package com.hjg.hjgtools.activity.mvvm;

import androidx.databinding.ObservableField;

public class User {

    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<Integer> age = new ObservableField<>();

    public User(String name, Integer age) {
        this.name.set(name);
        this.age.set(age);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableField<Integer> getAge() {
        return age;
    }

    public void setAge(ObservableField<Integer> age) {
        this.age = age;
    }
}
