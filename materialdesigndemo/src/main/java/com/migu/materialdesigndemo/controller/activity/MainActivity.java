package com.migu.materialdesigndemo.controller.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.migu.materialdesigndemo.R;
import com.migu.materialdesigndemo.controller.fragment.HomeFragment;
import com.migu.materialdesigndemo.controller.fragment.ListFragment;
import com.migu.materialdesigndemo.controller.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ListFragment listFragment;
    private SettingFragment settingFragment;
    private DrawerLayout drawerLayout;
    private TextView tv_head;
    private View inflateHeaderView;
    private Button btn_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        selectFragment(1);
        initFindViewById();
        initListener();
    }
    public void initFindViewById(){
        navigationView = (NavigationView) findViewById(R.id.nv);
        inflateHeaderView = navigationView.inflateHeaderView(R.layout.menu_header);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl);
        tv_head = (TextView) inflateHeaderView.findViewById(R.id.tv_head);
        btn_head = (Button) inflateHeaderView.findViewById(R.id.btn_head);
    }
    public void initListener(){
        navigationView.setNavigationItemSelectedListener(this);
        tv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了头部textView",Toast.LENGTH_SHORT).show();
            }
        });
        btn_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了头部Button",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_home:
                selectFragment(1);
                Toast.makeText(MainActivity.this,"menu_home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_list:
                selectFragment(2);
                Toast.makeText(MainActivity.this,"menu_list",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_setting:
                selectFragment(3);
                Toast.makeText(MainActivity.this,"menu_setting",Toast.LENGTH_SHORT).show();
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(Gravity.LEFT);
        return false;
    }

    public void selectFragment(int type){
        ft = fragmentManager.beginTransaction();
        hideFragment();
        switch (type){
            case 1:
                    if(homeFragment==null){
                        homeFragment = new HomeFragment();
                        ft.add(R.id.fl,homeFragment);
                    }
                    ft.show(homeFragment);
                break;
            case 2:
                    if(listFragment==null){
                        listFragment = new ListFragment();
                        ft.add(R.id.fl,listFragment);
                    }
                ft.show(listFragment);
                break;
            case 3:
                    if(settingFragment==null){
                        settingFragment = new SettingFragment();
                        ft.add(R.id.fl,settingFragment);
                    }
                ft.show(settingFragment);
                break;
        }
        ft.commitAllowingStateLoss();
    }
    public void hideFragment(){
        if (homeFragment!=null){
            ft.hide(homeFragment);
        }
        if (listFragment!=null){
            ft.hide(listFragment);
        }
        if (settingFragment!=null){
            ft.hide(settingFragment);
        }
    }
}
