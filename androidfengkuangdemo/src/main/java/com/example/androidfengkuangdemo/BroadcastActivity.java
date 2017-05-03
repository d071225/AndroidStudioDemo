package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import static com.example.androidfengkuangdemo.R.id.send;

/**
 * Created by DY on 2017/4/10.
 */

public class BroadcastActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        findViewById(send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.example.receiver.MyReceiver");
                intent.putExtra("msg","普通广播测试");
                sendBroadcast(intent);
            }
        });
        findViewById(R.id.send_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction("com.example.receiver.MyReceiver");
                intent.putExtra("msg","有序广播测试");
                sendOrderedBroadcast(intent,null);
            }
        });

    }
}
