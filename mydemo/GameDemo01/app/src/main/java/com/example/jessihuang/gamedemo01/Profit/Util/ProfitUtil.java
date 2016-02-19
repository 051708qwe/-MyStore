package com.example.jessihuang.gamedemo01.Profit.Util;

import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**赚钱模块的网络请求
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.20:01
 */
public class ProfitUtil {
    public static final String URL_GAME="http://zhushou.72g.com/app/game/game_list/";
    public  static  final String URL_GAMEINFO="http://zhushou.72g.com/app/game/game_info/";
    public  static  final String URL_GUESS_LIKE="http://zhushou.72g.com/app/game/game_like/";
/*游戏下载评分列表*/
    public static  void requestProfitList(final int page,DownLoadTask.RequestCallBack callBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> map=new HashMap<>();
                map.put("platform","2");
                 map.put("page",page+"");
                return HttpUtils.doPost(URL_GAME,map);
            }
        };
        /*创建一个下载任务*/
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }
    public  static void requestGameInfo(final String id,DownLoadTask.RequestCallBack callBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> map=new HashMap<>();
                map.put("id",id);
                return HttpUtils.doPost(URL_GAMEINFO,map);
            }
        };
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }
    public  static void downloadImg(final String url,DownLoadTask.RequestCallBack callBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {

                return HttpUtils.LoadBitmap(url);
            }
        };
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }
    public  static  void requestGuessLike(final String id, DownLoadTask.RequestCallBack callBack){
        DownLoadTask.Request request=new DownLoadTask.Request() {
            @Override
            public Object doRequest() {
                Map<String,String> map=new HashMap<>();
                map.put("platform","2");
                map.put("id",id);
                return HttpUtils.doPost(URL_GUESS_LIKE,map);
            }
        };
        DownLoadTask task=new DownLoadTask(request,callBack);
        task.execute();
    }

}
