package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/3/24.
 */

public class PlaneActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyPlane(this));
    }
    public class MyPlane extends View{

        private final Bitmap backBitmap,planeBitmap;
        private final float widthPixels;
        private final int heightPixels;
        private final Matrix matrix;
        private int width;
        private int backHeight;
        private int startY;
        private Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (startY<=3){
                    startY=backHeight-heightPixels;
                }else{
                    startY-=3;
                }
                invalidate();
            }
        };
        public MyPlane(Context context) {
            super(context);
            backBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.back_img);
            planeBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
            WindowManager windowManager=getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics metrics=new DisplayMetrics();
            display.getMetrics(metrics);
            widthPixels = metrics.widthPixels;
            heightPixels = metrics.heightPixels;
            width=backBitmap.getWidth();
            backHeight=backBitmap.getHeight();
            startY=backHeight-heightPixels;
            matrix = new Matrix();
            float scale=widthPixels/width;
            Log.e("123","scale===>"+scale+"width===>"+widthPixels+"width===>"+width);
            matrix.setScale(scale,1.0f);
            Timer timer=new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            },0,100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Bitmap backBitmap2 = Bitmap.createBitmap(backBitmap, 0, startY, width, heightPixels, matrix, false);
            canvas.drawBitmap(backBitmap2,0,0,null);
            canvas.drawBitmap(planeBitmap,320,700,null);
        }
    }
}
