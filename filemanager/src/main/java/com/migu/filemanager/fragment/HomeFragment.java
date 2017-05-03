package com.migu.filemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.migu.filemanager.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by DY on 2017/4/25.
 */

public class HomeFragment extends MPermissionsFragment {

    private GridView gv;
    private int[] girdview_menu_image = {R.drawable.menu_phone,R.drawable.menu_sdcard,R.drawable.menu_search,
            R.drawable.menu_create,R.drawable.menu_palse,R.drawable.menu_exit};
    private String[] gridview_menu_title = {"手机","SD卡","搜索","创建","粘贴","退出"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=View.inflate(getActivity(), R.layout.fragment_demo,null);
        initGridViewMenu(view);
        return view;
    }

    private void initGridViewMenu(View view) {
        gv = (GridView) view.findViewById(R.id.gv);
        gv.setSelector(R.drawable.menu_item_selected);
        gv.setBackgroundResource(R.drawable.menu_background);
        gv.setNumColumns(6);
        gv.setHorizontalSpacing(10);
        gv.setVerticalSpacing(10);
        ArrayList<HashMap<String,Object>> datas=new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <girdview_menu_image.length ; i++) {
            HashMap<String,Object> map=new HashMap<String, Object>();
            map.put("image",girdview_menu_image[i]);
            map.put("title",gridview_menu_title[i]);
            datas.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),datas,R.layout.grideview_item,new String[]{"image","title"},new int[]{R.id.iv_item,R.id.tv_item});
        gv.setAdapter(adapter);
    }

}
