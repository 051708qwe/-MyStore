package com.example.jessihuang.gamedemo01.Other.Ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.FileUtil;
import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;
import com.example.jessihuang.gamedemo01.Other.Utils.JumpManger;
import com.example.jessihuang.gamedemo01.Other.Utils.OtherHttpUtil;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class MainActivity extends Activity {
    private ImageView ivIcon,ivLabel;
    /*
    * 主界面*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            /*获取包的信息*/
            final PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            if(packageInfo!=null){
                OtherHttpUtil.requestVersion(packageInfo.versionName, new DownLoadTask.RequestCallBack() {
                    @Override
                    public void success(Object result) {
                         /*如果请求成功显示一个对话框*/
                        try {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            String string = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                            if (Contants.SUCCESS_STATE.equals(string)) {
                                JSONObject info=jsonObject.getJSONObject("info");
                                String msg=info.getString("msg");
                                String apk=info.getString("src");
                                String ver=info.getString("ver");
                                if(ver.equals(packageInfo.versionName)){
                                    return;
                                }else{
                                Dialog dialog = getDialog(msg,apk);

                                dialog.show();

                            }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void error(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        /*如果第一次使用，跳转引导页*/
        if(isFirstUse()){
        Intent it=new Intent(MainActivity.this,GuideActivity.class);
        startActivity(it);
        finish();
        }
        /*否则进入主界面*/
        else{
            setContentView(R.layout.activity_main);
            ivIcon= (ImageView) findViewById(R.id.main_logo_iv);
            ivLabel= (ImageView) findViewById(R.id.main_label_iv);
            //初始化label的动画
            final Animation anmi1 = AnimationUtils.loadAnimation(
                    MainActivity.this, R.anim.anim_main_icon_in);
            Animation anmi= AnimationUtils.loadAnimation(this,R.anim.anim_main_label_in);
            ivLabel.setAnimation(anmi);
            /*标题的动画监听，在此动画结束后执行icon的动画*/
            anmi.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    ivIcon.setAnimation(anmi1);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            /*icon的动画监听*/
            anmi1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //在icon的动画结束后设置程可见
                    ivIcon.setVisibility(View.VISIBLE);
                    /*动画结束跳转主界面*/
                    JumpManger.jumpToHome(MainActivity.this);
                    /*跳转后此界面不需要*/
                    finish();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }

    @NonNull
    private Dialog getDialog(  String msg,final String apk) {
        final Dialog dialog = new Dialog(MainActivity.this, R.style.upgrate_dialog);
        dialog.setContentView(R.layout.dialog_upgrate);
        TextView tvMsg = (TextView) dialog.findViewById(R.id.up_msg_tv);
        Spanned spanned = Html.fromHtml(msg);
        tvMsg.setText(spanned);
        Button cancel= (Button) dialog.findViewById(R.id.up_cancel_tv);
        final Button ok=(Button)dialog.findViewById(R.id.up_ok_tv);
        final ProgressBar p=(ProgressBar)dialog.findViewById(R.id.gift_pro);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                p.setVisibility(View.VISIBLE);
                ok.setEnabled(false);
                /*执行下载apk任务*/
        OtherHttpUtil.downloadApk(apk, new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                File apk = (File) result;
                FileUtil.installApk(MainActivity.this, apk);
            }

            @Override
            public void error(String msg) {
                Toast.makeText(MainActivity.this, "下载失败，请检查网络", Toast.LENGTH_SHORT).show();

            }
        }, new DownLoadTask.UpdateProgress() {
            @Override
            public void progressInfo(int pergress) {
                p.setProgress(pergress);
            }
        });
            }
        });

        return dialog;
    }

    private boolean isFirstUse(){
      SharedPreferences sp= getSharedPreferences(Contants.PERFERENCE_FIRST_USED,Context.MODE_PRIVATE);
       return sp.getBoolean(Contants.PERFERENCR_FLAG_USED,true);
    }
}
