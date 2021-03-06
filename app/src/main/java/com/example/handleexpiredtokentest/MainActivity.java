package com.example.handleexpiredtokentest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.handleexpiredtokentest.pojo_tokens.ResponGetToken;
import com.example.handleexpiredtokentest.token_handle.MyService;
import com.example.handleexpiredtokentest.token_handle.MyServiceHolder;
import com.example.handleexpiredtokentest.token_handle.OkHttpClientInstance;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //di panggil shared preferncenya ke sini nanti pas isi email sam password

    public void getData(){

        SharedPreferences settings = getSharedPreferences(getResources()
                .getString(R.string.sharedPreferences_token),MODE_PRIVATE);



        String token = settings.getString("token", null);

        MyServiceHolder myServiceHolder = new MyServiceHolder();



        OkHttpClient okHttpClient = new OkHttpClientInstance.Builder(MainActivity.this, myServiceHolder)

                .addHeader("Authorization", token)//dapat dari shared prefence

                .build();



        MyService myService = new retrofit2.Retrofit.Builder()

                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .client(okHttpClient)

                .build()

                .create(MyService.class);



        myServiceHolder.set(myService);

        Call<ResponGetToken> call = myService.getItems();
        call.enqueue(new Callback<ResponGetToken>() {
            @Override
            public void onResponse(Call<ResponGetToken> call, Response<ResponGetToken> response) {

               // LoginResponse loginResponse = response.body();
                ResponGetToken responGetToken = response.body();
                if (response.isSuccessful()) {
                    Log.d("Server Response", "failed" + responGetToken.getDisplayName());
                    //textView_log.setText(loginResponse.getMessage());

                }

                else  {
                   // textView_log.setText("User Not Found");

                }

            }

            @Override
            public void onFailure(Call<ResponGetToken> call, Throwable t) {
                Log.d("Server Response","success"+t.toString());
                //textView_log.setText("Connection not found");




            }
        });



    }

  }