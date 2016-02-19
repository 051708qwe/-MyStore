package com.example.jessihuang.gamedemo01.Profit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.jessihuang.gamedemo01.Other.Utils.ImageLoader;
import com.example.jessihuang.gamedemo01.Other.Utils.LogUtil;
import com.example.jessihuang.gamedemo01.Profit.bean.Profit;
import com.example.jessihuang.gamedemo01.R;

import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/14.15:49
 */
public class ProfitListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Profit> list;
    private Context context;
    private ImageLoader imageLoader;


    public ProfitListAdapter(List<Profit> list, Context context) {
        this.list = list;
        this.context = context;
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
        ProfitView view=null;
        /*判断复用*/
        if(convertView==null){
            view=new ProfitView();
             /*初始化控件，绑定布局*/
            convertView=inflater.inflate(R.layout.profit_listview_layout,null);
            view.iconImg=(ImageView)convertView.findViewById(R.id.profit_listview_icon);
            view.tvName=(TextView)convertView.findViewById(R.id.profit_info_game_name);
            view.ratingBar=(RatingBar)convertView.findViewById(R.id.profit_ratingbar);
            view.tvLoadTime=(TextView)convertView.findViewById(R.id.profit_info_download_time);
            view.tvSize=(TextView)convertView.findViewById(R.id.profit_info_game_size);
            view.tvUbPrize=(TextView)convertView.findViewById(R.id.profit_Ub_prize);
            convertView.setTag(view);
        }else{
            view=(ProfitView)convertView.getTag();
        }
        /*获取解析结果并设置视图*/
        Profit profit = list.get(position);
        String gameName=context.getString(R.string.profit_list_name, profit.getName());
        view.tvName.setText(gameName);
        String score=profit.getScore();
        float progress=Float.parseFloat(score);
        view.ratingBar.setProgress((int) progress);
        String loadTime=context.getString(R.string.profit_list_download_time,profit.getCount_dl());
        view.tvLoadTime.setText(loadTime);
        String size=context.getString(R.string.profit_list_game_size,profit.getSize());
        view.tvSize.setText(size);
        String UbSize=context.getString(R.string.profit_list_ub_prize,profit.getAll_prize());
        view.tvUbPrize.setText(UbSize);
        LogUtil.w("---------)))",gameName+" ");
        imageLoader.DisplayImage(profit.getIcon(),view.iconImg);
        return convertView;
    }
    class ProfitView{
        public ImageView iconImg;
        public TextView tvName,tvLoadTime,tvSize,tvUbPrize;
        public RatingBar ratingBar;
    }
}
