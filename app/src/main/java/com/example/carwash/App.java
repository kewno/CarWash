package com.example.carwash;

import android.app.Application;

import androidx.room.Room;

import com.example.carwash.Retrofit.RetrofitService;
import com.example.carwash.Room.Database;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    public static App instance;
    private Database database;

    RetrofitService service;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, Database.class, "db").allowMainThreadQueries().build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("http://api.openweathermap.org/data/2.5/") // базовый url
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // паттер фабрика
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //.client(client)
                .build();

        service = retrofit.create(RetrofitService.class);
    }

    public static App getInstance() {
        return instance;
    }

    public Database getDatabase() {
        return  database;
    }
}
