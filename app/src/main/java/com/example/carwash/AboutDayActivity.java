package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.carwash.RecycleViewWeatherAboutDay.AdapterAboutDay;
import com.example.carwash.RecycleViewWeatherAboutDay.WeatherAboutDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AboutDayActivity extends AppCompatActivity {

    private RecyclerView weatherWrap;
    public List<WeatherAboutDay> list;
    public Integer indexList = 0;
    public Integer positionList = 0;

    public TextView sity;

    public TextView degress;
    public TextView precipitation;

    public TextView feelsLike;
    public TextView speed;
    public TextView сloud;
    public TextView barvinoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_day);

        sity = findViewById(R.id.sity_about_day);

        degress = findViewById(R.id.degress_about_day);
        precipitation = findViewById(R.id.precipitation_about_day);

        feelsLike = findViewById(R.id.feelsLike);
        speed = findViewById(R.id.speed);
        сloud = findViewById(R.id.сloud);
        barvinoc = findViewById(R.id.barvinoc);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            indexList = 1 + extras.getInt("index");
            positionList = extras.getInt("position");
            degress.setText(extras.getString("degress"));
            precipitation.setText(extras.getString("precipitation"));
        }

        list = new ArrayList<>();

        weatherWrap = findViewById(R.id.weather_item);
        weatherWrap.setHasFixedSize(true);
        weatherWrap.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        weatherWrap.setAdapter(new AdapterAboutDay(mock()));

        sity.setText(MainActivity.sityNameText);
    }

    public List<WeatherAboutDay> mock() {
        Integer timeUsers = getTimeUser();


        if (positionList != 0) {
            int start = indexList + 8 * positionList - 8;
            int end = indexList + 8 * positionList;
            if (end >= MainActivity.listAll.size()) end = MainActivity.listAll.size();
            for (int i = start; i < end; i++) {
                list.add(new WeatherAboutDay(MainActivity.listAll.get(i).getTime(), MainActivity.listAll.get(i).getTemp(), MainActivity.listAll.get(i).getPrecipitation(),  MainActivity.listAll.get(i).getPrecipitationIcon(), MainActivity.listAll.get(i).getPressure(), MainActivity.listAll.get(i).getFeelsLike(), MainActivity.listAll.get(i).getSpeed(), MainActivity.listAll.get(i).getCloudsPercent()));
                String[] arr = MainActivity.listAll.get(i).getTime().split(":");
                Integer time = Integer.valueOf(arr[0]);

                if (time == timeUsers || (time - 1) == timeUsers || (time - 2) == timeUsers) {
                    feelsLike.setText(MainActivity.listAll.get(i).getFeelsLike());
                    speed.setText(MainActivity.listAll.get(i).getSpeed() + "м/с");
                    сloud.setText(MainActivity.listAll.get(i).getCloudsPercent() +"%");
                    barvinoc.setText(MainActivity.listAll.get(i).getPressure());
                }
            }
        } else {
            int start = 0;
            int end = indexList;
            for (int i = start; i < end; i++) {
                list.add(new WeatherAboutDay(MainActivity.listAll.get(i).getTime(), MainActivity.listAll.get(i).getTemp(), MainActivity.listAll.get(i).getPrecipitation(), MainActivity.listAll.get(i).getPrecipitationIcon(), MainActivity.listAll.get(i).getPressure(), MainActivity.listAll.get(i).getFeelsLike(), MainActivity.listAll.get(i).getSpeed(), MainActivity.listAll.get(i).getCloudsPercent()));

                String[] arr = MainActivity.listAll.get(i).getTime().split(":");
                Integer time = Integer.valueOf(arr[0]);

                if (time == timeUsers || (time - 1) == timeUsers || (time - 2) == timeUsers) {
                    feelsLike.setText(MainActivity.listAll.get(i).getFeelsLike());
                    speed.setText(MainActivity.listAll.get(i).getSpeed() + "м/с");
                    сloud.setText(MainActivity.listAll.get(i).getCloudsPercent() +"%");
                    barvinoc.setText(MainActivity.listAll.get(i).getPressure());
                }
            }
//            int start = 0;
//            int end = indexList;
//            for (int i = start; i < end; i++) {
//                list.add(new WeatherAboutDay(MainActivity.listAll.get(i).getTime(), MainActivity.listAll.get(i).getTemp(), MainActivity.listAll.get(i).getPrecipitation(), "01d"));
//            }
        }
        return list;
    }

    public int getTimeUser() {
        Date currentDate = new Date();
        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String timeText = timeFormat.format(currentDate);
        String[] arr1 = timeText.split(":");

        return Integer.valueOf(arr1[0]);
    }
}