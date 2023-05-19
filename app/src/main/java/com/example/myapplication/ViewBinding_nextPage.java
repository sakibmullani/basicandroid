package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.myapplication.databinding.ViewBindingNextPageActivityBinding;
import com.example.myapplication.models.Customer;


public class ViewBinding_nextPage extends AppCompatActivity {

//    ViewBindingNextPageActivityBinding viewBindingNextPageActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ViewBindingNextPageActivityBinding viewBindingNextPageActivityBinding = DataBindingUtil.setContentView(this, R.layout.view_binding_next_page_activity);

        Customer user = (Customer) getIntent().getSerializableExtra("user");
        viewBindingNextPageActivityBinding.setUser1(user);






//        setContentView(R.layout.view_binding_next_page_activity);

//        nextPageBinding=ViewBindingNextPageActivityBinding.inflate(getLayoutInflater());
//        setContentView(nextPageBinding.getRoot());
//
//        nextPageBinding.t1.setText("I am Also Changed");








    }
}