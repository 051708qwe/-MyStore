package com.example.jessihuang.gamedemo01.Gift.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jessihuang.gamedemo01.Gift.Util.GiftUtil;
import com.example.jessihuang.gamedemo01.Gift.bean.Gift;
import com.example.jessihuang.gamedemo01.Gift.adapter.GiftListAdapter;
import com.example.jessihuang.gamedemo01.Other.Ui.BaseFragment;
import com.example.jessihuang.gamedemo01.Other.Utils.DownLoadTask;
import com.example.jessihuang.gamedemo01.Other.widget.LoadMoreListView;
import com.example.jessihuang.gamedemo01.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/12.14:59
 */
public class GiftTypeFragment extends BaseFragment {
    private LoadMoreListView listView;
    private String json;
    private  int type;
    private  List<Gift> list;
    private  float lastY;
    private  int page=1;
    private  GiftListAdapter adapter;


    public GiftTypeFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gift_type;
    }

    @Override
    protected void initViews() {
        listView= (LoadMoreListView) root;

    }

    @Override
    protected void iniEvents() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Gift gift = list.get(position);
                String giftId = gift.getId();
                Intent it = new Intent(getActivity(), GiftInfoActivity.class);
                it.putExtra("id", giftId);
                getActivity().startActivity(it);
            }
        });
    }

    @Override
    protected void initData() {

        loadList();


    }

    private void loadList() {
        listView.setLoadMore(new LoadMoreListView.loadMore() {
            @Override
            public void onLoadMore() {
                page++;
                loadList();
            }
        });
        GiftUtil.requestGiftList(page, type, new DownLoadTask.RequestCallBack() {
            @Override
            public void success(Object result) {
                try {
                    if (result == null) {
                        return;
                    }
                    JSONObject jsonData = new JSONObject(result.toString());
                    String state = jsonData.getString("state");
                    if (state.equals("success")) {
                        //如果解析的状态成功
                        JSONArray info = jsonData.getJSONArray("info");
                        list = Gift.arrayGiftFromData(info.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new GiftListAdapter(getActivity(), list);
                                listView.setAdapter(adapter);

                            }
                        });


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void error(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
