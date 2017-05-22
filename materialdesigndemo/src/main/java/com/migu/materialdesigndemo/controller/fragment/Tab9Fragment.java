package com.migu.materialdesigndemo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.migu.materialdesigndemo.R;

/**
 * Created by DY on 2017/5/18.
 */

public class Tab9Fragment extends Fragment {
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared=true;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("Tab9Fragment","===onCreateView===");
            view = View.inflate(getActivity(), R.layout.fragment_home, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_content);
            tv.setText("Tab9Fragment");
//        isPrepared = true;
//            setlazyLoad();
        return view;
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tab9Fragment","===onCreate===");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Tab9Fragment","===onAttach===");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Tab9Fragment","===onActivityCreated===");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Tab9Fragment","===onResume===");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tab9Fragment","===onStart===");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Tab9Fragment","===onPause===");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Tab9Fragment","===onStop===");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tab9Fragment","===onDestroy===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Tab9Fragment","===onDestroyView===");
//        if (null != view) {
//            ((ViewGroup) view.getParent()).removeView(view);
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tab9Fragment","===onDetach===");
    }
}
