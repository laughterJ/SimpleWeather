package com.wiker.simpleweather;

import com.wiker.framework.activity.BaseActivity;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Bmob.initialize(this, "81ca7be0d101e76d8a7e0066518a0cdc");

    }
}
