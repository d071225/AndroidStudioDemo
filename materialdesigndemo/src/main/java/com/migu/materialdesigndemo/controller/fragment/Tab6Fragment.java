package com.migu.materialdesigndemo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.migu.materialdesigndemo.R;
import com.migu.materialdesigndemo.adapter.ItemRecycleViewAdapter;
import com.migu.materialdesigndemo.adapter.MyRecycleViewAdapter;
import com.migu.materialdesigndemo.view.RefreshRecyclerView;
import com.migu.materialdesigndemo.view.itemdecoration.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/5/18.
 */

public class Tab6Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared=false;
    private View view;
    private RefreshRecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<String> datas;
    private LinearLayoutManager linearLayoutManager;
    private MyRecycleViewAdapter adapter;
    private Handler handler=new Handler();
    private int count=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("Tab6Fragment","===onCreateView===");
        if (view==null) {
            view = View.inflate(getActivity(), R.layout.fragment_tab6, null);
            isPrepared = true;
            initData();
            setlazyLoad();
        }
        return view;
    }

    private void initData() {
        initFindViewById();
        initListener();
    }

    private void initListener() {
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    private void initFindViewById() {
        recyclerView = (RefreshRecyclerView) view.findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_blue_light, android.R.color.holo_green_light);
    }

    @Override
    protected void setlazyLoad() {
        super.setlazyLoad();
        if(!isPrepared || !isVisible) {
            return;
        }else{
            Toast.makeText(getContext(),"Tab5Fragment请求数据",0).show();
            Log.e("TabThreeFragment","===请求数据===");
            initRecycleView();
            isPrepared =false;
        }
    }

    private void initRecycleView() {
        datas = new ArrayList<>();
        for (int i = 0; i <1 ; i++) {
            datas.add("我是第"+i+"个item");
        }
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyRecycleViewAdapter(datas,getActivity());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(),1));
        recyclerView.setAdapter(adapter);
        recyclerView.setFooterResource(R.layout.item_footer);
        recyclerView.setLoadMoreEnable(true);
        recyclerView.setOnLoadMoreListener(new RefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMoreListener(int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (count<3) {
                            count++;
                            List<String> lists=new ArrayList<String>();
                            for (int i = 0; i < 2; i++) {
                                lists.add("加载更多数据 第"+i+"个item");
                            }
                            datas.addAll(lists);

                        }else{
                            Toast.makeText(getActivity(),"没有数据了",0).show();
                        }
                        recyclerView.notifyData();
                    }
                },2000);
            }
        });
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState){
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        break;
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//
//                    }
//
//        });
        adapter.setOnItemClickListener(new MyRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(),"点击了第"+position+"个cardItem",0).show();
            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tab6Fragment","===onCreate===");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Tab6Fragment","===onAttach===");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Tab6Fragment","===onActivityCreated===");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Tab6Fragment","===onResume===");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tab6Fragment","===onStart===");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Tab6Fragment","===onPause===");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Tab6Fragment","===onStop===");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tab6Fragment","===onDestroy===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Tab6Fragment","===onDestroyView===");
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tab6Fragment","===onDetach===");
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                datas.add(0,"下拉刷新的数据");
                swipeRefreshLayout.setRefreshing(false);
                recyclerView.notifyData();
            }
        },2000);

    }
}
