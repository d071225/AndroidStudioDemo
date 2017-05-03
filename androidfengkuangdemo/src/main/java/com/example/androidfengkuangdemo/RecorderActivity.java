package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by DY on 2017/4/12.
 */

public class RecorderActivity extends Activity {

    private MediaRecorder recorder;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorder);

        findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
                    Toast.makeText(RecorderActivity.this,"请插入SD卡",0).show();
                    return;
                }
                file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"soud.amr");
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(file.getAbsolutePath());
                try {
                    recorder.prepare();
                    recorder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file!=null&&file.exists()){
                    recorder.stop();
                    recorder.release();
                    recorder=null;
                }
            }
        });
    }
}
