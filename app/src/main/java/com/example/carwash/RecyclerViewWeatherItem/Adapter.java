package com.example.carwash.RecyclerViewWeatherItem;

import android.util.Log;
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

    private RecycleViewClickListener listener;

    public Adapter(List<WeatherItem> data, RecycleViewClickListener listener) {
        this.data = data;
        this.listener = listener;
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

    public interface RecycleViewClickListener {
        void onClick(View v, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }
}
