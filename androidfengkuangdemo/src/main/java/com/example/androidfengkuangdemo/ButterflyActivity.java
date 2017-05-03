package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/27.
 */

public class ButterflyActivity extends Activity {
    private float curX=0;
    private float curY=30;
    private float nextX=0;
    private float nextY=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (nextX>widthPixels){
                curX=nextX=0;
            }else {
                nextX+=8;
            }
            nextY= (float) (curY+(Math.random()*10-5));
            TranslateAnimation translateAnimation=new TranslateAnimation(curX,nextX,curY,nextY);
            curX=nextX;
            curY=nextY;
            translateAnimation.setDuration(200);
            butterflyIv.startAnimation(translateAnimation);

        }
    };
    private ImageView butterflyIv;
    private int widthPixels;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly);
        butterflyIv = (ImageView) findViewById(R.id.butterfly);
        WindowManager manager=getWindowManager();
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        widthPixels = metrics.widthPixels;
        final AnimationDrawable animationDrawable= (AnimationDrawable) butterflyIv.getBackground();
        butterflyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0);
                    }
                },0,200);
            }
        });
    }
}
