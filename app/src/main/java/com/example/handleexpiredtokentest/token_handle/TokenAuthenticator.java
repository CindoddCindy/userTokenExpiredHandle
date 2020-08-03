package com.example.handleexpiredtokentest.token_handle;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.handleexpiredtokentest.R;
import com.example.handleexpiredtokentest.pojo_tokens.PostRespon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class TokenAuthenticator implements Authenticator {
    private Context context;

    private MyServiceHolder myServiceHolder;


    public TokenAuthenticator(Context context, MyServiceHolder myServiceHolder) {
        this.context = context;

        this.myServiceHolder = myServiceHolder;

    }


    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
       // return null;
        if (myServiceHolder == null) {

            return null;

        }
        SharedPreferences settings = context.getSharedPreferences(context.getResources()

                .getString(R.string.sharedPreferences_token), context.MODE_PRIVATE);



        String refreshToken = settings.getString("refreshToken", null);

        String username = settings.getString("username", null);



        retrofit2.Response retrofitResponse = myServiceHolder.get().refreshToken(username, refreshToken).execute();



        if (retrofitResponse != null) {

            PostRespon refreshTokenResponse = (PostRespon) retrofitResponse.body();//post

            //PostRespon postRespon = retrofitResponse.body();




            String newAccessToken = refreshTokenResponse.getToken();//.getToken();



            return response.request().newBuilder()

                    .header("Authorization", newAccessToken)

                    .build();

        }



        return null;
    }
}
