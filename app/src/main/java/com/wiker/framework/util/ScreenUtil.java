package com.wiker.framework.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtil {

    public static int getScreenWidth(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
