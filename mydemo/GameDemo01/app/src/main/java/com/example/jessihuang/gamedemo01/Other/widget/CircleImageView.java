package com.example.jessihuang.gamedemo01.Other.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;

/**
 * 显示圆形图片的Imageview
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.13:51
 */
public class CircleImageView extends ImageView {


    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context) {
        super(context);
    }
    /*设置ImageView*/
    public void setImageUrl(final String url){
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = HttpUtils.LoadBitmap(url);
                        /*加工成圆形图片*/
                final Bitmap CircleBitmap = creatCircleBitmap(bitmap);
                /*在主现成中设置背景图片*/
                post(new Runnable() {
                    @Override
                    public void run() {
                        setImageBitmap(CircleBitmap);
                    }
                });

            }
        }.start();

    }
    /*生成圆形图片的方法*/
    private Bitmap creatCircleBitmap(Bitmap resource){
        Bitmap circle=Bitmap.createBitmap(resource.getWidth(),resource.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(circle);

        /*一张空图片 只有宽高没有内容*/
        Paint paint=new Paint();/*画圆形或者弧形时候一定要抗锯齿*/
        paint.setAntiAlias(true);

        /*画一个和源图覑宽高相同的内切圆*/
        canvas.drawCircle(resource.getWidth() / 2, resource.getWidth() / 2, resource.getWidth() / 2, paint);
        /*设置为获取交集部分*/
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(resource, 0, 0, paint);

        return  circle;
    }
}
