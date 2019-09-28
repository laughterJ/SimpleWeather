package com.wiker.framework.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /**
     * 获取布局文件
     */
    public abstract int getLayout();

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();
}
