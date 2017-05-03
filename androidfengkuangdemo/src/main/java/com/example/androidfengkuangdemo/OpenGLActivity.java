package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.view.MyRenderer;

/**
 * Created by DY on 2017/4/13.
 */

public class OpenGLActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView=new GLSurfaceView(this);
        glSurfaceView.setRenderer(new MyRenderer());
        setContentView(glSurfaceView);
    }
}
