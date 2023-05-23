package com.example.myapplication.api_calling;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiCntroller {
    private  static  final  String url ="https://jsonplaceholder.typicode.com/";
    private  static  apiCntroller clientObject;
    private  static Retrofit retrofit;

    apiCntroller(){

        retrofit =new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  static  synchronized  apiCntroller getInstance(){

        if (clientObject==null){

            clientObject=new apiCntroller();

        }
        return clientObject;

    }

    apiSet getApiALL(){
        return  retrofit.create(apiSet.class);

    }



}
