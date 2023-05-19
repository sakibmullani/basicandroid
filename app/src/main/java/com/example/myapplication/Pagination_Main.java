package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Pagination_Main extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    Pagination_Adapter myAdaptor;
    LinearLayoutManager layoutManager;
    ProgressBar progressBar;
    Boolean isScrolling=false;
    int currentItems,scrolledOutItems,totalItems;
    ArrayList list;
    ArrayList data;
    int a=10;
    int b=10;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagination_main_layout);
        recyclerView = findViewById(R.id.myRv);
        layoutManager = new LinearLayoutManager(this);
        progressBar=findViewById(R.id.myProgressBar);

        toolbar=(Toolbar) findViewById(R.id.toolBar_pagination);

        //set Toolbar back button
        setSupportActionBar(toolbar);
        toolbar.setTitle("My List");
//        toolbar.setSubtitle("contact List");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        String [] a = {"0","1","2","3","4","5","6","7","8","9"};
        list = new ArrayList(Arrays.asList(a));


        myAdaptor = new Pagination_Adapter(list,this);
        recyclerView.setAdapter(myAdaptor);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScrolling=true;

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems=layoutManager.getItemCount();
                scrolledOutItems=layoutManager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems+scrolledOutItems)==totalItems){
                    fetchData();
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);

                }

            }
        });


    }

    private void fetchData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                data=list;
                int i;
                for (i=0;i<10;i++) {
                    list.add(i+a+"");

                    progressBar.setVisibility(View.GONE);
                    myAdaptor.notifyDataSetChanged();
                }
                a=a+b;

            }
        },800);


//            data = list;
//            int i;
//            for (i = 0; i < 10; i++) {
//                list.add(i + a + "");
//                progressBar.setVisibility(View.GONE);
//                myAdaptor.notifyDataSetChanged();
//            }
//            a = a + b;
//       progressBar.setVisibility(View.VISIBLE);

        }


}