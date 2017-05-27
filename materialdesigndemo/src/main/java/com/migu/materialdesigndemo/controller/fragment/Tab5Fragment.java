package com.migu.materialdesigndemo.controller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import com.migu.materialdesigndemo.adapter.MyRecycleView;
import com.migu.materialdesigndemo.view.EndlessRecyclerOnScrollListener;
import com.migu.materialdesigndemo.view.itemdecoration.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/5/18.
 */

public class Tab5Fragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * 预加载标志，默认值为false，设置为true，表示已经预加载完成，可以加载数据
     */
    private boolean isPrepared=false;
    private View view;
    private MyRecycleView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> datas;
    private ItemRecycleViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private int count=0;
    private boolean isLoading;
    private Handler handler=new Handler();
    private boolean isShouDong;
    private int pull;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.e("Tab5Fragment","===onCreateView===");
        if (view==null) {
            view = View.inflate(getActivity(), R.layout.fragment_tab5, null);
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
        recyclerView = (MyRecycleView) view.findViewById(R.id.rv);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl);
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
//        for (int i = 0; i <1 ; i++) {
//            datas.add("我是第"+i+"个item");
//        }
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ItemRecycleViewAdapter(datas,getActivity());
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(),1));
        recyclerView.setAdapter(adapter);
//        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(2000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        if (count<2) {
//                            count++;
//                            List<String> lists=new ArrayList<String>();
//                            for (int i = 0; i < 10; i++) {
//                                lists.add("加载更多数据 第"+i+"个item");
//                            }
//                            datas.addAll(lists);
//                            adapter.notifyDataSetChanged();
//                        }else{
//                            Toast.makeText(getActivity(),"没有数据了",0).show();
//                        }
//                    }
//                });
//
//            }
//        });


//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState){
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
//                Log.e("123","dy===>"+dy+";lastVisibleItemPosition===>"+lastVisibleItemPosition);
//                if ((lastVisibleItemPosition+1)==adapter.getItemCount()&&dy>0){
////                    adapter.setHintFooter(false);
////                    adapter.notifyDataSetChanged();
//                    if (!isLoading){
//                        isLoading=true;
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (count<3) {
//                                    count++;
//                                    List<String> lists=new ArrayList<String>();
//                                    for (int i = 0; i < 2; i++) {
//                                        lists.add("加载更多数据 第"+i+"个item");
//                                    }
//                                    datas.addAll(lists);
//
//                                }else{
//                                    Toast.makeText(getActivity(),"没有数据了",0).show();
//                                    adapter.setHintFooter(true);
//                                }
//                                isLoading=false;
//                                adapter.notifyDataSetChanged();
//                            }
//                        },2000);
//                    }
//                }
//            }
//        });


        recyclerView.setOnLoadMoreListener(new MyRecycleView.OnLoadMoreListener() {
            @Override
            public void onLoadMore(RecyclerView recyclerView) {
                adapter.setHintFooter(false);
                adapter.notifyDataSetChanged();
                if (!isLoading){
                    isLoading=true;
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
                                adapter.setHintFooter(true);
                            }
                            isLoading=false;
                            adapter.notifyDataSetChanged();
                        }
                    },2000);
                }
            }
        });
        adapter.setOnItemClickListener(new ItemRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(getActivity(),"点击了第"+position+"个cardItem",0).show();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tab5Fragment","===onCreate===");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("Tab5Fragment","===onAttach===");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Tab5Fragment","===onActivityCreated===");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("Tab5Fragment","===onResume===");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("Tab5Fragment","===onStart===");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("Tab5Fragment","===onPause===");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("Tab5Fragment","===onStop===");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tab5Fragment","===onDestroy===");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("Tab5Fragment","===onDestroyView===");
        if (null != view) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("Tab5Fragment","===onDetach===");
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pull++;
                datas.add(0,"添加第"+pull+"个下拉刷新的数据");
                swipeRefreshLayout.setRefreshing(false);
                adapter.notifyDataSetChanged();
            }
        },2000);
    }
}
