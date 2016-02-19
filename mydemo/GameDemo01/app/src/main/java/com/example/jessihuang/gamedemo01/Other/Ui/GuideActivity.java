package com.example.jessihuang.gamedemo01.Other.Ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jessihuang.gamedemo01.Other.Adapter.GuideAdapter;
import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.JumpManger;
import com.example.jessihuang.gamedemo01.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 * Created by Jessi.Huang on 2016/1/11.
 */
public class GuideActivity extends Activity {
    private GuideAdapter guideAdapter;
    private ViewPager viewPager;
    private List<ImageView> list;
    private Button button;
    private ViewPager.OnPageChangeListener pageChangeListener ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager= (ViewPager) findViewById(R.id.guide_content_vp);
        button=(Button)findViewById(R.id.guide_jump_btn);
        list=new ArrayList<ImageView>();
        final int[] bitmaps=new int[]{R.drawable.bg_guide_01,
                R.drawable.bg_guide_02,
                R.drawable.bg_guide_03,
                R.drawable.bg_guide_04};
        for (int i=0;i<bitmaps.length;i++){
            ImageView img=new ImageView(this);
            img.setBackgroundResource(bitmaps[i]);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            list.add(img);
        }
        guideAdapter=new GuideAdapter(list);
        viewPager.setAdapter(guideAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //如果滑动到最后一页，显示按钮
                if (position == bitmaps.length - 1) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //点击进入主界面，并且设置一个进入的标志
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*获取指定的SharedPreferences*/
               SharedPreferences sp= getSharedPreferences(Contants.PERFERENCE_FIRST_USED
                       , Context.MODE_PRIVATE);
                /*获取一个编辑器*/
                SharedPreferences.Editor edit = sp.edit();
                /*添加一个字段表示已经不是第一次进入该应用*/
                edit.putBoolean(Contants.PERFERENCR_FLAG_USED, false);
                /*添加完自断后提交操作*/
                edit.commit();
                /*点击按钮跳转主界面*/
                JumpManger.jumpToHome(GuideActivity.this);
                /*跳转后就不需要此界面*/
                finish();
            }
        });
    }
}
