package com.example.jessihuang.gamedemo01.Other.Utils;

import android.os.AsyncTask;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/13.10:59
 */
public class DownLoadTask extends AsyncTask<Void,Integer,Object> {
    private  Request request;
    private  RequestCallBack requestCallBack;

    public DownLoadTask(Request request, RequestCallBack requestCallBack) {
        if(request==null||requestCallBack==null){
            throw new NullPointerException("request or requestCallBack can not be null");
        }
        this.request = request;
        this.requestCallBack = requestCallBack;
    }

    @Override
    protected Object doInBackground(Void... params) {
        return request.doRequest();
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(request);
        /*如果请求结果为空表示请求失败*/
        if(result==null){
            requestCallBack.error("can not request");
        }else{
            /*否则请求成功*/
            requestCallBack.success(result);
        }
    }
    /*请求接口*/
    public  interface  Request{
        /*执行请求的方法 返回请求结果*/
        Object doRequest();
    }
    /*请求回调接口*/
    public interface RequestCallBack{
        /*请求成功的回调方法*/
        void success(Object result);
        /*请求失败的回调方法*/
        void error(String msg);
    }
    public interface UpdateProgress{
        /*显示进度百分比*/
        void  progressInfo(int pergress);
    }
}
