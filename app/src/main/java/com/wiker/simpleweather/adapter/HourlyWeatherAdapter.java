package com.wiker.simpleweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wiker.framework.util.DateUtil;
import com.wiker.simpleweather.R;
import com.wiker.simpleweather.model.HourlyWeather;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {

    @BindString(R.string.temp) String strTemp;
    private Context mContext;
    private List<HourlyWeather> weatherList;

    public HourlyWeatherAdapter(Context context, List<HourlyWeather> weatherList) {
        this.mContext = context;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_weather_hourly, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder mHolder, int position) {
        HourlyWeather weather = weatherList.get(position);
        mHolder.tvTime.setText(DateUtil.getHour(weather.getDateTime()));
        mHolder.tvWeather.setText(weather.getCondDes());
        mHolder.tvTemp.setText(String.format(mContext.getResources().getString(R.string.temp), weather.getTemperiture()));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_hourly_time) TextView tvTime;
        @BindView(R.id.tv_hourly_weather) TextView tvWeather;
        @BindView(R.id.tv_hourly_temperiture) TextView tvTemp;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
