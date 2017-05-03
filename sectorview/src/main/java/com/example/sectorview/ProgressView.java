package com.example.sectorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by DY on 2017/3/2.
 */
public class ProgressView extends View {

    private int measuredWidth;
    private int startX = 0;
    private Paint paint;
    private TypedArray array;
    private float progressWide;
    private float progressDivide;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView, 0, 0);
        progressWide = array.getDimension(R.styleable.ProgressView_progress_width, 80);
        progressDivide = array.getDimension(R.styleable.ProgressView_progress_divide, 10);
        array.recycle();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        measuredWidth = this.getMeasuredWidth();
        Log.e("123", "getMeasuredWidth==" + measuredWidth);
        if (startX >= measuredWidth + progressWide + 10 - (measuredWidth % (progressWide+progressDivide))) {
            startX = 0;
        } else {
            startX += 10;
        }
        float start = startX;
        while (start < measuredWidth) {
            Log.e("123", "start==" + start);
            canvas.drawLine(start, 5, start + progressWide, 5, paint);
            start += (progressWide+progressDivide);
        }
        while (start >= -progressWide) {
            canvas.drawLine(start, 5, start + progressWide, 5, paint);
            start -= (progressWide+progressDivide);
        }
        invalidate();
//        canvas.drawLine(0,5,0+80,5,paint);
    }
}
