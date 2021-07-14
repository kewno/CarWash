package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.RecycleViewWeatherAboutDay.WeatherAboutDay;
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

    public static Integer indexList;

    private RecyclerView weatherWrap;
    private Adapter.RecycleViewClickListener listener;

    TextView sity;
    TextView precipitation;
    List<WeatherItem> list;
    public static List<WeatherAboutDay> listAll;

    public static String sityNameText = "";
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

        getWeather();

        listAll = new ArrayList<>();
        list = new ArrayList<>();

        weatherWrap = findViewById(R.id.weather_item);
        weatherWrap.setHasFixedSize(true);
        weatherWrap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        setOnClickListener();
    }

    private void setOnClickListener() {
        listener = new Adapter.RecycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), AboutDayActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("index", indexList);
                Log.e("ASSS1", String.valueOf(indexList));
                intent.putExtra("degress", list.get(position).getTemp());
                intent.putExtra("precipitation", list.get(position).getPrecipitation());
                startActivity(intent);
            }
        };
    }

    public List<WeatherItem> mock() {
        return list;
    }

    public void getWeather() {
        App.getInstance().service.weatherExample(sityNameText, "c7551ab9521b376649c12e614b0f5689", "metric", "ru", 40).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.code() == 404) {
                    Database db = App.getInstance().getDatabase(); // получение базы
                    SityDao sityDao = db.sityDao(); // из Database объекта получаем Dao
                    sityDao.deleteAll();

                    Toast.makeText(getApplicationContext(), "Ошибка, попробуйте ввести другой город", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), setSityActivity.class);
                    startActivity(intent);
                    return;
                }
                Example model = response.body();

                sityNameText = model.getCity().getName();
                boolean check = true;
                for (int i = 0; i < model.getList().size(); i++) {
                    String[] arr = model.getList().get(i).getDtTxt().split(" ");
                    String[] arrDate = arr[0].split("-");
                    String date = arrDate[2] + "." + arrDate[1];
                    String[] arrTime = arr[1].split(":");
                    String time = arrTime[0] + ":" + arrTime[1];

                    listAll.add(new WeatherAboutDay(time, String.valueOf(Math.round(model.getList().get(i).getMain().getTemp())), model.getList().get(i).getWeather().get(0).getDescription(), model.getList().get(i).getWeather().get(0).getIcon(), String.valueOf(model.getList().get(i).getMain().getPressure()), String.valueOf(model.getList().get(i).getMain().getFeelsLike()), String.valueOf(model.getList().get(i).getWind().getSpeed()), String.valueOf(model.getList().get(i).getClouds().getAll())));

                    if (i < 8 && check && !(arr[1].equals("00:00:00") || arr[1].equals("03:00:00") || arr[1].equals("06:00:00") || arr[1].equals("09:00:00"))) {
                        list.add(new WeatherItem(date, String.valueOf(Math.round(model.getList().get(i).getMain().getTemp())), model.getList().get(i).getWeather().get(0).getDescription(), model.getList().get(i).getWeather().get(0).getIcon()));
                        check = false;
                        Log.e("ASSS", String.valueOf(i + "i"));
                        if (i == 4)
                            indexList = 7;
                        else if (i == 3)
                            indexList = 6;
                        else if (i == 2)
                            indexList = 5;
                        else if (i == 1)
                            indexList = 4;
                        else if (i == 0) {
                            if (arr[1].equals("15:00:00"))
                                indexList = 3;
                            else if (arr[1].equals("18:00:00"))
                                indexList = 2;
                            else if (arr[1].equals("21:00:00"))
                                indexList = 1;
                        }
                    }
                    if (arr[1].equals("12:00:00") && i > 4) {
                        list.add(new WeatherItem(date, String.valueOf(Math.round(model.getList().get(i).getMain().getTemp())), model.getList().get(i).getWeather().get(0).getDescription(), model.getList().get(i).getWeather().get(0).getIcon()));
                    }
//                    if (!(arr[1].equals("12:00:00")) && i > ) {
//                        list.add(new WeatherItem(date, String.valueOf(Math.round(model.getList().get(i).getMain().getTemp())), model.getList().get(i).getWeather().get(0).getDescription(), "01d"));
//                    }
                }
                weatherWrap.setAdapter(new Adapter(mock(), listener));

                sity.setText("Ваш город: " + sityNameText);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("ASSS", String.valueOf(t));
            }
        });
    }
}