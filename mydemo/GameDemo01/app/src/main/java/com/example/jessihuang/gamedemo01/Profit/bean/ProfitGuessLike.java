package com.example.jessihuang.gamedemo01.Profit.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/16.14:48
 */
public class ProfitGuessLike {

    /**
     * id : 779
     * name : 火影忍者
     * icon : http://i3.72g.com/upload/201601/201601120954055269.jpg
     * count_dl : 490
     */

    private String id;
    private String name;
    private String icon;
    private String count_dl;

    public static ProfitGuessLike objectFromData(String str) {

        return new Gson().fromJson(str, ProfitGuessLike.class);
    }

    public static ProfitGuessLike objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ProfitGuessLike.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ProfitGuessLike> arrayProfitGuessLikeFromData(String str) {

        Type listType = new TypeToken<ArrayList<ProfitGuessLike>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ProfitGuessLike> arrayProfitGuessLikeFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ProfitGuessLike>>() {
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProfitGuessLike{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", count_dl='" + count_dl + '\'' +
                '}';
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCount_dl(String count_dl) {
        this.count_dl = count_dl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getCount_dl() {
        return count_dl;
    }
}
