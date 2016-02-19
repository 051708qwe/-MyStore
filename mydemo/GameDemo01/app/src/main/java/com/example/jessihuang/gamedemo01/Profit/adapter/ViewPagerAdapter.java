package com.example.jessihuang.gamedemo01.Profit.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;

import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/15.15:28
 */
public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imgList;
    private Context context;

    public ViewPagerAdapter(List<ImageView> imgList, Context context) {
        this.imgList = imgList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = imgList.get(position);
        container.addView(imageView);
        return imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgList.get(position));
    }
}
