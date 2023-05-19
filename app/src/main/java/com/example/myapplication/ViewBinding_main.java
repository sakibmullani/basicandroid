package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.View1BindingMainActivityBinding;

import com.example.myapplication.models.Customer;
import com.example.myapplication.viewmodels.MainViewmodel;

public class ViewBinding_main extends AppCompatActivity {

    MainViewmodel mainViewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View1BindingMainActivityBinding viewMain = DataBindingUtil.setContentView(this,R.layout.view1_binding_main_activity);

//       Customer customer= new Customer("Hi, SM");

        mainViewmodel= new ViewModelProvider(this).get(MainViewmodel.class);
        viewMain.setMainViewmodel(mainViewmodel);

        viewMain.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = mainViewmodel.getNumber().getValue();
                String name = mainViewmodel.getName().getValue();
                String city = mainViewmodel.getCity().getValue();

                Customer user = new Customer(name, city, number);

                Intent intent = new Intent(ViewBinding_main.this, ViewBinding_nextPage.class);
                intent.putExtra("user",user);
                startActivity(intent);


            }
        });




        //View Binding
//
//        mainView1=View1BindingMainActivityBinding.inflate(getLayoutInflater());
//        setContentView(mainView1.getRoot());
//
//
//        mainView1.t1.setText("I am Changed");  // T1 id of TextView
//
//        mainView1.button1.setOnClickListener(new View.OnClickListener() {   //button1 id of button
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(getApplicationContext(),ViewBinding_nextPage.class));
//
//            }
//        });

    }
}