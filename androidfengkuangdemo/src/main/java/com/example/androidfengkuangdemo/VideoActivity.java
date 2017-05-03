package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by DY on 2017/4/12.
 */

public class VideoActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_video);
        VideoView videoView= (VideoView) findViewById(R.id.vv);
        MediaController controller=new MediaController(this);
        File video=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"gaobai.mp4");
        Log.e("123","视频是否存在"+video.exists());
        if (video.exists()){
            videoView.setVideoPath(video.getAbsolutePath());
            videoView.setMediaController(controller);
            controller.setMediaPlayer(videoView);
            videoView.requestFocus();
        }
    }
}
