package com.example.myapplication.api_calling;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_API extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Boolean isScrolling=false;
//    int currentItems,scrolledOutItems,totalItems;
//    LinearLayoutManager layoutManager;
//    ArrayList list;
//    ArrayList data;
//    int a=10;
//    int b=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_main);

        recyclerView=findViewById(R.id.recyclcerId);
        progressBar=findViewById(R.id.progressbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        prossesData();
//        progressBar.setVisibility(View.VISIBLE);

    }

    private void prossesData() {
        Call<List<responseModel>> call=apiCntroller
                                        .getInstance()
                                         .getApiALL().getData();


        call.enqueue(new Callback<List<responseModel>>() {
            @Override
            public void onResponse(Call<List<responseModel>> call, Response<List<responseModel>> response) {

                List<responseModel> data = response.body();

                myAdapter adapter=new myAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<responseModel>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}