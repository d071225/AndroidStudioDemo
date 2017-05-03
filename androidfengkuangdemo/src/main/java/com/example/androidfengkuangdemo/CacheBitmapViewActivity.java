package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DY on 2017/3/23.
 */

public class CacheBitmapViewActivity extends Activity {

    private CacheBitmapView cacheBitmapView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        cacheBitmapView = new CacheBitmapView(this, width, height);
        setContentView(cacheBitmapView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.red:
                cacheBitmapView.paint.setColor(Color.RED);
                item.setChecked(true);
                break;
            case R.id.green:
                cacheBitmapView.paint.setColor(Color.GREEN);
                item.setChecked(true);
                break;
            case R.id.blue:
                cacheBitmapView.paint.setColor(Color.BLUE);
                item.setChecked(true);
                break;
            case R.id.width_1:
                cacheBitmapView.paint.setStrokeWidth(1);
                item.setChecked(true);
                break;
            case R.id.width_2:
                cacheBitmapView.paint.setStrokeWidth(4);
                item.setChecked(true);
                break;
            case R.id.width_3:
                cacheBitmapView.paint.setStrokeWidth(8);
                item.setChecked(true);
                break;
        }
        return true;
    }

    public class CacheBitmapView extends View{


        private Bitmap cacheBitmap=null;
        private Canvas cacheCanvas=null;
        private final Path path;
        private float preX;
        private float preY;
        public final Paint paint;

        public CacheBitmapView(Context context, int width, int height) {
            super(context);
            cacheBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            cacheCanvas = new Canvas();
            path = new Path();
            cacheCanvas.setBitmap(cacheBitmap);
            paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setAntiAlias(true);
            paint.setDither(true);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y);
                    preX =x;
                    preY =y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.quadTo(preX,preY,x,y);
                    preX=x;
                    preY=y;
                    break;
                case MotionEvent.ACTION_UP:
                    cacheCanvas.drawPath(path,paint);
                    path.reset();
                    break;
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint bitmapPaint=new Paint();
            bitmapPaint.setColor(Color.BLUE);
            canvas.drawBitmap(cacheBitmap,0,0,bitmapPaint);
            canvas.drawPath(path,paint);
        }
    }
}
