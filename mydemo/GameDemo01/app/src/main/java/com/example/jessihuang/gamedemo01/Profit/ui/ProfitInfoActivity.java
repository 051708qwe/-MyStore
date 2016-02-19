package com.example.jessihuang.gamedemo01.Profit.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Other.Ui.BaseActicity;
import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;
import com.example.jessihuang.gamedemo01.Other.Utils.LogUtil;
import com.example.jessihuang.gamedemo01.Other.widget.CircleImageView;
import com.example.jessihuang.gamedemo01.Other.widget.ShapePopWindow;
import com.example.jessihuang.gamedemo01.Profit.Fragment.GameGiftFragment;
import com.example.jessihuang.gamedemo01.Profit.Fragment.GameInfoFragment;
import com.example.jessihuang.gamedemo01.Profit.Fragment.GameTalkFragment;
import com.example.jessihuang.gamedemo01.Profit.Util.ProfitUtil;
import com.example.jessihuang.gamedemo01.Profit.adapter.CenterViewPagerAdapter;
import com.example.jessihuang.gamedemo01.Profit.adapter.ProfitPageAdapter;
import com.example.jessihuang.gamedemo01.Profit.bean.Profit;
import com.example.jessihuang.gamedemo01.Profit.bean.ProfitInfo;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.20:41
 */
public class ProfitInfoActivity extends FragmentActivity implements View.OnClickListener{
    private ImageView img_title_back,img_title_share,img_large,top_icon;
    private RelativeLayout relativeLayout;
    private  String img_large_url;
    private  String []img_large_urls;
    private  Intent intent;
    private Bundle bundle;
    private List<Fragment> list;
    private GameInfoFragment fragment_game;
    private GameGiftFragment fragment_gift;
    private GameTalkFragment fragment_talk;
    private CenterViewPagerAdapter centerViewPagerAdapter;
    private LinearLayout linearLayout_two,linearLayout_one;
    private TextView[] textViews;



