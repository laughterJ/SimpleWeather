package com.wiker.simpleweather.views.AreaPickerView;

import java.util.List;

import cn.bmob.v3.exception.BmobException;

public interface OnResponseListener {
    void onSuccess(List<Object> dataList, int dataType);
    void onError(BmobException e);
}
