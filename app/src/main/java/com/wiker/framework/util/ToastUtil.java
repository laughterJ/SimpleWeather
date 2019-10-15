package com.wiker.framework.util;

import android.content.Context;
import android.widget.Toast;

import com.wiker.framework.application.MyApplication;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.util
 */

public class ToastUtil {

    public static void showShortToast(String msg){
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
