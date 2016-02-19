package com.example.jessihuang.gamedemo01.Gift.Util;

import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 礼包模块的网络请求
 * @Author is Jessi.Huang
 * @Time is 2016/1/13.11:14
 */
public class GiftUtil {
    public  static  final  String URL_GIFT_LIST="http://zhushou.72g.com/app/gift/gift_list/";
    public  static  final  String URL_GIFT_INFO="http://zhushou.72g.com/app/gift/gift_info/";
    /*请求礼包列表*/
    public  static  void requestGiftList(final int page,final  int type,DownLoadTask.RequestCallBack requestCallBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> map=new HashMap<String, String>();
                map.put("platform","2");
                map.put("gifttype",type+"");
                map.put("page",page+"");
                return HttpUtils.doPost(URL_GIFT_LIST,map);
            }
        };

        //创建一个任务
        DownLoadTask downLoadTask=new DownLoadTask(request,requestCallBack);
        downLoadTask.execute();
    }
    /*请求礼包详情*/
    public  static void requestGiftInfo(final  String id,DownLoadTask.RequestCallBack requestCallBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> map=new HashMap<String, String>();
                map.put("id",id);

                return HttpUtils.doPost(URL_GIFT_INFO,map);
            }
        };
        DownLoadTask downLoadTask=new DownLoadTask(request,requestCallBack);
        downLoadTask.execute();
    }
}
