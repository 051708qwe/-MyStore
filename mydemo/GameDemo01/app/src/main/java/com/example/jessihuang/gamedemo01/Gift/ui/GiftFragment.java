package com.example.jessihuang.gamedemo01.Gift.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Gift.adapter.GiftPageAdapter;
import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 礼包界面
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.10:36
 */
public class GiftFragment extends BaseFragment{
    private List<Map<String,Object>> list;
    private ViewPager viewPager;
    private GiftPageAdapter pageAdapter;
    private TextView tvMbile,tvWeb;
    GiftTypeFragment mobilefragment;
    GiftTypeFragment webfragment;

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id=v.getId();
            int index=0;
            /*如果点击手游礼包*/
            if(id==R.id.gift_type_mobile_tv){
           /* viewPager.setCurrentItem(0);*/
                index=0;
                /*tvMbile.setSelected(true);
                tvWeb.setSelected(false);*/
            }else if(id==R.id.gift_type_web_tv){
                //如果点击页游礼包
                index=1;
               /* viewPager.setCurrentItem(1);
                tvWeb.setSelected(true);
                tvMbile.setSelected(false);*/
            }
            viewPager.setCurrentItem(index);
            boolean isChecked=index==0?true:false;
            tvMbile.setSelected(isChecked);
            tvWeb.setSelected(!isChecked);
        }
    };
    private  ViewPager.OnPageChangeListener pageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position==0){
                tvMbile.setSelected(true);
                tvWeb.setSelected(false);
            }else if(position==1){
                tvMbile.setSelected(false);
                tvWeb.setSelected(true);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    protected int getLayout() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initViews() {
        viewPager= (ViewPager)root.findViewById(R.id.gift_type_vp);
        tvMbile=(TextView)root.findViewById(R.id.gift_type_mobile_tv);
        tvWeb=(TextView)root.findViewById(R.id.gift_type_web_tv);
        tvMbile.setSelected(true);

    }

    @Override
    protected void iniEvents() {
        tvMbile.setOnClickListener(onClickListener);
        tvWeb.setOnClickListener(onClickListener);
        viewPager.setOnPageChangeListener(pageChangeListener);
    }

    @Override
    protected void initData() {
        mobilefragment=new GiftTypeFragment(1);
         webfragment=new GiftTypeFragment(2);
        List<Fragment> fragmentlist=new ArrayList<>();
        fragmentlist.add(mobilefragment);
        fragmentlist.add(webfragment);
        pageAdapter=new GiftPageAdapter(getFragmentManager(),fragmentlist);
        viewPager.setAdapter(pageAdapter);


    }


}
