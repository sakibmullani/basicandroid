package com.example.myapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.models.Customer;

public class MainViewmodel extends ViewModel {

    private MutableLiveData<String> number = new MutableLiveData<>();
    private MutableLiveData<String> name = new MutableLiveData<>();
    private MutableLiveData<String> city = new MutableLiveData<>();

    public void setNumber(String numberValue) {
        number.setValue(numberValue);
    }

    public MutableLiveData<String> getNumber() {
        return number;
    }

    public void setName(String nameValue) {
        name.setValue(nameValue);
    }

    public MutableLiveData<String> getName() {
        return name;
    }

    public void setCity(String cityValue) {
        city.setValue(cityValue);
    }

    public MutableLiveData<String> getCity() {
        return city;
    }

}
