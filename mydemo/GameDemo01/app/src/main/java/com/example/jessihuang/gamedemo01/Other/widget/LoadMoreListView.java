package com.example.jessihuang.gamedemo01.Other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * @Author is Jessi.Huang
 * @Time is 2016/1/15.11:09
 */
public class LoadMoreListView extends ListView {
    private float lastY;
    private  loadMore loadMore;
    private float firstY;
    public LoadMoreListView(Context context) {
        super(context);
        initScrollListener();
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScrollListener();
    }

    private  void initScrollListener(){
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState != SCROLL_STATE_IDLE) {
                    //如果状态不是停止的不做其他操作
                    return;
                }
                //获取listview中Item总个数
                int count = view.getCount();
                int firstVisiblePosition = view.getFirstVisiblePosition();
                int lastVisiblePosition = view.getLastVisiblePosition();
                int childCount = view.getChildCount();
                /*如果当前可见的最后一项的position等于最后一个Item的Position表示显示的是最后一项*/
                if (lastVisiblePosition == count - 1) {
                    View lastChild = view.getChildAt(childCount - 1);
                    float y = lastChild.getY();
                    /*如果第一次随意停下来的位置和第二次随意停下来的位置相同，表示已经滑到底了，为滑到底的规则*/
                    if (lastY == y) {
                        if (loadMore != null) {
                            loadMore.onLoadMore();
                        }

                    } else {
                        lastY = y;
                    }
                }
                if(firstVisiblePosition==count-1){
                    View firstChid=view.getChildAt(childCount-1);
                    float y=firstChid.getY();
                    if(firstY==y){
                        if(loadMore!=null){
                            loadMore.onLoadMore();
                        }
                    }
                    else{
                        firstY=y;
                    }

                }


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
    /*设置一个加载更多的监听*/
    public  void setLoadMore(loadMore loadMore){
        this.loadMore=loadMore;
    }
    /*加载更多的监听接口*/
    public  interface  loadMore{
        /*回调的方法*/
        void onLoadMore();
    }
}
