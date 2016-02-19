package com.example.jessihuang.gamedemo01.Profit.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.Profit.ui.ProfitInfoFragment;

import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/15.17:54
 */
public class ProfitPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public ProfitPageAdapter(FragmentManager fm,List<Fragment> list) {
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
