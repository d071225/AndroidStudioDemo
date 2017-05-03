package com.migu.filemanager;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import com.migu.filemanager.fragment.DashBoardFragment;
import com.migu.filemanager.fragment.HomeFragment;
import com.migu.filemanager.fragment.NoticeFragment;

public class MainActivity extends MPermissionsActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    initFragment(1);
                    return true;
                case R.id.navigation_dashboard:
                    initFragment(2);
                    return true;
                case R.id.navigation_notifications:
                    initFragment(3);
                    return true;
            }
            return false;
        }

    };
    private FragmentTransaction fragmentTransaction;
    private HomeFragment homeFragment;
    private android.app.FragmentManager fragmentManager;
    private DashBoardFragment dashBoardFragment;
    private NoticeFragment noticeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager = getFragmentManager();

        initFragment(1);
    }

    private void initFragment(int position) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragments(fragmentTransaction);
        switch (position){
            case 1:
                if (homeFragment==null){
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content,homeFragment);
                }else{
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 2:
                if (dashBoardFragment==null){
                    dashBoardFragment = new DashBoardFragment();
                    fragmentTransaction.add(R.id.content,dashBoardFragment);
                }else{
                    fragmentTransaction.show(dashBoardFragment);
                }
                break;
            case 3:
                if (noticeFragment==null){
                    noticeFragment = new NoticeFragment();
                    fragmentTransaction.add(R.id.content,noticeFragment);
                }else{
                    fragmentTransaction.show(noticeFragment);
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
        if (dashBoardFragment!=null){
            fragmentTransaction.hide(dashBoardFragment);
        }
        if (noticeFragment!=null){
            fragmentTransaction.hide(noticeFragment);
        }
    }

    @Override
    public void permissionSuccess(int requestCode) {
        super.permissionSuccess(requestCode);

    }
}
