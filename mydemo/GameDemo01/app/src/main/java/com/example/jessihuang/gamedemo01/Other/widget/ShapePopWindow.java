package com.example.jessihuang.gamedemo01.Other.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.jessihuang.gamedemo01.R;

/**
 * 分享菜单
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.10:05
 */
public class ShapePopWindow extends PopupWindow {
    private Button btn;
    public ShapePopWindow(Context context) {
        super(context);
        /*设置布局*/
        View content= LayoutInflater.from(context).inflate(R.layout.pop_share,null);
        setContentView(content);
        /*设置宽高，不设置不会显示*/
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        /*设置进出动画*/
        setAnimationStyle(R.style.share_pop);
        btn= (Button) content.findViewById(R.id.shaer_cancel_btn);
        /*在pop之外区域点击 pop消失*/
        setFocusable(true);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
