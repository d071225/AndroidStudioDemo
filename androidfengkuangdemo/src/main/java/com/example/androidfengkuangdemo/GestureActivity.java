package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by DY on 2017/3/29.
 */

public class GestureActivity extends Activity implements GestureDetector.OnGestureListener{

    private int width;
    private int height;
    private Bitmap bitmap;
    private ImageView iv;
    private GestureDetector detector;
    private Matrix matrix;
    private float scale=1;
    private Animation[] animations=new Animation[4];
    private ViewFlipper vf;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        detector = new GestureDetector(this,this);
        vf = (ViewFlipper) findViewById(R.id.vf);
        vf.addView(addImageView(R.drawable.java));
        vf.addView(addImageView(R.drawable.javaee));
        vf.addView(addImageView(R.drawable.ajax));
        vf.addView(addImageView(R.drawable.android));
        vf.addView(addImageView(R.drawable.swift));
        vf.addView(addImageView(R.drawable.html));
        animations[0]= AnimationUtils.loadAnimation(this,R.anim.anim_left_in);
        animations[1]= AnimationUtils.loadAnimation(this,R.anim.anim_left_out);
        animations[2]= AnimationUtils.loadAnimation(this,R.anim.anim_right_in);
        animations[3]= AnimationUtils.loadAnimation(this,R.anim.anim_right_out);
//        iv = (ImageView) findViewById(R.id.imageView2);
//        matrix = new Matrix();
//        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flower);
//        height = bitmap.getHeight();
//        width = bitmap.getWidth();
//        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flower));
//        final WindowManager manager=getWindowManager();
//        Display defaultDisplay = manager.getDefaultDisplay();
//        final DisplayMetrics metrics=new DisplayMetrics();
//        defaultDisplay.getMetrics(metrics);
//        final int widthPixels = metrics.widthPixels;
//        final int heightPixels = metrics.heightPixels;
//        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                matrix.reset();
//                matrix.setScale(0.5f,0.5f,100,100);
////                matrix.postTranslate(100,100);
//                Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//                iv.setImageBitmap(bitmap2);
////                iv.setImageMatrix(matrix);
//            }
//        });
    }
    public View addImageView(int id){
        ImageView iv=new ImageView(this);
        iv.setBackgroundResource(id);
        return iv;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        velocityX=velocityX>4000?4000:velocityX;
//        velocityX=velocityX<-4000?-4000:velocityX;
//        scale += scale*velocityX/4000f;
//        scale=scale>0.01f?scale:0.01f;
//        matrix.reset();
//        matrix.setScale(scale,scale,100,100);
//        BitmapDrawable drawable= (BitmapDrawable) iv.getDrawable();
//        if (!drawable.getBitmap().isRecycled()){
//            drawable.getBitmap().recycle();
//        }
//        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
//        iv.setImageBitmap(bitmap2);
        if (e1.getX()-e2.getX()>50){
            vf.setInAnimation(animations[0]);
            vf.setOutAnimation(animations[1]);
            vf.showPrevious();
            return true;
        }
        if (e2.getX()-e1.getX()>50){
            vf.setInAnimation(animations[2]);
            vf.setOutAnimation(animations[3]);
            vf.showPrevious();
            return true;
        }
        return true;
    }
}
