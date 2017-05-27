package com.migu.materialdesigndemo.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by DY on 2017/5/27.
 */

public class MyRecycleView extends RecyclerView {

    private float startY;

    public MyRecycleView(Context context) {
        this(context,null);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private OnLoadMoreListener onLoadMoreListener;
    public interface OnLoadMoreListener{
        public void onLoadMore(RecyclerView recyclerView);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
            this.onLoadMoreListener=onLoadMoreListener;
    }

    private void init() {
        addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int itemCount = getAdapter().getItemCount();
                int lastVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                Log.e("123","onScrollStateChanged distanceY--->"+distanceY);
                Log.e("123","(lastVisibleItemPosition+1)==itemCount--->"+((lastVisibleItemPosition+1)==itemCount));
                if (distanceY<0&&(lastVisibleItemPosition+1)==itemCount){
                    onLoadMoreListener.onLoadMore(recyclerView);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
    float distanceY;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        float y=ev.getRawY();
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                startY = y;
////                Log.e("123","ACTION_DOWN startY--->"+startY);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                distanceY=y-startY;
////                Log.e("123","ACTION_MOVE distanceY--->"+distanceY);
//                startY=y;
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float y=e.getRawY();
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = y;
                Log.e("123","ACTION_DOWN startY--->"+startY);
                break;
            case MotionEvent.ACTION_MOVE:
                distanceY=y-startY;
                Log.e("123","ACTION_MOVE distanceY--->"+distanceY);
                startY=y;
                break;
        }
        return super.onTouchEvent(e);
    }
}
