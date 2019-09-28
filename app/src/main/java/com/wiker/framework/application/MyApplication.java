package com.wiker.framework.application;

import android.app.Application;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.application
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static MyApplication getInstance() {
        if (mInstance == null){
            mInstance = new MyApplication();
        }
        return mInstance;
    }
}
