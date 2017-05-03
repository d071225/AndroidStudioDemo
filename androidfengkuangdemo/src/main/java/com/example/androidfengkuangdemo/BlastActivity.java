package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.reflect.Field;

/**
 * Created by DY on 2017/3/27.
 */

public class BlastActivity extends Activity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame=new FrameLayout(this);
        setContentView(frame);
        final MediaPlayer boom=MediaPlayer.create(this,R.raw.bomb);
        final MyView myView=new MyView(this);
        frame.setBackgroundResource(R.drawable.back);
        myView.setBackgroundResource(R.drawable.anim_myview);
        myView.setVisibility(View.INVISIBLE);
        animationDrawable = (AnimationDrawable) myView.getBackground();
        frame.addView(myView);
        frame.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
//                        animationDrawable.stop();
                        float x = event.getX();
                        float y = event.getY();
                        myView.setLoacation((int)y-100,(int)x-20);
                        myView.setVisibility(View.VISIBLE);
                        animationDrawable.start();
                        boom.start();
                        break;
                }
                return false;
            }
        });
    }
    public class MyView extends ImageView{

        public MyView(Context context) {
            super(context);
        }
        public void setLoacation(int top,int left){
            setFrame(left,top,left+40,top+100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            try {
                Field field=AnimationDrawable.class.getDeclaredField("mCurFrame");
                field.setAccessible(true);
                int current=field.getInt(animationDrawable);
                if (current==animationDrawable.getNumberOfFrames()-1){
                    setVisibility(INVISIBLE);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
