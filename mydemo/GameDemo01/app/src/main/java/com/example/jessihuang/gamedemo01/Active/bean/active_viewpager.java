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
 * @Time is 2016/1/15.20:33
 */
public class active_viewpager {

    /**
     * id : 25
     * bname : 72G游戏助手新版上线
     * target : 3
     * target_id : 49
     * bimg : http://i3.72g.com/upload/201512/201512041834559608.jpg
     * px : 1
     */

    private String id;
    private String bname;
    private String target;
    private String target_id;
    private String bimg;
    private String px;

    public static active_viewpager objectFromData(String str) {

        return new Gson().fromJson(str, active_viewpager.class);
    }

    public static active_viewpager objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), active_viewpager.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<active_viewpager> arrayactive_viewpagerFromData(String str) {

        Type listType = new TypeToken<ArrayList<active_viewpager>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<active_viewpager> arrayactive_viewpagerFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<active_viewpager>>() {
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

    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public void setPx(String px) {
        this.px = px;
    }

    public String getId() {
        return id;
    }

    public String getBname() {
        return bname;
    }

    public String getTarget() {
        return target;
    }

    public String getTarget_id() {
        return target_id;
    }

    public String getBimg() {
        return bimg;
    }

    public String getPx() {
        return px;
    }
}
