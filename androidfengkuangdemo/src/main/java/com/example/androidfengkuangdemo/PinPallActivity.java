package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/24.
 */

public class PinPallActivity extends Activity{
    private boolean isClosed=false;
    private int widthPixels;
    private int heightPixels;
    private Random random=new Random();
    private int ballX=random.nextInt(200)+20;
    private int ballY=random.nextInt(10)+20;
    private int ballSize=16;
    private int racketX=random.nextInt(200);
    private int racketY;
    private int racketWidth=90;
    private int racketHeight=30;
    private int ySpeed=15;
    private double xyRate=random.nextDouble()-0.5;
    private int xSpeed= (int) (ySpeed*xyRate*2);
    private MyView myView;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
                myView.invalidate();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myView = new MyView(this);
        setContentView(myView);
        WindowManager windowManager=getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        display.getMetrics(metrics);
        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;
        racketY=heightPixels-80;
        myView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_A:
                        if (racketX>0){
                            racketX-=10;
                        }
                        break;
                    case KeyEvent.KEYCODE_D:
                        if (racketX<widthPixels-racketWidth){
                            racketX+=10;
                        }
                        break;
                }
                myView.invalidate();
                return true;
            }
        });
        final Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (ballX<=0||ballX>=widthPixels-ballSize){
                    xSpeed=-xSpeed;
                }
                if (ballY>=racketY-ballSize&&(ballX<racketX||ballX>racketX+racketWidth)){
                    timer.cancel();
                    isClosed=true;
                }
                if (ballY<=0||(ballY>=racketY-ballSize&&ballX>racketX&&ballX<=racketX+widthPixels)){
                    ySpeed=-ySpeed;
                }
                ballX+=xSpeed;
                ballY+=ySpeed;
                handler.sendEmptyMessage(0);
            }
        },0,100);
    }
    public class MyView extends View{

        private final Paint paint;

        public MyView(Context context) {
            super(context);
            paint = new Paint();
            paint.setAntiAlias(true);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            if (isClosed){
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏已经结束",widthPixels/2-100,200,paint);
            }else{
                paint.setColor(Color.rgb(255,0,0));
                canvas.drawCircle(ballX,ballY,ballSize,paint);
                paint.setColor(Color.rgb(80,80,200));
                canvas.drawRect(racketX,racketY,racketX+racketWidth,racketY+racketHeight,paint);
            }
        }
    }
}
