package com.dyy.interfacedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Daiyy on 2017/5/9.
 */

public class SecendActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv=new TextView(this);
        tv.setText("登录页");
        setContentView(tv);
    }
}
