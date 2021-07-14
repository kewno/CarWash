package com.example.carwash.RecycleViewWeatherAboutDay;

public class WeatherAboutDay {
    String time;
    String temp;
    String precipitation;
    String precipitationIcon;
    String pressure;
    String feelsLike;
    String speed;
    String cloudsPercent;

    public WeatherAboutDay(String time, String temp, String precipitation, String precipitationIcon, String pressure, String feelsLike, String speed, String cloudsPercent) {
        this.time = time;
        this.temp = temp;
        this.precipitation = precipitation;
        this.precipitationIcon = precipitationIcon;
        this.pressure = pressure;
        this.feelsLike = feelsLike;
        this.speed = speed;
        this.cloudsPercent = cloudsPercent;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getPrecipitationIcon() {
        return precipitationIcon;
    }

    public String getPressure() {
        return pressure;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public String getSpeed() {
        return speed;
    }

    public String getCloudsPercent() {
        return cloudsPercent;
    }
}
