package com.example.myapplication.api_calling;

import com.example.myapplication.api_calling.apiSet;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiCntroller {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static apiCntroller instance;
    private Retrofit retrofit;

    private apiCntroller() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized apiCntroller getInstance() {
        if (instance == null) {
            instance = new apiCntroller();
        }
        return instance;
    }

    public apiSet getApiALL() {
        return retrofit.create(apiSet.class);
    }
}
