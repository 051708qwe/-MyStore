package com.example.jessihuang.gamedemo01.Other.Adapter;

import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 引导页的适配器
 * Created by Jessi.Huang on 2016/1/11.
 */
public class GuideAdapter extends PagerAdapter {
    private List<ImageView> list;

    public GuideAdapter(List<ImageView> list) {
        this.list = list;
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView v=list.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView imgview = list.get(position);
        container.removeView(imgview);

    }
}
