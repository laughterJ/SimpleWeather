package com.wiker.simpleweather;

import com.google.gson.JsonObject;

public class APIUtil {

    private static final String BaseUrl = "https://free-api.heweather.net/s6/";
    private static final String Key = "96760b1b590a44a2999f6c94e48a8b53";

    public static String getConventionalWeatherUrl(String type, JsonObject param) {
        String url = BaseUrl + "weather/" + type;
        return url + getParameter(param);
    }

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