    private ImageLoader imageLoader,imageLoader1;
    private TextView ganmeName,gameVer,gameSize,gameLoadTime,gameInfo,gameGift,gameTalk;
    private RatingBar ratingBar;
    private ViewPager viewPagerCenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profitinfo);
        initView();
        initData();
        initEvent();


    }

    private void initData() {
        //接受上一个界面传过来的id用于下载游戏详情的数据
        Intent it=getIntent();
        Bundle b=it.getExtras();
        String id=b.getString("id");
        ProfitUtil.requestGameInfo(id, new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                //请求成功，开始下载数据
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                    if (Contants.SUCCESS_STATE.equals(state)) {
                        //解析
                        JSONObject info = jsonObject.getJSONObject("info");
                        ProfitInfo profitInfo = ProfitInfo.objectFromData(info.toString());
                        //获取图片组的下载地址
                        imageLoader1 = new ImageLoader(ProfitInfoActivity.this);
                        img_large_url = profitInfo.getSnapshot();
                        img_large_urls = img_large_url.split(",");
                        imageLoader1.DisplayImage(img_large_urls[0], img_large);
                       // LogUtil.w("-------0", img_large_url + "");
                        //LogUtil.w("-------0", img_large_urls.length + "");
                        imageLoader = new ImageLoader(ProfitInfoActivity.this);
                        imageLoader.DisplayImage(profitInfo.getIcon(), top_icon);

                        ganmeName.setText(profitInfo.getName());
                        String score = profitInfo.getScore();
                        float s = Float.valueOf(score);
                        ratingBar.setProgress((int) s);
                        String gameVersion = getString(R.string.profit_info_ver, profitInfo.getVersion());
                        gameVer.setText(gameVersion);
                        gameSize.setText(profitInfo.getSize());
                        String loadTime = getString(R.string.profit_info_download, profitInfo.getCount_dl());
                        gameLoadTime.setText(loadTime);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(String msg) {
                LogUtil.w("tag", "数据加载出错，请检查你的网络设置");

            }
        });
        //加载布局中部的ViewPager的数据
        initViewPageData();

    }

    private void initViewPageData() {
        list=new ArrayList<Fragment>();
        fragment_game=new GameInfoFragment();
        fragment_gift=new GameGiftFragment();
        fragment_talk=new GameTalkFragment();
        list.add(fragment_game);
        list.add(fragment_gift);
        list.add(fragment_talk);
        centerViewPagerAdapter=new CenterViewPagerAdapter(getSupportFragmentManager(),list);
        viewPagerCenter.setAdapter(centerViewPagerAdapter);



    }

    private void initEvent() {
        img_title_back.setOnClickListener(this);
        img_title_share.setOnClickListener(this);
        img_large.setOnClickListener(this);
        gameInfo.setOnClickListener(this);
        gameTalk.setOnClickListener(this);
        gameGift.setOnClickListener(this);
        viewPagerCenter.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    gameInfo.setSelected(true);
                    gameGift.setSelected(false);
                    gameTalk.setSelected(false);
                    linearLayout_two.setVisibility(View.VISIBLE);
                    linearLayout_one.setVisibility(View.INVISIBLE);
                }
                if(position==1){
                    gameInfo.setSelected(false);
                    gameGift.setSelected(true);
                    gameTalk.setSelected(false);
                    linearLayout_two.setVisibility(View.VISIBLE);
                    linearLayout_one.setVisibility(View.INVISIBLE);
                }
                if(position==2){
                    gameInfo.setSelected(false);
                    gameGift.setSelected(false);
                    gameTalk.setSelected(true);
                    linearLayout_one.setVisibility(View.VISIBLE);
                    linearLayout_two.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void initView() {
        img_title_back=(ImageView)findViewById(R.id.title_back_iv);
        img_title_share=(ImageView)findViewById(R.id.title_share_iv);
        relativeLayout=(RelativeLayout)findViewById(R.id.profit_info_show);
        img_large=(ImageView)findViewById(R.id.profit_large_img);
        top_icon=(ImageView)findViewById(R.id.profit_game_icon);
        ganmeName=(TextView)findViewById(R.id.profit_ganme_name);
        ratingBar=(RatingBar)findViewById(R.id.profit_ratingbar);
        gameVer=(TextView)findViewById(R.id.profit_top_ver_info);
        gameSize=(TextView)findViewById(R.id.profit_top_game_size);
        gameLoadTime=(TextView)findViewById(R.id.profit_top_download_time);
        viewPagerCenter=(ViewPager)findViewById(R.id.profit_viewpager_center);
        linearLayout_one=(LinearLayout)findViewById(R.id.profit_one_button);
        linearLayout_two=(LinearLayout)findViewById(R.id.profit_two_button);
        gameInfo=(TextView)findViewById(R.id.profit_check_game_info);
        gameGift=(TextView)findViewById(R.id.profit_check_gift);
        gameTalk=(TextView)findViewById(R.id.profit_check_talk);
        textViews=new TextView[]{gameInfo,gameGift,gameTalk};
        gameInfo.setSelected(true);


    }
  

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.title_share_iv:
                ShapePopWindow popWindow=new ShapePopWindow(ProfitInfoActivity.this);
                //popWindow.showAsDropDown(ivHead);
                popWindow.showAtLocation(relativeLayout, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL
                        ,0,0);
                break;
            case R.id.profit_large_img:
                intent=new Intent(ProfitInfoActivity.this,ProfitLargeImgActivity.class);
                bundle=new Bundle();
                bundle.putStringArray("large_urls", img_large_urls);
                intent.putExtras(bundle);
                startActivity(intent);
                //topViewPager.setVisibility(View.VISIBLE);
                break;
            case R.id.profit_check_game_info:
                viewPagerCenter.setCurrentItem(0);
                linearLayout_two.setVisibility(View.VISIBLE);
                linearLayout_one.setVisibility(View.INVISIBLE);
                break;
            case R.id.profit_check_gift:
                viewPagerCenter.setCurrentItem(1);
                linearLayout_two.setVisibility(View.VISIBLE);
                linearLayout_one.setVisibility(View.INVISIBLE);
                break;
            case R.id.profit_check_talk:
                viewPagerCenter.setCurrentItem(2);
                linearLayout_one.setVisibility(View.VISIBLE);
                linearLayout_two.setVisibility(View.INVISIBLE);
                break;

        }

    }



}
