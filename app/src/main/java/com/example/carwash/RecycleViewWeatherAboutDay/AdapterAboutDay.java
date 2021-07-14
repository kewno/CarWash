package com.example.carwash.RecycleViewWeatherAboutDay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.carwash.R;
import com.example.carwash.RecyclerViewWeatherItem.Adapter;
import com.example.carwash.RecyclerViewWeatherItem.WeatherItem;

import java.util.List;

public class AdapterAboutDay extends RecyclerView.Adapter<AdapterAboutDay.ViewHolder> {
    List<WeatherAboutDay> data;

    public AdapterAboutDay(List<WeatherAboutDay> data) {
        this.data = data;
    }

    @Override
    public AdapterAboutDay.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_weather_item, parent, false);
        return new AdapterAboutDay.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAboutDay.ViewHolder holder, int position) {
        WeatherAboutDay chatMess = data.get(position);
        holder.precipitation.setText(chatMess.getPrecipitation());
        holder.time.setText(chatMess.getTime());
        holder.temp.setText(chatMess.getTemp() + "°C");
        if (chatMess.getPrecipitationIcon().equals("01d"))
            holder.precipitationIcon.setImageResource(R.drawable.d_01);
        else if (chatMess.getPrecipitationIcon().equals("02d"))
            holder.precipitationIcon.setImageResource(R.drawable.d_02);
        else if (chatMess.getPrecipitationIcon().equals("04d") || chatMess.getPrecipitation().equals("04n"))
            holder.precipitationIcon.setImageResource(R.drawable.d_04);
        else if (chatMess.getPrecipitationIcon().equals("03d"))
            holder.precipitationIcon.setImageResource(R.drawable.d_03);
        else if (chatMess.getPrecipitationIcon().equals("10d"))
            holder.precipitationIcon.setImageResource(R.drawable.d_10);
        else if (chatMess.getPrecipitationIcon().equals("01n"))
            holder.precipitationIcon.setImageResource(R.drawable.n_01);
        else if (chatMess.getPrecipitationIcon().equals("02n"))
            holder.precipitationIcon.setImageResource(R.drawable.n_02);
        else if (chatMess.getPrecipitationIcon().equals("03n"))
            holder.precipitationIcon.setImageResource(R.drawable.n_03);
        else if (chatMess.getPrecipitationIcon().equals("10n"))
            holder.precipitationIcon.setImageResource(R.drawable.n_10);
        else
            holder.precipitationIcon.setImageResource(R.drawable.d_01);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView temp;
        TextView precipitation;
        ImageView precipitationIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            temp = itemView.findViewById(R.id.coll_degress);
            precipitation = itemView.findViewById(R.id.precipitation);
            precipitationIcon = itemView.findViewById(R.id.precipitation_icon);
        }

    }
}
