package com.wiker.simpleweather.activity;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.wiker.framework.activity.BaseActivity;
import com.wiker.simpleweather.adapter.HourlyWeatherAdapter;
import com.wiker.simpleweather.R;
import com.wiker.simpleweather.model.ForecastWeather;
import com.wiker.simpleweather.model.HourlyWeather;
import com.wiker.simpleweather.model.LivingIndexItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity{

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.rv_hourly) RecyclerView rvHourly;
    @BindView(R.id.ll_forecast) LinearLayout llForecast;
    @BindView(R.id.tv_living_index) TextView tvLivingIndex;
    @BindView(R.id.tv_living_des) TextView tvLivingDes;
    @BindView(R.id.ll_living_index) LinearLayout llLivingIndex;

    private List<LivingIndexItem> livingIndexItems;

    @Override
    public int getLayout() {
        setTheme(R.style.AppTheme);
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mToolbar.setTitle("浦东新区");
        setSupportActionBar(mToolbar);
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

        livingIndexItems = new ArrayList<>();
        for (int i=0;i<5;i++) {
            LivingIndexItem indexItem = new LivingIndexItem();
            indexItem.setType("舒适度指数");
            indexItem.setBrf("舒适");
            indexItem.setTxt("白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。");
            livingIndexItems.add(indexItem);
        }
        showLivingIndex();
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

    private void showLivingIndex() {
        tvLivingIndex.setText(String.format(getString(R.string.living_index), livingIndexItems.get(0).getBrf()));
        tvLivingDes.setText(livingIndexItems.get(0).getTxt());
        for (int i=1;i<livingIndexItems.size();i++) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.item_living_index, llLivingIndex, false);
            ((TextView)itemView.findViewById(R.id.tv_index_type)).setText(livingIndexItems.get(i).getType());
            ((TextView)itemView.findViewById(R.id.tv_index_des)).setText(livingIndexItems.get(i).getBrf());
            llLivingIndex.addView(itemView);
        }
    }
}
