package com.example.carwash.RetrofitSecond;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitServicee {
    @GET("users/{id}")
    Call<OneUserList> oneUser(@Path("id") long Id); //@Query("users") String id //id = parametr ..Call<List<Friend>>

    @GET("users")
    Call<ManyUserList> listUser(@Query("page") long IdPage);

    @DELETE("users/{id}")
    Call<Void> delUser(@Path("id") long Id);

    @Headers({"Content-Type: application/json"})
    @PUT("users/{id}")
    Call<PutUserList> putUser(@Path("id") long Id, @Body PutUserAdd body);
}
