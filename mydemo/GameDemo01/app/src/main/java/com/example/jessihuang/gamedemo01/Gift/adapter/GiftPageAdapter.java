package com.example.jessihuang.gamedemo01.Gift.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 礼包界面的ViewPager的适配器
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.18:52
 */
public class GiftPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    public GiftPageAdapter(FragmentManager fm,List<Fragment> list) {
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
