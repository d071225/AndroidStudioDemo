package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DY on 2017/3/23.
 */

public class DrawTextActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new myView(this));
    }
    public class myView extends View{


        private Paint paint;
        private Path[] paths=new Path[3];
        private final static String DRAW_CONTEXT="疯狂android讲义";
        public myView(Context context) {
            this(context,null);
        }

        public myView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs,0);
        }

        public myView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            paint = new Paint(Paint.DITHER_FLAG);
            paint.setAntiAlias(true);
            paint.setColor(Color.CYAN);
            paint.setStrokeWidth(1);
            paths[0]=new Path();
            paths[0].moveTo(0,0);
            for (int i=1;i<20;i++){
                paths[0].lineTo(i*30, (float) (Math.random()*30));
            }
            paths[1]=new Path();
            RectF rectF=new RectF(0,0,600,300);
            paths[1].addOval(rectF, Path.Direction.CCW);
            paths[2]=new Path();
            paths[2].addArc(rectF,60,90);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            canvas.translate(40,40);
            paint.setTextAlign(Paint.Align.RIGHT);
            paint.setTextSize(20);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[0],paint);
            paint.setTextSize(40);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_CONTEXT,paths[0],-8,20,paint);
            canvas.translate(0,60);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[1],paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_CONTEXT,paths[1],-100,35,paint);
            canvas.translate(0,360);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(paths[2],paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawTextOnPath(DRAW_CONTEXT,paths[2],-10,0,paint);
            invalidate();
        }
    }
}
