package com.wiker.simpleweather.model;

import com.google.gson.annotations.SerializedName;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

public class Town extends BmobObject {

    @SerializedName("id")
    private int townId;
    @SerializedName("name")
    private String townName;
    @SerializedName("areacode")
    private String townCode;
    @SerializedName("parentid")
    private int provinceId;
    private BmobRelation mCity;

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public BmobRelation getmCity() {
        return mCity;
    }

    public void setmCity(BmobRelation mCity) {
        this.mCity = mCity;
    }
}
