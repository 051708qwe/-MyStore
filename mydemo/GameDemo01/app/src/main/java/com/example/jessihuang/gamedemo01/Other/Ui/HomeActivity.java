package com.example.jessihuang.gamedemo01.Other.Ui;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.jessihuang.gamedemo01.Active.ui.ActivityFragment;
import com.example.jessihuang.gamedemo01.Exchange.ui.ExchangeFragment;
import com.example.jessihuang.gamedemo01.Gift.ui.GiftFragment;
import com.example.jessihuang.gamedemo01.My.ui.MyFragment;
import com.example.jessihuang.gamedemo01.Profit.ui.ProfitFragment;
import com.example.jessihuang.gamedemo01.R;

/**
 * 主界面
 * @Author is Jessi.Huang
 * @Time is 2016/1/11.16:57
 */
public class HomeActivity extends FragmentActivity {
private  Animation animation;
    private  Animation animation_nomal;
    private RadioGroup radioGroup;
    private  RadioButton rb1,rb2,rb3,rb4,rb5;
    private android.support.v4.app.Fragment[] fragments;
    private FrameLayout frameLayout;
    private   FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_home);
        radioGroup = (RadioGroup) findViewById(R.id.home_rg);
        initRadioButton();
        frameLayout = (FrameLayout) findViewById(R.id.home_content_fl);
        fragments = new android.support.v4.app.Fragment[]{
                new ProfitFragment(),
                new GiftFragment(),
                new ActivityFragment(),
                new ExchangeFragment(),
                new MyFragment()
        };

         fragmentManager = getSupportFragmentManager();
        final android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            android.support.v4.app.Fragment fragment = fragments[i];
            fragmentTransaction.add(R.id.home_content_fl, fragment);
            fragmentTransaction.hide(fragment);
        }

        /*默认显示礼包界面*/
        fragmentTransaction.show(fragments[1]);
        fragmentTransaction.commit();
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
              if(checkedId==R.id.home_profit_rb){
                  setAnimation(rb1,rb2,rb3,rb4,rb5);
                  showPage(0,1,2,3,4);
                  //Toast.makeText(HomeActivity.this,"11",Toast.LENGTH_SHORT).show();
                  //fragmentTransaction.show(fragments[0]);


              }
               if(checkedId==R.id.home_gift_rb){
                   setAnimation(rb2, rb1, rb3, rb4, rb5);
                   showPage(1,0, 2, 3, 4);
                  // fragmentTransaction.show(fragments[1]);
               }
               if(checkedId==R.id.home_active_rb){

                   setAnimation(rb3, rb1, rb2, rb4, rb5);
                   showPage(2, 0, 1, 3, 4);
               }
               if(checkedId==R.id.home_exchange_rb){

                   setAnimation(rb4,rb1,rb2,rb3,rb5);
                   showPage(3, 0, 2, 1, 4);
               }
               if(checkedId==R.id.home_my_rb){

                   setAnimation(rb5,rb1,rb2,rb3,rb4);
                   showPage(4,1,2,3,0);
               }



           }
       });


    }
    /*点击开始动画的方法，第一个参数为开始动画的按钮剩下四个为取消动画的按钮*/
    private void setAnimation(RadioButton rb,RadioButton rb1,RadioButton rb2,RadioButton rb3,RadioButton rb4){
        animation=AnimationUtils.loadAnimation(HomeActivity.this,R.anim.anmi_home_radiobutton);
        rb.startAnimation(animation);
        animation.setFillAfter(true);
        rb1.clearAnimation();
        rb2.clearAnimation();
        rb3.clearAnimation();
        rb4.clearAnimation();






    }
    private void initRadioButton() {
        rb1=(RadioButton)findViewById(R.id.home_profit_rb);
        rb2=(RadioButton)findViewById(R.id.home_gift_rb);
        rb3=(RadioButton)findViewById(R.id.home_active_rb);
        rb4=(RadioButton)findViewById(R.id.home_exchange_rb);
        rb5=(RadioButton)findViewById(R.id.home_my_rb);

    }
    private  void showPage(int a,int b,int c,int d,int e){
        FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        fragmentTransaction.show(fragments[a]);
        fragmentTransaction.hide(fragments[b]);
        fragmentTransaction.hide(fragments[c]);
        fragmentTransaction.hide(fragments[d]);
        fragmentTransaction.hide(fragments[e]);
        fragmentTransaction.commit();
    }
}

