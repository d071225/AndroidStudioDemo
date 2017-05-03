package com.example.androidfkjydemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by DY on 2017/3/8.
 */

public class SlidingView extends View {

    private Paint paint;
    private float currentX;
    private float currentY;

    public SlidingView(Context context) {
        this(context,null);
    }

    public SlidingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(currentX,currentY,15,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX=event.getX();
        currentY=event.getY();
        invalidate();
        return true;
    }
}
