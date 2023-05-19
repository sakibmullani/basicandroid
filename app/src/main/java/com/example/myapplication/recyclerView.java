package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Objects;

public class recyclerView extends AppCompatActivity {

    Toolbar toolbar;



   ArrayList<ContactModel> arrContact= new ArrayList<>();

   ArrayList<ContactModel> arrContact2= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        toolbar=(Toolbar) findViewById(R.id.toolBar_Recycler);
        RecyclerView recyclerView=findViewById(R.id.recycleViewId);
        RecyclerView recyclerView2=findViewById(R.id.recycleViewId2);

        //set Toolbar back button
        setSupportActionBar(toolbar);
        toolbar.setTitle("My List");
//        toolbar.setSubtitle("contact List");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);



//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        arrContact.add(new ContactModel(R.drawable.a,"SAKIB", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.b,"B", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.c,"C", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.d,"D", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.e,"E", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.f,"F", "7057828588"));
        arrContact.add(new ContactModel(R.drawable.a,"A", "123456789"));
        arrContact.add(new ContactModel(R.drawable.b,"B", "123456789"));
        arrContact.add(new ContactModel(R.drawable.c,"C", "123456789"));
        arrContact.add(new ContactModel(R.drawable.d,"D", "123456789"));
        arrContact.add(new ContactModel(R.drawable.e,"E", "123456789"));
        arrContact.add(new ContactModel(R.drawable.f,"F", "123456789"));
        arrContact.add(new ContactModel(R.drawable.a,"A", "123456789"));
        arrContact.add(new ContactModel(R.drawable.b,"B", "123456789"));


        arrContact2.add(new ContactModel(R.drawable.c,"C", "7057828588"));
        arrContact2.add(new ContactModel(R.drawable.d,"D", "7057828588"));
        arrContact2.add(new ContactModel(R.drawable.e,"E", "7057828588"));
        arrContact2.add(new ContactModel(R.drawable.f,"F", "7057828588"));
        arrContact2.add(new ContactModel(R.drawable.a,"A", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.b,"B", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.c,"C", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.d,"D", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.e,"E", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.f,"F", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.a,"A", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.b,"B", "123456789"));
        arrContact2.add(new ContactModel(R.drawable.a,"SAKIB", "7057828588"));
        arrContact2.add(new ContactModel(R.drawable.b,"B", "7057828588"));

        RecyclerContactAdapter adapter=new RecyclerContactAdapter(this, arrContact);
       recyclerView.setAdapter(adapter);

        Recycler_horizontal adapter2=new Recycler_horizontal(this, arrContact2);
        recyclerView2.setAdapter(adapter2);

    }
}