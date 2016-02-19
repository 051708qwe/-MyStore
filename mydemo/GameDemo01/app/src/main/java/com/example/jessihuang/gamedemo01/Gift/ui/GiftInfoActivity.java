package com.example.jessihuang.gamedemo01.Gift.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Gift.Util.GiftUtil;
import com.example.jessihuang.gamedemo01.Gift.bean.GiftInfo;
import com.example.jessihuang.gamedemo01.Other.Ui.BaseActicity;
import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;
import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;
import com.example.jessihuang.gamedemo01.Other.Utils.LogUtil;
import com.example.jessihuang.gamedemo01.Other.widget.CircleImageView;
import com.example.jessihuang.gamedemo01.Other.widget.ShapePopWindow;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/13.14:29
 */
public class GiftInfoActivity extends BaseActicity {
    private ProgressBar progressBar;
    private CircleImageView ivHead;
    private ImageView ivAndroid,ivIos;
    private TextView tvName,tvVialadity,tvUbi,tvSize,tvType
            ,tvContent,tvGet,per_tv;
    private RelativeLayout rela,perPela;
    private int platForm;
    private Button perButButton,perTopButton,lastButton1,lastButton2;


    @Override
    protected int getLayout() {
        return R.layout.activity_giftinfo;
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.gift_info_title);
        setRightImage(R.drawable.selector_share);
        showLeft();
        ivHead=(CircleImageView)findViewById(R.id.gift_info_head_iv);
        tvName=(TextView)findViewById(R.id.gift_info_name);
        tvVialadity=(TextView)findViewById(R.id.gift_beforeDate_tv);
        tvUbi=(TextView)findViewById(R.id.gift_Ub);
        tvSize=(TextView)findViewById(R.id.gift_info_game_size_tv);
        tvType=(TextView)findViewById(R.id.gift_info_game_type_tv);
        tvContent=(TextView)findViewById(R.id.gift_info_content_tv);
        tvGet=(TextView)findViewById(R.id.gift_info_get_tv);
        rela=(RelativeLayout)findViewById(R.id.gift_info_rl);
        ivAndroid=(ImageView)findViewById(R.id.gift_icon_android);
        ivIos=(ImageView)findViewById(R.id.gift_icon_ios);
        lastButton1=(Button)findViewById(R.id.gift_btn1);
        lastButton2=(Button)findViewById(R.id.gift_btn2);
        per_tv=(TextView)findViewById(R.id.id_per_tv);
        progressBar=(ProgressBar)findViewById(R.id.gift_pro);

    }

    @Override
    protected void iniEvents() {
        serRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*显示分享菜单*/
                ShapePopWindow popWindow=new ShapePopWindow(GiftInfoActivity.this);
                //popWindow.showAsDropDown(ivHead);
                popWindow.showAtLocation(linearLayout, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL
                        ,0,0);
            }
        });

    }

    @Override
    protected void initData() {



        /*获取从上一页传进来的ID*/
        String id=getIntent().getStringExtra("id");
        GiftUtil.requestGiftInfo(id, new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                    /*如果请求成功开始解析*/
                    if (Contants.SUCCESS_STATE.equals(state)) {
                        JSONObject info = jsonObject.getJSONObject("info");
                        GiftInfo giftInfo = GiftInfo.objectFromData(info.toString());
                        LogUtil.w("----->", giftInfo.toString());
                        ImageLoader imageLoader = new ImageLoader(GiftInfoActivity.this);
                        imageLoader.DisplayImage(giftInfo.getIcon(), ivHead);
                        String url = giftInfo.getIcon();
                        /*用httpurl下载图片*/
                        //DownLoadBitmap(url);
                        ivHead.setImageUrl(url);
                        tvName.setText(giftInfo.getName());
                        String Ubi = getResources().getString(R.string.gift_info_cosume, giftInfo.getConsume());
                        tvUbi.setText(Ubi);
                        String validity = getString(R.string.gift_info_beforedate, giftInfo.getStime(), giftInfo.getEtime()
                        );
                        tvVialadity.setText(validity);
                        String size = getString(R.string.gift_info_size, giftInfo.getSize());
                        String type = getString(R.string.gift_info_type, giftInfo.getGame_type());
                        /*如果游戏类型或者游戏大小为null字符串时候 不显示下载*/
                        String game_type = giftInfo.getGame_type();
                        String game_size = giftInfo.getSize();
                        if (!(game_size == null || game_type == null)) {
                            rela.setVisibility(View.VISIBLE);
                            tvSize.setText(size);
                            tvType.setText(type);
                        }
                        //设置剩余百分比
                        String pe = getString(R.string.gift_info_per, giftInfo.getRemain(), giftInfo.getTotal());
                        per_tv.setText(pe);
                        int remain = giftInfo.getRemain();
                        int total = Integer.valueOf(giftInfo.getTotal());
                        progressBar.setMax(total);
                        progressBar.setProgress(remain);


                        if (remain != 0) {
                            lastButton1.setVisibility(View.GONE);
                            lastButton2.setVisibility(View.VISIBLE);

                        } else {
                            lastButton1.setVisibility(View.VISIBLE);
                            lastButton2.setVisibility(View.GONE);


                        }

                        Spanned conetnt = Html.fromHtml(giftInfo.getContent());
                        tvContent.setText(conetnt);
                        Spanned method = Html.fromHtml(giftInfo.getHowget());
                        tvGet.setText(method);


                        platForm = Integer.valueOf(giftInfo.getPlatform());
                        if (platForm == 2) {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.INVISIBLE);

                        }
                        if (platForm == 1) {
                            ivAndroid.setVisibility(View.INVISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }
                        if (platForm == 3) {
                            ivAndroid.setVisibility(View.VISIBLE);
                            ivIos.setVisibility(View.VISIBLE);
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void error(String msg) {

            }

        });
    }
}



