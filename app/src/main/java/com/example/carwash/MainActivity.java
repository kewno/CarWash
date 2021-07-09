package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.carwash.RecyclerViewWeatherItem.Adapter;
import com.example.carwash.RecyclerViewWeatherItem.WeatherItem;
import com.example.carwash.Retrofit.Example;
import com.example.carwash.Room.Database;
import com.example.carwash.Room.Sity;
import com.example.carwash.Room.SityDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView weatherWrap;
    TextView sity;
    TextView precipitation;
    List<WeatherItem> list;

    public String sityNameText = "";
    SityDao sityDao;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sity = findViewById(R.id.sity);
        precipitation = findViewById(R.id.precipitation);

        db = App.getInstance().getDatabase(); // получение базы
        sityDao = db.sityDao(); // из Database объекта получаем Dao
        List<Sity> sitys = sityDao.getAll();
        sityNameText = sitys.get(0).sityName;

        list = new ArrayList<>();

        weatherWrap = findViewById(R.id.weather_item);
        weatherWrap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getWeather();
    }

    public List<WeatherItem> mock() {
        sity.setText("Ваш город: " + sityNameText);

//        list.add(new WeatherItem(sityNameText, "30", "Ясно", "01d"));
//        list.add(new WeatherItem(sityNameText, "32", "Ясно", "01d"));
//        list.add(new WeatherItem(sityNameText, "28", "Облачно", "03d"));
//        list.add(new WeatherItem(sityNameText, "31", "Ясно", "01d"));
//        list.add(new WeatherItem(sityNameText, "28", "Облачно", "03d"));
//        list.add(new WeatherItem(sityNameText, "27", "Облачно", "03d"));
//        list.add(new WeatherItem(sityNameText, "20", "Осадки", "04d"));
        setOutput();
        return list;
    }

    public void setOutput() {
        String output = "Самое время отправиться на мойку";

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPrecipitation() == "Осадки" && i < 2) {
                output = "Мойку автомобиля стоит отложить";
            }
        }

        precipitation.setText(output);
    }

    public void getWeather() {
        App.getInstance().service.weatherExample("Пермь", "c7551ab9521b376649c12e614b0f5689", "metric", "ru", 40).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example model = response.body();
                Log.d("ASSS", String.valueOf(model.getList().get(0).getMain().getTemp())); // температура //response.body().getList().get(0).getWeather().get(0).getMain()
                Log.d("ASSS", String.valueOf(model.getList().get(0).getWeather().get(0).getDescription())); // облака
                Log.d("ASSS", String.valueOf(model.getList().get(0).getDtTxt())); // облака model.getList().get(0).getMain()
                sityNameText = model.getCity().getName();
                for (int i = 0; i < model.getList().size(); i++) {
                    String[] arr = model.getList().get(i).getDtTxt().split(" ");
                    list.add(new WeatherItem(arr[1], String.valueOf(model.getList().get(i).getMain().getTemp()), model.getList().get(i).getWeather().get(0).getDescription(), "01d"));
                }
                weatherWrap.setAdapter(new Adapter(mock()));
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("ASSS", String.valueOf(t));
            }
        });
    }
}