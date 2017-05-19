package com.migu.materialdesigndemo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by DY on 2017/5/19.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titls;
    public TabAdapter(FragmentManager fm,List<Fragment> fragments,String[] titls) {
        super(fm);
        this.fragments=fragments;
        this.titls=titls;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titls[position];
    }


}
