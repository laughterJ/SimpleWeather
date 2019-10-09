package com.wiker.simpleweather;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wiker.framework.activity.BaseActivity;
import com.wiker.simpleweather.model.HourlyWeather;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity{

    @BindView(R.id.rv_hourly) RecyclerView rvHourly;

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
    }
}
