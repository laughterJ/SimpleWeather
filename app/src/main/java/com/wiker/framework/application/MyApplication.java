package com.wiker.framework.application;

import android.app.Application;
import android.content.Context;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.application
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
