package com.example.handleexpiredtokentest.token_handle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyService {
    @GET("/api/items")
    Call<List> getItems();



    @POST("/api/auth/token")

    @FormUrlEncoded

    Call refreshToken(

            @Field("username") String username,

            @Field("refreshToken") String refreshToken

    );


}
