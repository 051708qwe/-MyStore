package com.example.jessihuang.gamedemo01.Other.Utils;

import android.app.Activity;
import android.content.Intent;

import com.example.jessihuang.gamedemo01.Other.Ui.HomeActivity;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/11.17:03
 */
public class JumpManger {
    /*activity发起页面
    * 跳转到主界面*/
    public  static  void jumpToHome(Activity activity){
        Intent  it=new Intent(activity, HomeActivity.class);
        activity.startActivity(it);

    }
}
