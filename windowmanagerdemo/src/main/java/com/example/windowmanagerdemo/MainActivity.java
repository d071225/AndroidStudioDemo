package com.example.windowmanagerdemo;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private Button btn;
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private Button moveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveBtn = new Button(MainActivity.this);
                moveBtn.setText("windowmanager 控件");
                layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
                layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
                layoutParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                layoutParams.gravity= Gravity.LEFT|Gravity.TOP;
                layoutParams.x=100;
                layoutParams.y=300;
                windowManager.addView(moveBtn, layoutParams);
                moveBtn.setOnTouchListener(MainActivity.this);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int rawX= (int) event.getRawX();
        int rawY= (int) event.getRawY();
        Log.e("123","rawX:"+rawX+"rawY:"+rawY);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                layoutParams.x=rawX;
                layoutParams.y=rawY;
                Log.e("123","ACTION_MOVE rawX:"+rawX+"ACTION_MOVE rawY:"+rawY);
                windowManager.updateViewLayout(moveBtn,layoutParams);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return false;
    }
}
