package com.wiker.simpleweather.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wiker.framework.activity.BaseActivity;
import com.wiker.simpleweather.adapter.HourlyWeatherAdapter;
import com.wiker.simpleweather.R;
import com.wiker.simpleweather.model.ForecastWeather;
import com.wiker.simpleweather.model.HourlyWeather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity{

    @BindView(R.id.rv_hourly) RecyclerView rvHourly;
    @BindView(R.id.ll_forecast) LinearLayout llForecast;

    @Override
    public int getLayout() {
        setTheme(R.style.AppTheme);
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }
  @Override
    public void initData(){
        Bmob.initialize(this, "81ca7be0d101e76d8a7e0066518a0cdc");
        List<HourlyWeather> weathers = new ArrayList<>();
        for (int i=0;i<10;i++){
            HourlyWeather weather = new HourlyWeather();
            weather.setCondDes("晴");
            weather.setDateTime("2019-10-09 21:20:12");
            weather.setTemperiture("15");
            weathers.add(weather);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHourly.setLayoutManager(manager);
        HourlyWeatherAdapter adapter = new HourlyWeatherAdapter(this, weathers);
        rvHourly.setAdapter(adapter);

        for (int i=0;i<7;i++) {
            ForecastWeather weather = new ForecastWeather();
            weather.setDate("10-08");
            weather.setDayTxt("多云");
            weather.setNightTxt("晴");
            weather.setMaxTmp("20");
            weather.setMinTmp("10");
            weather.setWindDes("西北风");
            showForeWeather(weather);
        }
    }

    private void showForeWeather(ForecastWeather weather) {
        View itemView = LayoutInflater.from(this).inflate(R.layout.item_weather_forecast, llForecast, false);
        ((TextView)itemView.findViewById(R.id.tv_fore_date)).setText(weather.getDate());
        String weatherData = weather.getDayTxt().equals(weather.getNightTxt())
                ? weather.getDayTxt() : weather.getDayTxt() + "转" + weather.getNightTxt();
        ((TextView)itemView.findViewById(R.id.tv_fore_weather)).setText(weatherData);
        String tmpData = weather.getMinTmp() + "°/" + weather.getMaxTmp() + "°";
        ((TextView)itemView.findViewById(R.id.tv_fore_tmp)).setText(tmpData);
        ((TextView)itemView.findViewById(R.id.tv_fore_wind)).setText(weather.getWindDes());
        llForecast.addView(itemView);
    }
}
