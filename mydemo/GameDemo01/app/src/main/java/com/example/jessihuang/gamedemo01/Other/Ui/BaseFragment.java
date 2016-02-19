package com.example.jessihuang.gamedemo01.Other.Ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 本应用里所有的Fragnent类的父类
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.14:42
 */
public abstract class BaseFragment extends Fragment {
    /*该页面最底层布局*/
    protected View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       root= inflater.inflate(getLayout(),container,false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        iniEvents();
        initData();
    }

    /*获取布局*/
    protected abstract  int getLayout();
    /*初始化布局*/
    protected  abstract  void initViews();
    /*初始化事件*/
    protected  abstract  void iniEvents();
    /*初始化数据*/
    protected  abstract  void initData();
}
