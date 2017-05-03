package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.service.MyIntentService;
import com.example.service.MyService;

/**
 * Created by DY on 2017/4/6.
 */

public class ServiceActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        findViewById(R.id.btn_start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceActivity.this, MyService.class);
                startService(intent);
            }
        });
        findViewById(R.id.btn_start_intentservice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ServiceActivity.this, MyIntentService.class);
                startService(intent);
            }
        });
    }
}
