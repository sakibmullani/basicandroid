package com.example.myapplication.api_calling;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiSet {

    @GET("todos")
    Call<List<responseModel>>getData();
}
