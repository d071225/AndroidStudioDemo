package com.example.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.androidfengkuangdemo.R;

/**
 * Created by DY on 2017/3/16.
 */

public class PlantView extends View {

    private Bitmap plant;
    public float currentX,currentY;

    public PlantView(Context context) {
        this(context,null);
    }

    public PlantView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PlantView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        plant = BitmapFactory.decodeResource(context.getResources(), R.drawable.plane);
        setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p=new Paint();
        canvas.drawBitmap(plant,currentX,currentY,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX=event.getX();
        currentY=event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                invalidate();
                break;
        }
        return true;
    }
}
