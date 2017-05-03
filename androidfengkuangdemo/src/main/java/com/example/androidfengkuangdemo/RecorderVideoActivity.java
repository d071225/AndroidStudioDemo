package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static com.example.androidfengkuangdemo.R.id.btn_stop;

/**
 * Created by DY on 2017/4/12.
 */

public class RecorderVideoActivity extends Activity {

    private MediaRecorder recorder;
    private File file;
    private SurfaceView surfaceView;
    private Button btn_record,btn_stop;
    private boolean isRecording;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder_video);
        surfaceView = (SurfaceView) findViewById(R.id.sv);
        surfaceView.getHolder().setFixedSize(320,280);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().setKeepScreenOn(true);
        btn_record = (Button) findViewById(R.id.btn_record);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_record.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
                    Toast.makeText(RecorderVideoActivity.this,"请插入SD卡",0).show();
                    return;
                }
                file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"myvideo.mp4");
                recorder = new MediaRecorder();
                recorder.reset();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
//                recorder.setVideoSize(320,280);
//                recorder.setVideoFrameRate(4);
                recorder.setOutputFile(file.getAbsolutePath());
                recorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
                try {

                    recorder.prepare();
                    recorder.start();
                    btn_record.setEnabled(false);
                    btn_stop.setEnabled(true);
                    isRecording=true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording){
                    recorder.stop();
                    recorder.release();
                    recorder=null;
                    btn_record.setEnabled(true);
                    btn_stop.setEnabled(false);
                    isRecording=false;
                }
            }
        });
    }
}
