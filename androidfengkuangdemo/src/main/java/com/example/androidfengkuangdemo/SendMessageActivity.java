package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

/**
 * Created by DY on 2017/4/6.
 */

public class SendMessageActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        final EditText et_to_num= (EditText) findViewById(R.id.et_to_num);
        final EditText et_content= (EditText) findViewById(R.id.et_content);
        final SmsManager manager=SmsManager.getDefault();
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pendingIntent=PendingIntent.getActivity(SendMessageActivity.this,0,new Intent(),0);
                manager.sendTextMessage(et_to_num.getText().toString(),null,et_content.getText().toString()
                ,pendingIntent,null);
            }
        });
    }
}
