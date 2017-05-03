package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/20.
 */

public class ClipActivity extends Activity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
                drawable.setLevel(drawable.getLevel()+200);
        }
    };
    private ClipDrawable drawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip);
        ImageView iv= (ImageView) findViewById(R.id.iv);
        drawable = (ClipDrawable) iv.getDrawable();
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (drawable.getLevel()>10000){
                    timer.cancel();
                }else{
                    handler.sendEmptyMessage(0);
                }
            }
        },0,300);
    }
}
