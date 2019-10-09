package com.wiker.simpleweather.model;

public class ForecastWeather {

    private String date;
    private String dayTxt;
    private String nightTxt;
    private String maxTmp;
    private String minTmp;
    private String windDes;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayTxt() {
        return dayTxt;
    }

    public void setDayTxt(String dayTxt) {
        this.dayTxt = dayTxt;
    }

    public String getNightTxt() {
        return nightTxt;
    }

    public void setNightTxt(String nightTxt) {
        this.nightTxt = nightTxt;
    }

    public String getMaxTmp() {
        return maxTmp;
    }

    public void setMaxTmp(String maxTmp) {
        this.maxTmp = maxTmp;
    }

    public String getMinTmp() {
        return minTmp;
    }

    public void setMinTmp(String minTmp) {
        this.minTmp = minTmp;
    }

    public String getWindDes() {
        return windDes;
    }

    public void setWindDes(String windDes) {
        this.windDes = windDes;
    }
}
