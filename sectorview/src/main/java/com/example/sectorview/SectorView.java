package com.example.sectorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by DY on 2017/3/1.
 */
public class SectorView extends View {

    private TypedArray typedArray;
    private int color;
    private float radius;

    public SectorView(Context context) {
        this(context,null);
    }

    public SectorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SectorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Sector, 0, 0);
        color = typedArray.getColor(R.styleable.Sector_background_color, Color.BLACK);
        radius = typedArray.getDimension(R.styleable.Sector_radius, 50);
        typedArray.recycle();
    }
    public void addSize(){
        if (radius<300){
            radius+=5;
            invalidate();
            requestLayout();
        }else{
            Toast.makeText(getContext(),"已经最大了",Toast.LENGTH_LONG).show();
        }
    }
    public void decreaseSize(){
        if (radius>10){
            radius-=5;
            invalidate();
            requestLayout();
        }else{
            Toast.makeText(getContext(),"已经最小了",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint=new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        canvas.drawCircle(0,0,radius,paint);
    }
}
