package com.example.jessihuang.gamedemo01.Profit.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;
import com.example.jessihuang.gamedemo01.Other.Utils.LogUtil;
import com.example.jessihuang.gamedemo01.Profit.Util.ProfitUtil;
import com.example.jessihuang.gamedemo01.Profit.bean.ProfitInfo;
import com.example.jessihuang.gamedemo01.Profit.ui.ProfitInfoActivity;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.content.Intent.getIntent;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/16.13:47
 */
public class GameInfoFragment extends BaseFragment implements View.OnClickListener{
    private TextView ub_plus,intrduce_game;
    private Button charge_do;
    private String id;
    private TextView[] gameName,gameDownLoadTime;
    private ImageView[] gameIcon;
    private ImageView gameIcon1,gameIcon2,gameIcon3,gameIcon4;
    private TextView gameName1,gameName2,gameName3,gameName4,gameTime1,gameTime2,gameTime3,gameTime4;
    private ImageLoader imageLoader;
    private Intent it;
    private  Bundle b;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gameinfo;
    }

    @Override
    protected void initViews() {
        ub_plus=(TextView)root.findViewById(R.id.profit_ub_prize);
        charge_do=(Button)root.findViewById(R.id.profit_charge_do);
        intrduce_game=(TextView)root.findViewById(R.id.profit_intrduce_text);
        gameIcon1=(ImageView)root.findViewById(R.id.profit_guess_like_icon1);
        gameIcon2=(ImageView)root.findViewById(R.id.profit_guess_like_icon2);
        gameIcon3=(ImageView)root.findViewById(R.id.profit_guess_like_icon3);
        gameIcon4=(ImageView)root.findViewById(R.id.profit_guess_like_icon4);
        gameName1=(TextView)root.findViewById(R.id.profit_buttom_game_name_1);
        gameName2=(TextView)root.findViewById(R.id.profit_buttom_game_name_2);
        gameName3=(TextView)root.findViewById(R.id.profit_buttom_game_name_3);
        gameName4=(TextView)root.findViewById(R.id.profit_buttom_game_name_4);
        gameTime1=(TextView)root.findViewById(R.id.profit_buttom_download_time1);
        gameTime2=(TextView)root.findViewById(R.id.profit_buttom_download_time2);
        gameTime3=(TextView)root.findViewById(R.id.profit_buttom_download_time3);
        gameTime4=(TextView)root.findViewById(R.id.profit_buttom_download_time4);
        gameIcon=new ImageView[]{gameIcon1,gameIcon2,gameIcon3,gameIcon4};
        gameName=new TextView[]{gameName1,gameName2,gameName3,gameName4};
        gameDownLoadTime=new TextView[]{gameTime1, gameTime2, gameTime3, gameTime4};





    }

    @Override
    protected void iniEvents() {
        gameIcon1.setOnClickListener(this);
        gameIcon2.setOnClickListener(this);
        gameIcon3.setOnClickListener(this);
        gameIcon4.setOnClickListener(this);



    }

    @Override
    protected void initData() {
        //接受上一个界面传过来的id用于下载游戏详情的数据
        Intent intent=getActivity().getIntent();
        Bundle bundle=intent.getExtras();
         id=bundle.getString("id");
        ProfitUtil.requestGameInfo(id, new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                    if (Contants.SUCCESS_STATE.equals(state)) {
                        //解析
                        JSONObject info = jsonObject.getJSONObject("info");
                        ProfitInfo profitInfo = ProfitInfo.objectFromData(info.toString());
                        String ub_plus_num = getString(R.string.profit_ub_plus, profitInfo.getDl_back_jifen());
                        ub_plus.setText(ub_plus_num);
                        String game_task_state = profitInfo.getGame_task_state();
                        if (game_task_state.equals("0")) {
                            charge_do.setText(R.string.profit_not_done);
                        } else {
                            charge_do.setText(R.string.profit_done);
                        }
                        String game_desc = profitInfo.getGame_desc();
                        Spanned s = Html.fromHtml(game_desc);
                        intrduce_game.setText("        " + s);
                        //加载猜你喜欢的数据
                        initGuessLike();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {

            }
        });



    }

    private void initGuessLike()  {
        ProfitUtil.requestGuessLike(id,new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                    if (Contants.SUCCESS_STATE.equals(state)) {
                        //解析
                        JSONArray info = jsonObject.getJSONArray("info");
                        List<ProfitInfo> profitInfos = ProfitInfo.arrayProfitInfoFromData(info.toString());
                        for (int i = 0; i <profitInfos.size() ; i++) {
                            imageLoader=new ImageLoader(getActivity());
                            imageLoader.DisplayImage(profitInfos.get(i).getIcon(), gameIcon[i]);
                            String gameNames=profitInfos.get(i).getName();
                            gameName[i].setText(gameNames);
                            String laodTime=getString(R.string.profit_list_download_time,profitInfos.get(i).getCount_dl());
                            gameDownLoadTime[i].setText(laodTime);
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {


            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profit_guess_like_icon1:
                it=new Intent(getActivity(), ProfitInfoActivity.class);
                 b=new Bundle();
                b.putString("id","779");
                it.putExtras(b);
                startActivity(it);
            case R.id.profit_guess_like_icon2:
                it=new Intent(getActivity(), ProfitInfoActivity.class);
                 b=new Bundle();
                b.putString("id", "780");
                it.putExtras(b);
                startActivity(it);
            case R.id.profit_guess_like_icon3:
                it=new Intent(getActivity(), ProfitInfoActivity.class);
                b=new Bundle();
                b.putString("id", "777");
                it.putExtras(b);
                startActivity(it);
            case R.id.profit_guess_like_icon4:
                it=new Intent(getActivity(), ProfitInfoActivity.class);
                b=new Bundle();
                b.putString("id", "194");
                it.putExtras(b);
                startActivity(it);

        }
    }
}
