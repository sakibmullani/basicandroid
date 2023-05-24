package com.example.myapplication.api_calling;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity_API extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Boolean isScrolling=false;
    myAdapter adapter;
    List<responseModel> dataList;
    apiSet apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_activity_main);

        recyclerView = findViewById(R.id.recyclcerId);
        progressBar = findViewById(R.id.progressbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();
        adapter = new myAdapter(dataList);
        recyclerView.setAdapter(adapter);

        apiService = apiCntroller.getInstance().getApiALL();

        prossesData();
        setupDeleteListener();
    }

    private void prossesData() {
        Call<List<responseModel>> call = apiService.getData();

        call.enqueue(new Callback<List<responseModel>>() {
            @Override
            public void onResponse(Call<List<responseModel>> call, Response<List<responseModel>> response) {
                List<responseModel> data = response.body();
                if (data != null) {
                    dataList.addAll(data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<responseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupDeleteListener() {
        adapter.setOnDeleteClickListener(new myAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                if (position >= 0 && position < dataList.size()) {
                    int id = Integer.parseInt(dataList.get(position).getId());
                    deleteData(id, position);
                }
            }
        });
    }

    private void deleteData(int id, final int position) {
        Call<Void> call = apiService.deleteUser(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    dataList.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(MainActivity_API.this, "User deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity_API.this, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity_API.this, "Failed to delete user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}