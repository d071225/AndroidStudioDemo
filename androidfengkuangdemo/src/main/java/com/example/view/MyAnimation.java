package com.example.view;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Created by DY on 2017/3/27.
 */

public class MyAnimation extends Animation {
    private float x;
    private float y;
    private int duration;
    private Camera camera=new Camera();
    public MyAnimation(float x,float y,int duration) {
        this.x=x;
        this.y=y;
        this.duration=duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        setDuration(duration);
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        camera.save();
        camera.translate(100.0f-100.0f*interpolatedTime,150.0f-150.f*interpolatedTime,80.0f-80.0f*interpolatedTime);
        camera.rotateY(360*interpolatedTime);
        camera.rotateX(360*interpolatedTime);
        Matrix matrix=t.getMatrix();
        camera.getMatrix(matrix);
        matrix.preTranslate(-x,-y);
        matrix.postTranslate(x,y);
        camera.restore();
    }
}
