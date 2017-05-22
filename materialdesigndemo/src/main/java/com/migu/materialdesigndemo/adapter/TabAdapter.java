package com.migu.materialdesigndemo.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.migu.materialdesigndemo.R;

import java.util.List;

/**
 * Created by DY on 2017/5/19.
 */

public class TabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titls;
    private Context context;
    public TabAdapter(FragmentManager fm,List<Fragment> fragments,String[] titls,Context context) {
        super(fm);
        this.fragments=fragments;
        this.titls=titls;
        this.context=context;
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
//        Drawable image = context.getResources().getDrawable(R.mipmap.ic_launcher);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" "+titls[position]);
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
        return null;
    }
    public View getTabView(int position){
        View view=View.inflate(context,R.layout.tabview_item,null);
        TextView tv= (TextView) view.findViewById(R.id.tv_tabview);
        tv.setText(titls[position]);
        return view;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
