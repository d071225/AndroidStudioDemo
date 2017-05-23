package com.migu.materialdesigndemo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.migu.materialdesigndemo.R;

/**
 * Created by DY on 2017/5/18.
 */

public class Tab4Fragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared=true;
    private boolean isClick;

    private View view;
    private ImageView iv;
    private int startX;
    private int startY;
    private int widthPixels;
    private int heightPixels;
    private long startTime;
    private long endTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("Tab4Fragment","===onCreateView===");
            view = View.inflate(getActivity(), R.layout.fragment_tab4, null);
            initData();
        return view;
    }

    private void initData() {
        getScreenSize();
        initFindViewById();
        initListener();
    }

    private void initListener() {
        iv.setOnClickListener(this);
        iv.setOnTouchListener(this);
    }

    private void initFindViewById() {
        iv = (ImageView) view.findViewById(R.id.iv);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tab4Fragment","===onCreate===");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Tab4Fragment","===onAttach===");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Tab4Fragment","===onActivityCreated===");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Tab4Fragment","===onResume===");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tab4Fragment","===onStart===");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Tab4Fragment","===onPause===");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Tab4Fragment","===onStop===");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tab4Fragment","===onDestroy===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Tab4Fragment","===onDestroyView===");
//        if (null != view) {
//            ((ViewGroup) view.getParent()).removeView(view);
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tab4Fragment","===onDetach===");
    }



    @Override
    public void onClick(View v) {
        iv.setFocusable(true);
        Toast.makeText(getContext(),"点击了图片",0).show();
        Log.e("123","点击了图片");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                isClick=false;
                startTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_MOVE:
                isClick=true;
                int dx= (int) (event.getRawX()-startX);
                int dy= (int) (event.getRawY()-startY);
                int left=iv.getLeft()+dx;
                int right=iv.getRight()+dx;
                int top =iv.getTop()+dy;
                int bottom=iv.getBottom()+dy;
                if (left<0) {
                    left = 0;
                    right = iv.getWidth();
                }
                if (left>widthPixels){
                    right=widthPixels;
                    left=right-iv.getWidth();
                }
                if (bottom>heightPixels){
                    bottom=heightPixels;
                    top=bottom-iv.getHeight();
                }
                if (bottom<0){
                    bottom=0;
                    top=iv.getHeight();
                }
                iv.layout(left,top,right,bottom);
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                endTime = System.currentTimeMillis();
                Log.e("123","开始："+startTime+"结束："+endTime);
                if ((endTime-startTime)>100){
                    isClick=true;
                }else{
                    isClick=false;
                }
                break;
        }
        return isClick;
    }
    public void getScreenSize(){
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;
    }
}
