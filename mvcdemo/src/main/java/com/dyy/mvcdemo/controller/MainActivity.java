package com.dyy.mvcdemo.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dyy.mvcdemo.R;
import com.dyy.mvcdemo.callback.OnLoadCallBack;
import com.dyy.mvcdemo.iml.LoginModelIml;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginModelIml loginModelIml=new LoginModelIml(this);

        loginModelIml.getLoginData("18001690419", "123", new OnLoadCallBack<String>() {
            @Override
            public void onSucess(String s) {

            }

            @Override
            public void onErr() {

            }
        });
    }
}
