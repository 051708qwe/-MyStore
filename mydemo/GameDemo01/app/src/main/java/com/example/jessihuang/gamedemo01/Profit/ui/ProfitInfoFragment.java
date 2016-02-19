package com.example.jessihuang.gamedemo01.Profit.ui;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.R;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/15.17:39
 */
public class ProfitInfoFragment extends BaseFragment {
    private TextView tvGameUb,tvGameIntrduce,tvButtom1,tvButtom2,
            tvButtom3,tvButtom4,tvButtomSize1,tvButtomSize2,tvButtomSize3,tvButtomSize4;
    private ImageView ivGuessLike1,ivGuessLike2,ivGuessLike3,ivGuessLike4;
    @Override
    protected int getLayout() {
        return R.layout.profit_check_gameinfo;
    }

    @Override
    protected void initViews() {
        tvGameUb=(TextView)root.findViewById(R.id.profit_ub_prize);
        tvGameIntrduce=(TextView)root.findViewById(R.id.profit_intrduce_text);
        tvButtom1=(TextView)root.findViewById(R.id.profit_buttom_game_name_1);
        tvButtom2=(TextView)root.findViewById(R.id.profit_buttom_game_name_2);
        tvButtom3=(TextView)root.findViewById(R.id.profit_buttom_game_name_3);
        tvButtom4=(TextView)root.findViewById(R.id.profit_buttom_game_name_4);
        tvButtomSize1=(TextView)root.findViewById(R.id.profit_buttom_download_time1);
        tvButtomSize2=(TextView)root.findViewById(R.id.profit_buttom_download_time2);
        tvButtomSize3=(TextView)root.findViewById(R.id.profit_buttom_download_time3);
        tvButtomSize4=(TextView)root.findViewById(R.id.profit_buttom_download_time4);
        ivGuessLike1=(ImageView)root.findViewById(R.id.profit_guess_like_icon1);
        ivGuessLike2=(ImageView)root.findViewById(R.id.profit_guess_like_icon2);
        ivGuessLike3=(ImageView)root.findViewById(R.id.profit_guess_like_icon3);
        ivGuessLike4=(ImageView)root.findViewById(R.id.profit_guess_like_icon4);

    }

    @Override
    protected void iniEvents() {

    }

    @Override
    protected void initData() {

    }
}
