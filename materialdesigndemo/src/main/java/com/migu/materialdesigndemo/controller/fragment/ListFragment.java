package com.migu.materialdesigndemo.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.migu.materialdesigndemo.R;
import com.migu.materialdesigndemo.adapter.TabAdapter;
import com.migu.materialdesigndemo.controller.activity.MainActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/5/18.
 */

public class ListFragment extends Fragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;
    private String[] titles={"Tab 1","Tab 2","Tab 3","Tab 4","Tab 5","Tab 6","Tab 7","Tab 8","Tab 9",
            "Tab 10","Tab 11","Tab 12"};
    private List<Fragment> fragments;
    private TabAdapter adapter;
    private Button opennv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_list,null);
        initData();
        return view;
    }

    private void initData() {
        initFindViewById();
        initViewPager();
        initTableLayout();
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        initListenser();
    }

    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        TabOneFragment tabOneFragment=new TabOneFragment();
        TabTwoFragment tabTwoFragment=new TabTwoFragment();
        TabThreeFragment tabThreeFragment=new TabThreeFragment();
        Tab4Fragment tab4Fragment=new Tab4Fragment();
        Tab5Fragment tab5Fragment=new Tab5Fragment();
        Tab6Fragment tab6Fragment=new Tab6Fragment();
        Tab7Fragment tab7Fragment=new Tab7Fragment();
        Tab8Fragment tab8Fragment=new Tab8Fragment();
        Tab9Fragment tab9Fragment=new Tab9Fragment();
        Tab10Fragment tab10Fragment=new Tab10Fragment();
        Tab11Fragment tab11Fragment=new Tab11Fragment();
        Tab12Fragment tab12Fragment=new Tab12Fragment();
        fragments.add(tabOneFragment);
        fragments.add(tabTwoFragment);
        fragments.add(tabThreeFragment);
        fragments.add(tab4Fragment);
        fragments.add(tab5Fragment);
        fragments.add(tab6Fragment);
        fragments.add(tab7Fragment);
        fragments.add(tab8Fragment);
        fragments.add(tab9Fragment);
        fragments.add(tab10Fragment);
        fragments.add(tab11Fragment);
        fragments.add(tab12Fragment);
        adapter = new TabAdapter(getActivity().getSupportFragmentManager(),fragments,titles,getContext());
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(2);
    }

    private void initListenser() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        opennv.setOnClickListener(this);
    }

    private void initTableLayout() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(getActivity(),tabLayout,10,10);
            }
        });
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

    }

    private void initFindViewById() {
        tabLayout = (TabLayout) view.findViewById(R.id.tl);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        opennv = (Button) view.findViewById(R.id.btn_opennv);
    }

    public static void setIndicator(Context context, TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout ll_tab = null;
        try {
            ll_tab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) (getDisplayMetrics(context).density * leftDip);
        int right = (int) (getDisplayMetrics(context).density * rightDip);

        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            //拿到tabView的mTextView属性
            Field mTextViewField = null;
            try {
                mTextViewField = child.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(child);

                child.setPadding(0, 0, 0, 0);
                //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.width=width;
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric;
    }

    @Override
    public void onClick(View v) {
        MainActivity mainActivity= (MainActivity) getActivity();
        mainActivity.openNavigationView();
    }
}
