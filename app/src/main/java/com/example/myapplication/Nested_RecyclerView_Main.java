package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Objects;

public class Nested_RecyclerView_Main extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<nestedRV_parentModelClass>nestedRV_parentModelClassArrayList;
    ArrayList<nestedRV_childModelClass>nestedRV_childModelClassArrayList;
    ArrayList<nestedRV_childModelClass>favoriteList;
    ArrayList<nestedRV_childModelClass>mybest;
    ArrayList<nestedRV_childModelClass>Comedy;
    ArrayList<nestedRV_childModelClass>Action;
    nested_parentAdapter parentAdapter;

    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nested_recycler_view_main_activity);


        recyclerView=findViewById(R.id.rv_parent);
        favoriteList=new ArrayList<>();
        mybest=new ArrayList<>();
        Comedy=new ArrayList<>();
        Action=new ArrayList<>();
        nestedRV_parentModelClassArrayList=new ArrayList<>();
        nestedRV_childModelClassArrayList=new ArrayList<>();

        toolbar=(Toolbar) findViewById(R.id.toolBar_Recycler);

        //set Toolbar back button
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Movies");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv1));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv2));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv3));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv4));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv1));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv2));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv3));
        favoriteList.add(new nestedRV_childModelClass(R.drawable.mv4));

        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest Movies",favoriteList));


        mybest.add(new nestedRV_childModelClass(R.drawable.mv4));
        mybest.add(new nestedRV_childModelClass(R.drawable.mv5));
        mybest.add(new nestedRV_childModelClass(R.drawable.mv6));
        mybest.add(new nestedRV_childModelClass(R.drawable.mv4));
        mybest.add(new nestedRV_childModelClass(R.drawable.mv5));
        mybest.add(new nestedRV_childModelClass(R.drawable.mv6));

        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Movies",mybest));


        Comedy.add(new nestedRV_childModelClass(R.drawable.mv7));
        Comedy.add(new nestedRV_childModelClass(R.drawable.mv8));
        Comedy.add(new nestedRV_childModelClass(R.drawable.mv9));
        Comedy.add(new nestedRV_childModelClass(R.drawable.mv7));
        Comedy.add(new nestedRV_childModelClass(R.drawable.mv8));
        Comedy.add(new nestedRV_childModelClass(R.drawable.mv9));

        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest ",Comedy));

        Action.add(new nestedRV_childModelClass(R.drawable.mv10));
        Action.add(new nestedRV_childModelClass(R.drawable.mv11));
        Action.add(new nestedRV_childModelClass(R.drawable.mv1));
        Action.add(new nestedRV_childModelClass(R.drawable.mv10));
        Action.add(new nestedRV_childModelClass(R.drawable.mv11));
        Action.add(new nestedRV_childModelClass(R.drawable.mv1));

        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest Movies2",Action));


//        nestedRV_childModelClassArrayList.clear();
//
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv1));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv2));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv3));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv4));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv5));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv6));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv7));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv8));
//        nestedRV_childModelClassArrayList.add(new nestedRV_childModelClass(R.drawable.mv9));
//
//
//        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest Movies",nestedRV_childModelClassArrayList));
//        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Movies",nestedRV_childModelClassArrayList));
//        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest",nestedRV_childModelClassArrayList));
//        nestedRV_parentModelClassArrayList.add(new nestedRV_parentModelClass("Latest Movies2",nestedRV_childModelClassArrayList));


        parentAdapter=new nested_parentAdapter(nestedRV_parentModelClassArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();






    }
}