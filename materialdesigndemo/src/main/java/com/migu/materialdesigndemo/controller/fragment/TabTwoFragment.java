package com.migu.materialdesigndemo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.migu.materialdesigndemo.R;

/**
 * Created by DY on 2017/5/18.
 */

public class TabTwoFragment extends BaseFragment {
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("TabTwoFragment","===onCreateView===");
        if (view==null) {
            view = View.inflate(getActivity(), R.layout.fragment_home, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_content);
            tv.setText("TabTwoFragment");
            isPrepared = true;
            setlazyLoad();
        }
        return view;
    }

    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if(!isPrepared || !isVisible) {
            return;
        }else{
            Toast.makeText(getActivity(),"TabTwoFragment 2请求数据",0).show();
            Log.e("TabTwoFragment","===请求数据===");
            isPrepared=false;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TabTwoFragment","===onCreate===");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TabTwoFragment","===onAttach===");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TabTwoFragment","===onActivityCreated===");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TabTwoFragment","===onResume===");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TabTwoFragment","===onStart===");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TabTwoFragment","===onPause===");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TabTwoFragment","===onStop===");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TabTwoFragment","===onDestroy===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TabTwoFragment","===onDestroyView===");
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TabTwoFragment","===onDetach===");
    }
}
