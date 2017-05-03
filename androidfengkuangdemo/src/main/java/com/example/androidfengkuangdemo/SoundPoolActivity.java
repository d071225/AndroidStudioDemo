package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DY on 2017/4/12.
 */

public class SoundPoolActivity extends Activity {
    private Map<Integer,Integer> maps=new HashMap<Integer, Integer>();
    private SoundPool sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundpool);
        AudioAttributes attributes=new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
       .build();
        sp = new SoundPool.Builder()
        .setAudioAttributes(attributes)
        .setMaxStreams(10)
        .build();
        maps.put(1, sp.load(this,R.raw.alarm,1));
        maps.put(2, sp.load(this,R.raw.bomb,1));
        maps.put(3, sp.load(this,R.raw.shot,1));
        findViewById(R.id.btn_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(maps.get(1),1,1,0,0,1);
            }
        });
        findViewById(R.id.btn_bomb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(maps.get(2),1,1,0,0,1);
            }
        });
        findViewById(R.id.btn_shot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(maps.get(3),1,1,0,0,1);
            }
        });
    }
}
