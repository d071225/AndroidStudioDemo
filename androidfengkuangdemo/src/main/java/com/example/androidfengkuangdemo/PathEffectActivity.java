package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DY on 2017/3/23.
 */

public class PathEffectActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }
    public class MyView extends View{
        private PathEffect[] effects=new PathEffect[7];
        private Paint mPaint;
        private int[] colors;
        private Path path;
        private float apha=0;
        public MyView(Context context) {
            this(context,null);
        }

        public MyView(Context context, @Nullable AttributeSet attrs) {
            this(context, attrs,0);
        }

        public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(4);
            path = new Path();
            path.moveTo(0,0);
            for (int i=1;i<40;i++){
                path.lineTo(i*20, (float) Math.random()*60);
            }
            colors = new int[]{Color.BLACK,Color.BLUE,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.RED, Color.YELLOW};
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            effects[0]=null;
            effects[1]=new CornerPathEffect(10);
            effects[2]=new DiscretePathEffect(3.0f,5.0f);
            effects[3]=new DashPathEffect(new float[]{20,10,5,10},apha);
            Path p=new Path();
//            p.addRect(0,0,8,8,Path.Direction.CCW);
            p.moveTo(4, 0);
            p.lineTo(0, -4);
            p.lineTo(8, -4);
            p.lineTo(12, 0);
            p.lineTo(8, 4);
            p.lineTo(0, 4);
            effects[4]=new PathDashPathEffect(p,12,apha,PathDashPathEffect.Style.ROTATE);
            effects[5]=new ComposePathEffect(effects[2],effects[4]);
            effects[6]=new SumPathEffect(effects[4],effects[3]);
            canvas.translate(8,8);
            for (int i=0;i<effects.length;i++){
                mPaint.setPathEffect(effects[i]);
                mPaint.setColor(colors[i]);
                canvas.drawPath(path,mPaint);
                canvas.translate(0,60);
            }
            apha++;
            invalidate();
        }
    }
}
