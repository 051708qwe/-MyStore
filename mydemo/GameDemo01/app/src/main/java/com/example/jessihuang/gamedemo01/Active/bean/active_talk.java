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
 * @Time is 2016/1/15.20:41
 */
public class active_talk {

    /**
     * id : 51319
     * uid : 318351747
     * content : .skqnxnm
     * img :
     * litpic :
     * pubdate : 2015-12-15 23:27
     * floor : 41
     * nickname : d15845342573
     * hpic : http://i3.72g.com/upload/201512/201512160029034346.png
     */

    private String id;
    private String uid;
    private String content;
    private String img;
    private String litpic;
    private String pubdate;
    private String floor;
    private String nickname;
    private String hpic;

    public static active_talk objectFromData(String str) {

        return new Gson().fromJson(str, active_talk.class);
    }

    public static active_talk objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), active_talk.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<active_talk> arrayactive_talkFromData(String str) {

        Type listType = new TypeToken<ArrayList<active_talk>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<active_talk> arrayactive_talkFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<active_talk>>() {
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

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setLitpic(String litpic) {
        this.litpic = litpic;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setHpic(String hpic) {
        this.hpic = hpic;
    }

    public String getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public String getLitpic() {
        return litpic;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getFloor() {
        return floor;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHpic() {
        return hpic;
    }
}
