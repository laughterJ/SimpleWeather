package com.wiker.simpleweather.model;

import com.google.gson.annotations.SerializedName;

import cn.bmob.v3.BmobObject;

public class Province extends BmobObject {

    @SerializedName("id")
    private int provinceId;
    @SerializedName("name")
    private String provinceName;
    @SerializedName("areacode")
    private String provinceCode;

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }
}
