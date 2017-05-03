package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.ScaleAnimation;

/**
 * Created by DY on 2017/3/27.
 */

public class MyAnimationActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myanimation);
        WindowManager manager=getWindowManager();
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);
        float widthPixels = metrics.xdpi;
        float heightPixels = metrics.ydpi;
        ScaleAnimation scaleAnimation=new ScaleAnimation(0.1f,1.0f,0.1f,1.0f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
//        findViewById(R.id.lv).setAnimation(new MyAnimation(widthPixels/2,heightPixels/2,3500));
        findViewById(R.id.lv).setAnimation(scaleAnimation);
    }
}
