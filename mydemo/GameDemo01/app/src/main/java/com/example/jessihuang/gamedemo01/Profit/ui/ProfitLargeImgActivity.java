package com.example.jessihuang.gamedemo01.Profit.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;
import com.example.jessihuang.gamedemo01.Other.Utils.LogUtil;
import com.example.jessihuang.gamedemo01.Profit.InterfaceUtil.LoadImgInterface;
import com.example.jessihuang.gamedemo01.Profit.Util.MyAsyncTask;
import com.example.jessihuang.gamedemo01.Profit.Util.ProfitUtil;
import com.example.jessihuang.gamedemo01.Profit.adapter.ViewPagerAdapter;
import com.example.jessihuang.gamedemo01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/16.12:39
 */
public class ProfitLargeImgActivity extends Activity implements LoadImgInterface {
    private ViewPager viewPager;
    private List<ImageView> imgList;
    private  int i;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit_view_pager);
        //初始化控件
        viewPager=(ViewPager)findViewById(R.id.profit_viewpager);
        imgList=new ArrayList<ImageView>();
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        final String[] urls=bundle.getStringArray("large_urls");
        for (int i = 0; i <urls.length-1 ; i++) {
            new MyAsyncTask().execute(urls[i]);
        }




    }

    @Override
    public void loadImg(Bitmap bitmap) {
        ImageView imageView=new ImageView(ProfitLargeImgActivity.this);
        imageView.setImageBitmap(bitmap);
        imgList.add(imageView);
        viewPagerAdapter=new ViewPagerAdapter(imgList,ProfitLargeImgActivity.this);
        viewPager.setAdapter(viewPagerAdapter);
    }
}
