package com.example.carwash.RecyclerViewWeatherItem;

public class WeatherItem {
    String sity;
    String temp;
    String precipitation;
    String precipitationIcon;

    public WeatherItem(String sity, String temp, String precipitation, String precipitationIcon) {
        this.sity = sity;
        this.temp = temp;
        this.precipitation = precipitation;
        this.precipitationIcon = precipitationIcon;
    }

    public String getSity() {
        return sity;
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
}
