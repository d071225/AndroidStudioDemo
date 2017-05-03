package com.example.androidfengkuangdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by DY on 2017/3/27.
 */

public class FishView extends SurfaceView implements SurfaceHolder.Callback {
    private final Bitmap fishbg;
    private SurfaceHolder holder;
    private final Bitmap[] fishs;
    private float fishX=778;
    private float fishY=500;
    private UpdateViewThread updateViewThread;
    private Matrix matrix;
    private int fishAngle=new Random().nextInt(60);
    private float fishSpeed=6;
    private int fishIndex=0;
    private boolean haseSurface;

    public FishView(Context context) {
        super(context);
        holder=getHolder();
        matrix=new Matrix();
        holder.addCallback(this);
        fishbg = BitmapFactory.decodeResource(getResources(), R.drawable.fishbg);
        fishs = new Bitmap[10];
        haseSurface=false;
        for (int i=0;i<10;i++){
            try {
                int filed= (int) R.drawable.class.getField("fish"+i).get(null);
                fishs[i]=BitmapFactory.decodeResource(getResources(),filed);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        haseSurface=true;
        resume();
    }

    private void resume() {
        if (updateViewThread==null){
            updateViewThread=new UpdateViewThread();
            if (haseSurface){
                updateViewThread.start();
            }
        }
    }
    public class UpdateViewThread extends Thread{
        private boolean done;

        public UpdateViewThread() {
            done=false;
        }

        @Override
        public void run() {
            SurfaceHolder surfaceHolder=holder;
            while (!done){
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawBitmap(fishbg,0,0,null);
                if (fishX<0|fishY<0){
                    fishX=778;
                    fishY=500;
                    fishAngle=new Random().nextInt(60);
                }
                matrix.reset();
                matrix.setRotate(fishAngle);
                matrix.postTranslate(fishX -= fishSpeed * Math
                                .cos(Math.toRadians(fishAngle))
                        , fishY -= fishSpeed * Math.sin(Math.toRadians(fishAngle)));
                canvas.drawBitmap(fishs[fishIndex++ % fishs.length], matrix, null);
                surfaceHolder.unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void requestExitAndWait() {
            done=true;
            try {
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void onWindowResize(int w,int h){

        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (updateViewThread != null)
            updateViewThread.onWindowResize(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        haseSurface=false;
            pause();
    }

    private void pause() {
        if (updateViewThread!=null){
            updateViewThread.requestExitAndWait();
            updateViewThread = null;
        }
    }
}
