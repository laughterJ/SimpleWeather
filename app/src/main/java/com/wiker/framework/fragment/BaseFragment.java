package com.wiker.framework.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiker.framework.application.MyApplication;

import butterknife.ButterKnife;

/**
 * created by JH at 2019/4/19
 * des： com.wiker.framework.fragment
 */

public abstract class BaseFragment extends Fragment {

    private Context mContext;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        if (view == null) view = inflater.inflate(getLayout(), parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getContext() != null ? getContext() : MyApplication.getContext();
        initView();
        initData();
    }

    /**
     * 获取布局文件
     */
    protected abstract int getLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();
}
