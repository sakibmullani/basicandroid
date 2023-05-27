package com.example.myapplication.api_calling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class Update_API_Activity extends AppCompatActivity {


    String url = "https://jsonplaceholder.typicode.com/";

    private EditText userId, title, body;
    private Button updateBtn;
    private ProgressBar loadingPB;
    private TextView responseTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_api);

//        userId = findViewById(R.id.idUserId);
//        title = findViewById(R.id.idTitle);
//        body = findViewById(R.id.idBody);
        updateBtn = findViewById(R.id.idBtnUpdate);
        loadingPB = findViewById(R.id.idPBLoading);
        responseTV = findViewById(R.id.idTVResponse);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(TextUtils.isEmpty(userId.getText().toString()) && TextUtils.isEmpty(title.getText().toString())){
//
//                    Toast.makeText(Update_API_Activity.this, "Please enter Details", Toast.LENGTH_SHORT).show();
//                    return;
//            }
//              callPUTDataMethod(userId.getText().toString(), title.getText().toString(),body.getText().toString());

               callPUTDataMethod();
            
        }

    });

    }

//    private void callPUTDataMethod(String userId,String title, String body) {
        private void callPUTDataMethod() {

        loadingPB.setVisibility(View.VISIBLE);

        Retrofit retrofit=new Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory
                        .create())
                        .build();

        RetrofitAPI_POST retrofitAPI_post = retrofit.create(RetrofitAPI_POST.class);


//        Update_Model dataModal_post=new Update_Model(userId,title,body);
//
//        Call<Update_Model> call=retrofitAPI_post.updateData(dataModal_post);

        Update_Model dataModal_post=new Update_Model("1", "sm", null);
        Call<Update_Model> call= retrofitAPI_post.updateData(1, dataModal_post);


        call.enqueue(new Callback<Update_Model>() {
            @Override
            public void onResponse(Call<Update_Model> call, Response<Update_Model> response) {
                Toast.makeText(Update_API_Activity.this, "Data updated to API", Toast.LENGTH_SHORT).show();

                loadingPB.setVisibility(View.GONE);


                Update_Model responseFromApi =response.body();

                         String responseString = "Response Code : "
                                                            + response.code() +
                                                             "\n User Id : "
                                                            + responseFromApi.getUserId() +
                                                             "\nTitle : "
                                                            + responseFromApi.getTitle() + "\n" +
                                                             "Body : "
                                                            + responseFromApi.getBody();

                responseTV.setText(responseString);


            }

            @Override
            public void onFailure(Call<Update_Model> call, Throwable t) {


                responseTV.setText("Error found  : " + t.getMessage());

            }
        });




    }

}
