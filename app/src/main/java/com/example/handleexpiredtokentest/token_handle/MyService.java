package com.example.handleexpiredtokentest.token_handle;

import com.example.handleexpiredtokentest.pojo_tokens.ResponGetToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyService {
  /*
    @GET("/api/items")
    Call<List> getItems();

   */

  @GET("")
  Call<ResponGetToken>getItems();


/*
    @POST("/api/auth/token")

    @FormUrlEncoded

    Call refreshToken(

            @Field("username") String username,

            @Field("refreshToken") String refreshToken

    );

 */

    @POST("")
    @FormUrlEncoded
    Call refreshToken(@Field("user_email")String user_email, @Field("user_pass")String user_pass);


}
