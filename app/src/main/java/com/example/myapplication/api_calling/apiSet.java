package com.example.myapplication.api_calling;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface apiSet {

    @GET("todos")
    Call<List<responseModel>>getData();

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
