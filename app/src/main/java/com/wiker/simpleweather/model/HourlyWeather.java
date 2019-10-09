package com.wiker.simpleweather.model;

public class HourlyWeather {

    private String cloud;
    private String condDes;
    private String dateTime;
    private String temperiture;
    private String windDes;

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getCondDes() {
        return condDes;
    }

    public void setCondDes(String condDes) {
        this.condDes = condDes;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTemperiture() {
        return temperiture;
    }

    public void setTemperiture(String temperiture) {
        this.temperiture = temperiture;
    }

    public String getWindDes() {
        return windDes;
    }

    public void setWindDes(String windDes) {
        this.windDes = windDes;
    }
}
