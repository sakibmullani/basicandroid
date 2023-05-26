package com.example.myapplication.api_calling;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI_POST {

    @POST("users")
    Call<DataModal_POST> createPost(@Body DataModal_POST dataModal);

    @PATCH("posts/{id}")
    Call<Update_Model> updateData(@Path("id") int id, @Body Update_Model dataModal);
}
