package com.example.jessihuang.gamedemo01.Other.Ui;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessihuang.gamedemo01.R;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/13.14:32
 */
public abstract  class BaseActicity extends Activity {
    private ImageView ivLeft,ivRight;
    private TextView tvTitle,tvRight;
    protected LinearLayout linearLayout;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ivLeft=(ImageView)findViewById(R.id.tiele_back_iv);
        ivRight=(ImageView)findViewById(R.id.tiele_left_iv);
        tvTitle=(TextView)findViewById(R.id.tilte_tv);
        tvRight=(TextView)findViewById(R.id.tilte_right_tv);
        linearLayout=(LinearLayout)findViewById(R.id.base_content_ll);

        relativeLayout=(RelativeLayout)findViewById(R.id.tilte_right_rl);
        setDefaultEvent(BaseActicity.this);

        /*把子类的布局添加到titlebar下面的linearlayout中*/
        View child=getLayoutInflater().inflate(getLayout(),linearLayout);
        initViews();
        iniEvents();
        initData();
    }
    /*获取布局*/
    protected abstract  int getLayout();
    /*初始化布局*/
    protected  abstract  void initViews();
    /*初始化事件*/
    protected  abstract  void iniEvents();
    /*初始化数据*/
    protected  abstract  void initData();
    protected  void setTitleText(String text){
        if(tvTitle!=null){
            tvTitle.setText(text);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }
    protected  void setTitleText(int num){
        if(tvTitle!=null){
            tvTitle.setText(num);
            tvTitle.setVisibility(View.VISIBLE);
        }
    }
    protected  void setRightImage(int drawable){
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(drawable);


    }
    protected  void setRightText(String msg){
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(msg);
    }
    protected  void setRightText(int msg){
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(msg);
    }
    private  void setDefaultEvent(final Context context){
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    protected void serRightOnclickListener(View.OnClickListener onClickListener){
        relativeLayout.setOnClickListener(onClickListener);
    }



    protected  void showLeft(){
        ivLeft.setVisibility(View.VISIBLE);
        ivRight.setVisibility(View.VISIBLE);
    }
}
