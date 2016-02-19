package com.example.jessihuang.gamedemo01.Profit.Util;

import android.graphics.Bitmap;

import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;
import com.example.jessihuang.gamedemo01.Profit.InterfaceUtil.LoadImgInterface;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/16.12:49
 */
public class MyAsyncTask extends android.os.AsyncTask<String,Integer,Bitmap> {
    private LoadImgInterface loadImgInterface;

    @Override
    protected Bitmap doInBackground(String... params) {
        String url=params[0];
        Bitmap bitmap= HttpUtils.LoadBitmap(url);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        loadImgInterface=new LoadImgInterface() {
            @Override
            public void loadImg(Bitmap bitmap) {
                loadImgInterface.loadImg(bitmap);
            }
        };



    }
}
