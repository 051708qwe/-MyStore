package com.example.jessihuang.gamedemo01.Gift.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Gift.bean.Gift;
import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;
import com.example.jessihuang.gamedemo01.R;

import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.19:39
 */
public class GiftListAdapter extends BaseAdapter {
    private List<Gift> list;
    private LayoutInflater inflater;
    private  Context context;
    private ImageLoader imageLoader;


    public GiftListAdapter(Context context,List<Gift> list) {
        this.list = list;
        this.context=context;
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GiftItem item=null;
        if(convertView==null){
            item=new GiftItem();

            convertView=inflater.inflate(R.layout.adapter_gift_list, null);
            item.ivHeader= (ImageView) convertView.findViewById(R.id.adapter_gift_header_iv);
            item.tvName= (TextView) convertView.findViewById(R.id.adapter_gift_name_tv);
            item.tvRemain= (TextView) convertView.findViewById(R.id.adapter_gift_remain_tv);
            item.tvContent= (TextView) convertView.findViewById(R.id.adapter_gift_content_tv);
            item.button1=(Button)convertView.findViewById(R.id.adapter_gift_btn1);
            item.button2=(Button)convertView.findViewById(R.id.adapter_gift_btn2);
            convertView.setTag(item);
        }else{
            item=(GiftItem)convertView.getTag();
        }
        Gift gift=list.get(position);
        //用占位符和值连接成字符串
        String string = context.getString(R.string.gift_list_remain, gift.getRemain());
        //创建一个可加工的字符串
        SpannableString spannableString=new SpannableString(string);
        ForegroundColorSpan span=new ForegroundColorSpan(context.getResources().getColor(R.color.YELLOW_BUTTON_COLOR));
        spannableString.setSpan(span,3,string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        item.tvName.setText(gift.getName());
        //把加工好的字符串设置出去
        item.tvRemain.setText(spannableString);
        item.tvContent.setText(gift.getContent());
        //如果剩余0显示淘号，否则显示免费领取
        if(gift.getRemain()==0){
            item.button1.setVisibility(View.VISIBLE);
            item.button2.setVisibility(View.GONE);


        }
        else{
            item.button2.setVisibility(View.VISIBLE);
            item.button1.setVisibility(View.GONE);
        }
        imageLoader.DisplayImage(gift.getIcon(),item.ivHeader);

        return convertView;
    }
     class  GiftItem{
         ImageView ivHeader;
         TextView tvName,tvRemain,tvContent;
         Button button1,button2;

    }

}
