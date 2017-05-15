package com.dyy.interfacedemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FinishAppInterface{
    InterfaceFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                if (fragment==null){
                    fragment=new InterfaceFragment();
                }
                ft.replace(R.id.fl,fragment).commit();
            }
        });
    }

    @Override
    public void finishApp() {
        finish();
    }
}
