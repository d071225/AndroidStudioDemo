package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

/**
 * Created by DY on 2017/3/24.
 */

public class MatricActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

    }
    public class MyView extends View{

        private final Bitmap bitmap;
        private final int height;
        private final int width;
        private final Matrix matrix;
        private boolean isScale=false;
        private float sx=0.0f;
        private float scale=1.0f;

        public MyView(Context context) {
            super(context);
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.a);
            height = bitmap.getHeight();
            width = bitmap.getWidth();
            matrix = new Matrix();
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            matrix.reset();
            if (!isScale){
                matrix.setSkew(sx,0);
            }else{
                matrix.setScale(scale,scale);
            }
            Bitmap bitmap2 = Bitmap.createBitmap(this.bitmap, 0, 0, width, height, matrix, true);
            canvas.drawBitmap(bitmap2,matrix,null);
        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            switch (event.getKeyCode()){
                case KeyEvent.KEYCODE_A:
                    isScale=false;
                    sx-=0.1;
                    invalidate();
                    break;
                case KeyEvent.KEYCODE_D:
                    isScale=false;
                    sx+=0.1;
                    invalidate();
                    break;
                case KeyEvent.KEYCODE_W:
                    isScale=true;
                    if (scale<2.0){
                        scale+=0.1;
                    }
                    invalidate();
                    break;
                case KeyEvent.KEYCODE_S:
                    isScale=true;
                    if (scale>0.5){
                        scale-=0.1;
                    }
                    invalidate();
                    break;
            }
            return true;
        }
    }
}
