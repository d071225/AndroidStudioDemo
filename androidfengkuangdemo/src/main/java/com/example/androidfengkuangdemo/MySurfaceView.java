package com.example.androidfengkuangdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by DY on 2017/3/28.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private Canvas canvas;
    private boolean flag;
    private final Paint paint;
    private float x=50;
    private float y=50;
    private float r=10;
    public MySurfaceView(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
             while (flag)  {
                 doDraw();
             }
            }
        });
        flag=true;
        thread.start();
    }

    private void doDraw() {
        canvas = holder.lockCanvas();//获取画布，开始绘画
        canvas.drawRGB(0,0,0);
        canvas.drawCircle(x,y,r,paint);
        holder.unlockCanvasAndPost(canvas);//完成绘画，将画布展示在屏幕上
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            flag=false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x=event.getX();
        y=event.getY();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()){
            case KeyEvent.KEYCODE_W:
                y-=5;
                break;
            case KeyEvent.KEYCODE_S:
                y+=5;
                break;
            case KeyEvent.KEYCODE_A:
                x-=5;
                break;
            case KeyEvent.KEYCODE_D:
                x+=5;
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
