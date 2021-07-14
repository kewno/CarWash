package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.example.carwash.RetrofitSecond.ManyUserList;
import com.example.carwash.RetrofitSecond.RetrofitServicee;
import com.example.carwash.RetrofitSecond.OneUserList;
import com.example.carwash.RetrofitSecond.PutUserAdd;
import com.example.carwash.RetrofitSecond.PutUserList;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSeconds extends AppCompatActivity {
    RetrofitServicee service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_seconds);

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("https://reqres.in/api/") // базовый url
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // паттер фабрика
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.client(client)
                .build();

        service = retrofit.create(RetrofitServicee.class); //Создаем объект, при помощи которого будем выполнять запросы

        service.oneUser(1).enqueue(new Callback<OneUserList>() {
            @Override
            public void onResponse(Call<OneUserList> call, Response<OneUserList> response) {
                OneUserList model = response.body(); // тип построить
                Log.d("ASSS", String.valueOf(model.getOneUser().getEmail()));
            }
            @Override
            public void onFailure(Call<OneUserList> call, Throwable t) {
                Log.d("ASSS", String.valueOf(t));
            }
        });

        service.listUser(2).enqueue(new Callback<ManyUserList>() {
            @Override
            public void onResponse(Call<ManyUserList> call, Response<ManyUserList> response) {
                ManyUserList model = response.body(); // тип построить
                //if (model != null) {
                Log.d("ASSS2", String.valueOf(response.body().getManyUser().get(1).getEmail()));
                //}
            }
            @Override
            public void onFailure(Call<ManyUserList> call, Throwable t) {
                Log.d("ASSS2", String.valueOf(t));
            }
        });

        service.delUser(1).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                //OneUserList model = response.body(); // тип построить
                Log.d("ASSS3", String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ASSS3", String.valueOf(t));
            }
        });

        PutUserAdd putUser = new PutUserAdd();
        putUser.setName("Alex");
        putUser.setJob("Design");

        service.putUser(1, putUser).enqueue(new Callback<PutUserList>() {
            @Override
            public void onResponse(Call<PutUserList> call, Response<PutUserList> response) {
                PutUserList model = response.body(); // тип построить
                Log.d("ASSS4", String.valueOf(model.getData()));
            }
            @Override
            public void onFailure(Call<PutUserList> call, Throwable t) {
                Log.d("ASSS4", String.valueOf(t));
            }
        });
    }
}