package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.receiver.ActivityMusicReceiver;
import com.example.service.MusicService;

/**
 * Created by DY on 2017/4/10.
 */

public class MusicActivity extends Activity {
    public final static String CTL_ACTION="com.example.receiver.CTL_ACTION";
    public final static String UPDATE_ACTION="com.example.receiver.UPDATE_ACTION";
    private String[] titles={"心愿","约定","美丽新世界"};
    private String[] authors={"未知艺术家","周慧","伍佰"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        TextView tv_title= (TextView) findViewById(R.id.tv_title);
        TextView tv_author= (TextView) findViewById(R.id.tv_author);
        Button btn_play= (Button) findViewById(R.id.btn_play);
        Button btn_stop= (Button) findViewById(R.id.btn_stop);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(UPDATE_ACTION);
        ActivityMusicReceiver receiver=new ActivityMusicReceiver(titles,authors,tv_title,tv_author,btn_play);
        registerReceiver(receiver,intentFilter);
        Intent intent=new Intent(this, MusicService.class);
        startService(intent);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CTL_ACTION);
                intent1.putExtra("control",1);
                sendBroadcast(intent1);
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CTL_ACTION);
                intent1.putExtra("control",2);
                sendBroadcast(intent1);
            }
        });
    }
}
