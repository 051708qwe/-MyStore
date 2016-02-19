package com.example.jessihuang.gamedemo01.Profit.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 加载页面中部viewpager使用的适配器
 * @Author is Jessi.Huang
 * @Time is 2016/1/16.13:52
 */
public class CenterViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public CenterViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
