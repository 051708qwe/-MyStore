package com.example.jessihuang.gamedemo01.Active.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/15.20:37
 */
public class active_list {

    /**
     * id : 52
     * aname : 获奖名单
     * shortname : 新游《攻城掠地》火热上线
     * hotpic : http://i3.72g.com/upload/201512/201512101040321263.jpg
     * astime : 2015-12-10 10:00
     * aetime : 2015-12-16 10:00
     * total_join_user : 41
     * status : 已结束
     */

    private String id;
    private String aname;
    private String shortname;
    private String hotpic;
    private String astime;
    private String aetime;
    private int total_join_user;
    private String status;

    public static active_list objectFromData(String str) {

        return new Gson().fromJson(str, active_list.class);
    }

    public static active_list objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), active_list.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<active_list> arrayactive_listFromData(String str) {

        Type listType = new TypeToken<ArrayList<active_list>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<active_list> arrayactive_listFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<active_list>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setHotpic(String hotpic) {
        this.hotpic = hotpic;
    }

    public void setAstime(String astime) {
        this.astime = astime;
    }

    public void setAetime(String aetime) {
        this.aetime = aetime;
    }

    public void setTotal_join_user(int total_join_user) {
        this.total_join_user = total_join_user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getAname() {
        return aname;
    }

    public String getShortname() {
        return shortname;
    }

    public String getHotpic() {
        return hotpic;
    }

    public String getAstime() {
        return astime;
    }

    public String getAetime() {
        return aetime;
    }

    public int getTotal_join_user() {
        return total_join_user;
    }

    public String getStatus() {
        return status;
    }
}
