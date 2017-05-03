package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/27.
 */

public class TweenAnimActivity extends Activity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            flower.startAnimation(reverse);

        }
    };
    private Animation reverse;
    private ImageView flower;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        flower = (ImageView) findViewById(R.id.flower_iv);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_demo);
        animation.setFillAfter(true);
        reverse = AnimationUtils.loadAnimation(this, R.anim.anim_demo2);
        animation.setFillAfter(true);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flower.startAnimation(animation);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                },4000);
            }
        });

    }
}
