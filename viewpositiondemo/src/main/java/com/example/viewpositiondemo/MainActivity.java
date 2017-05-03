package com.example.viewpositiondemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn= (Button)findViewById(R.id.btn);
        final RelativeLayout rl1= (RelativeLayout) findViewById(R.id.rl1);
        final RelativeLayout rl2= (RelativeLayout) findViewById(R.id.rl2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("123", "button left:" + btn.getLeft() + "right:" + btn.getRight() + "top:" + btn.getTop() + "bottom:" + btn.getBottom());
                Log.e("123", "rl1 left:" + rl1.getLeft() + "right:" + rl1.getRight() + "top:" + rl1.getTop() + "bottom:" + rl1.getBottom());
                Log.e("123", "rl2 left:" + rl2.getLeft() + "right:" + rl2.getRight() + "top:" + rl2.getTop() + "bottom:" + rl2.getBottom());
                Log.e("123", "判定滑动的最小距离：" + ViewConfiguration.get(MainActivity.this).getScaledTouchSlop());
            }
        }, 2000);
//        ObjectAnimator.ofFloat(btn,"translationX",0,100).setDuration(100).start();
    }
}
