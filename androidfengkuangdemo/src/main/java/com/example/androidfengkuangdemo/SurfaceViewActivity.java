package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.File;
import java.io.IOException;

/**
 * Created by DY on 2017/4/12.
 */

public class SurfaceViewActivity extends Activity {

    private MediaPlayer mediaPlayer;
    private SurfaceView sv;
    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surfaceview);
        sv = (SurfaceView) findViewById(R.id.sv);
        mediaPlayer = new MediaPlayer();
        sv.getHolder().setKeepScreenOn(true);
        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
        findViewById(R.id.btn_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
               }else{
                   mediaPlayer.start();
               }
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
        });
        sv.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (position>0){
                    play();
                    mediaPlayer.seekTo(position);
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }
    public void play(){
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"gaobai.mp4");
            mediaPlayer.setDisplay(sv.getHolder());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()){
            position = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
    }
}
