package com.wiker.simpleweather.views.AreaPickerView;

import android.view.View;

import com.wiker.simpleweather.model.CityModel;
import com.wiker.simpleweather.model.ProvinceModel;
import com.wiker.simpleweather.model.TownModel;

import java.util.List;

public class AreaPickerView {

    // View 集合
    private List<View> views;
    // TabLayout 标识
    private List<String> tabNames;
    // 省级数据
    private List<ProvinceModel.Province> provinceList;
    // 市级数据
    private List<CityModel.City> cityList;
    // 县级数据
    private List<TownModel.Town> townList;
}
