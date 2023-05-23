package com.example.myapplication.api_calling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class Post_API_Activity extends AppCompatActivity {

    // creating variables for our edittext,
    // button, textview and progressbar.
    private EditText nameEdt, jobEdt;
    private Button postDataBtn;
    private TextView responseTV;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_api);

        // initializing our views
        nameEdt = findViewById(R.id.idEdtName);
        jobEdt = findViewById(R.id.idEdtJob);
        postDataBtn = findViewById(R.id.idBtnPost);
        responseTV = findViewById(R.id.idTVResponse);
        loadingPB = findViewById(R.id.idLoadingPB);

        // adding on click listener to our button.
        postDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (nameEdt.getText().toString().isEmpty() && jobEdt.getText().toString().isEmpty()) {
                    Toast.makeText(Post_API_Activity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(nameEdt.getText().toString(), jobEdt.getText().toString());
            }
        });
    }

    private void postData(String name, String job) {

        // below line is for displaying our progress bar.
        loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")

                .addConverterFactory(GsonConverterFactory.create())

                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI_POST retrofitAPI = retrofit.create(RetrofitAPI_POST.class);

        // passing data from our text fields to our modal class.
        DataModal_POST modal = new DataModal_POST(name, job);

        // calling a method to create a post and passing our modal class.
        Call<DataModal_POST> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal_POST>() {
            @Override
            public void onResponse(Call<DataModal_POST> call, Response<DataModal_POST> response) {
                // this method is called when we get response from our api.
                Toast.makeText(Post_API_Activity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                jobEdt.setText("");
                nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModal_POST responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\n Name : " + responseFromAPI.getName() + "\n" + "Job : " + responseFromAPI.getJob();

                // below line we are setting our
                // string to our text view.
                responseTV.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModal_POST> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                responseTV.setText("Error found is : " + t.getMessage());
            }
        });
    }
}