package com.example.carwash.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("forecast")
    Call<Example> weatherExample(@Query("q") String sity, @Query("appid") String key, @Query("units") String units, @Query("land") String language, @Query("cnt") int coll);
}
