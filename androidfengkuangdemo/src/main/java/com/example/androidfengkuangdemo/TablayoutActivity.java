package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/3/20.
 */

public class TablayoutActivity extends Activity {
    private List<String> tabs=new ArrayList<String>();
    private TabLayout tb;
    private myAdapter adapter;
    private int height;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ViewPager vp= (ViewPager) findViewById(R.id.vp);
        tb = (TabLayout) findViewById(R.id.tb);
        intdata();
        adapter = new myAdapter();
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);
        view = adapter.getTabView(0);
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height =view.getMeasuredHeight();
        Log.e("123","子控件高度we===》"+ height);
        ViewGroup.LayoutParams layoutParams=tb.getLayoutParams();
                layoutParams.height= height;
                layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
                tb.setLayoutParams(layoutParams);
//        ViewTreeObserver vto= tb.getViewTreeObserver();
//        Log.e("123","子控件高度we===》"+ tb.getHeight());
//        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                Log.e("123","子控件高度 onPreDraw===》");
//                tb.getViewTreeObserver().removeOnPreDrawListener(this);
//                height = tb.getHeight();
//                Log.e("123","子控件高度===》"+ height);
////                ViewGroup.LayoutParams layoutParams=tb.getLayoutParams();
////                layoutParams.height= height;
////                layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
////                tb.setLayoutParams(layoutParams);
//                return true;
//            }
//        });

        for (int i = 0; i< adapter.getCount(); i++){
            TabLayout.Tab tab= tb.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }
    public class myAdapter extends PagerAdapter{

        private TextView tv;
        public View getTabView(int position){
            View view= LayoutInflater.from(TablayoutActivity.this).inflate(R.layout.tablayout_item,null,false);
            TextView tab_tv= (TextView) view.findViewById(R.id.tab_tv);
            tab_tv.setText(tabs.get(position));
            return view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            tv = new TextView(TablayoutActivity.this);
            tv.setText(tabs.get(position));
            tv.setTextSize(48);
            container.addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(tv);
        }
    }
    public void intdata(){
        tabs.add("热门推荐");
        tabs.add("热门收藏");
        tabs.add("今日热榜");
        tabs.add("本月热榜");
    }
}
