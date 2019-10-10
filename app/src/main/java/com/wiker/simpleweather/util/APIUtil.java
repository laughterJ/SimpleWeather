package com.wiker.simpleweather.util;

import com.google.gson.JsonObject;

public class APIUtil {

    private static final String BaseUrl = "https://free-api.heweather.net/s6/";
    private static final String Key = "96760b1b590a44a2999f6c94e48a8b53";

    // 获取常规天气数据
    public static String getConventionalWeatherUrl(String type, JsonObject param) {
        String url = BaseUrl + "weather/" + type;
        return url + getParameter(param);
    }

    // 获取常规空气质量数据
    public static String getConventionalAirUrl(JsonObject param) {
        String url = BaseUrl + "air/now";
        return url + getParameter(param);
    }

    private static String getParameter(JsonObject param) {
        StringBuilder params = new StringBuilder("?");
        for (String key : param.keySet()) {
            params.append(key).append(param.get(key).getAsString());
        }
        params.append("key").append(Key);
        return params.toString();
    }
}
