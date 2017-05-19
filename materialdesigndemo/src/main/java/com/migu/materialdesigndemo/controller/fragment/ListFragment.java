package com.migu.materialdesigndemo.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.migu.materialdesigndemo.R;
import com.migu.materialdesigndemo.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/5/18.
 */

public class ListFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;
    private String[] titles={"Tab 1","Tab 2","Tab 3"};
    private List<Fragment> fragments;

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
        fragments.add(tabOneFragment);
        fragments.add(tabTwoFragment);
        fragments.add(tabThreeFragment);
        TabAdapter adapter=new TabAdapter(getActivity().getSupportFragmentManager(),fragments,titles);
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
    }

    private void initTableLayout() {
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initFindViewById() {
        tabLayout = (TabLayout) view.findViewById(R.id.tl);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
    }
}
