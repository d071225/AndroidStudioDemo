package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.SubMenu;


/**
 * Created by DY on 2017/3/16.
 */

public class MenuActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu=menu.addSubMenu("字体大小");
        subMenu.setIcon(android.R.drawable.ic_media_play);
        subMenu.setHeaderIcon(android.R.drawable.ic_lock_power_off);
        subMenu.setHeaderTitle("选择字体大小");
        subMenu.add(0,1,0,"1号字");
        subMenu.add(0,2,0,"2号字");
        subMenu.add(0,3,0,"3号字");
        subMenu.add(0,4,0,"4号字");
        return super.onCreateOptionsMenu(menu);
    }
}
