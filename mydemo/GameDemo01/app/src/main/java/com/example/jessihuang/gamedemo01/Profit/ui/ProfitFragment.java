package com.example.jessihuang.gamedemo01.Profit.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.Other.Utils.Contants;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.widget.LoadMoreListView;
import com.example.jessihuang.gamedemo01.Profit.Util.ProfitUtil;
import com.example.jessihuang.gamedemo01.Profit.adapter.ProfitListAdapter;
import com.example.jessihuang.gamedemo01.Profit.bean.Profit;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 赚钱页面
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.10:36
 */
public class ProfitFragment extends BaseFragment {
    private LoadMoreListView listViewMore;
    private ListView listView;
    private Button btn1,btn2,btn3;
    private List<Profit> list;
    private int page=1;
    private   ProfitListAdapter profitListAdapter;


    @Override
    protected int getLayout() {

        return R.layout.fragment_profit;
    }

    @Override
    protected void initViews() {

        listView=(ListView)root.findViewById(R.id.profit_listview);
        btn1=(Button)root.findViewById(R.id.profit_check_getPoint);
        btn2=(Button)root.findViewById(R.id.profit_check_task);
        btn3=(Button)root.findViewById(R.id.profit_check_score);
        listViewMore= (LoadMoreListView) listView;




    }

    @Override
    protected void iniEvents() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Profit profit = list.get(position);
                String profitId = profit.getId();
                Intent it=new Intent(getActivity(),ProfitInfoActivity.class);
                it.putExtra("id",profitId);
                getActivity().startActivity(it);

            }
        });
            
    }

    @Override
    protected void initData() {
        loadMore();

    }

    private void loadMore() {
        listViewMore.setLoadMore(new LoadMoreListView.loadMore() {
            @Override
            public void onLoadMore() {
                page++;
                loadMore();
            }
        });
        ProfitUtil.requestProfitList( page,new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                try {
                    if (result == null) {
                        return;
                    }
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString(Contants.FLAR_REQUEST_STATE);
                    if (Contants.SUCCESS_STATE.equals(state)) {
                        JSONArray info = jsonObject.getJSONArray("info");
                        list = Profit.arrayProfitFromData(info.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                profitListAdapter = new ProfitListAdapter(list, getActivity());
                                listView.setAdapter(profitListAdapter);
                            }
                        });
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
}
