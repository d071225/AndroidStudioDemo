package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.view.PlantView;

/**
 * Created by DY on 2017/3/16.
 */

public class PlantActivity extends Activity {
    private float speed=10;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final PlantView plantView=new PlantView(this);
        setContentView(plantView);
        plantView.setBackgroundResource(R.drawable.back);
        WindowManager windowManager=getWindowManager();
        Display display=windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics=new DisplayMetrics();
        display.getMetrics(displayMetrics);
        plantView.currentX=displayMetrics.widthPixels/2;
        plantView.currentY=displayMetrics.heightPixels-40;
        plantView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_W:
                        plantView.currentY-=speed;
                        break;
                    case KeyEvent.KEYCODE_S:
                        plantView.currentY+=speed;
                        break;
                    case KeyEvent.KEYCODE_A:
                        plantView.currentX-=speed;
                        break;
                    case KeyEvent.KEYCODE_D:
                        plantView.currentX+=speed;
                        break;
                }
                plantView.invalidate();
                return true;
            }
        });
    }
}
