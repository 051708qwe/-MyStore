package com.example.jessihuang.gamedemo01.Other.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求版本的接口操作
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.16:22
 */
public class OtherHttpUtil {
    /*请求版本更新的url*/
    public  static final String URL_UPGRATE="http://zhushou.72g.com/app/common/upgrade";
/*执行版本号并请求回掉*/
    public  static void requestVersion(final String version, DownLoadTask.RequestCallBack callBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {

                Map<String,String> map=new HashMap<>();
                map.put("platform","2");
                map.put("ver",version);
                return HttpUtils.doPost(URL_UPGRATE,map);
            }
        };
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }
    /*下载APK*/
    public  static void downloadApk(final  String url,DownLoadTask.RequestCallBack callBack, final DownLoadTask.UpdateProgress progress){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                return HttpUtils.downLoad(FileUtil.APK_DIR,"72G.apk",url,progress);
            }
        };
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }
}
