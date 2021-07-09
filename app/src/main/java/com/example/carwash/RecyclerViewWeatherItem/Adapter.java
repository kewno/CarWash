package com.example.carwash.RecyclerViewWeatherItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.carwash.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<WeatherItem> data;

    public Adapter(List<WeatherItem> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeatherItem chatMess = data.get(position);
        holder.precipitation.setText(chatMess.getPrecipitation());
        holder.sity.setText(chatMess.getSity());
        holder.temp.setText(chatMess.getTemp() + "°C");
        if (chatMess.getPrecipitation() == "Ясно")
            holder.precipitationIcon.setImageResource(R.drawable.d_01);
        else if (chatMess.getPrecipitation() == "Осадки")
            holder.precipitationIcon.setImageResource(R.drawable.d_04);
        else if (chatMess.getPrecipitation() == "Облачно")
            holder.precipitationIcon.setImageResource(R.drawable.d_03);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sity;
        TextView temp;
        TextView precipitation;
        ImageView precipitationIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            sity = itemView.findViewById(R.id.sity);
            temp = itemView.findViewById(R.id.coll_degress);
            precipitation = itemView.findViewById(R.id.precipitation);
            precipitationIcon = itemView.findViewById(R.id.precipitation_icon);
        }
    }
}
