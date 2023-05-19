package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class RecyclerShowALLData extends AppCompatActivity {

    TextView showName, showNumber;
    ImageView showImage;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_showalldata);

        showImage=findViewById(R.id.showImage);
        showNumber=findViewById(R.id.showNumber);
        showName=findViewById(R.id.showName);
        toolbar=findViewById(R.id.toolBar_Recycler_profile);

        //set Toolbar back button
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Profile");
//        toolbar.setSubtitle("contact List");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        showName.setText(getIntent().getExtras().getString("name"));
        showNumber.setText(getIntent().getExtras().getString("number"));
        int img= getIntent().getIntExtra("image",0);
        showImage.setImageResource(img);


    }
}