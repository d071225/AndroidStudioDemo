package com.dyy.mvcdemo.controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Daiyy on 2017/5/15.
 */

public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv=new TextView(this);
        tv.setText("登录成功页面");
        setContentView(tv);
    }
}
