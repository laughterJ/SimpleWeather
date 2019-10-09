package com.wiker.simpleweather.model;

import com.google.gson.annotations.SerializedName;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class City extends BmobObject {

    @SerializedName("id")
    private int cityId;
    @SerializedName("name")
    private String cityName;
    @SerializedName("areacode")
    private String cityCode;
    @SerializedName("parentid")
    private int provinceId;
    private BmobRelation mProvince;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public BmobRelation getmProvince() {
        return mProvince;
    }

    public void setmProvince(BmobRelation mProvince) {
        this.mProvince = mProvince;
    }
}
