package com.example.scheme;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.androidfengkuangdemo.R;

/**
 * Created by DY on 2017/3/17.
 */

public class SchemeActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        TextView scheme_tv= (TextView) findViewById(R.id.scheme_tv);
        scheme_tv.setText("仅指定scheme的activity");
    }
}
