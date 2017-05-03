package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by DY on 2017/4/7.
 */

public class AudioActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ToggleButton tb= (ToggleButton) findViewById(R.id.tb);
        final Vibrator vibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final AudioManager manager= (AudioManager) getSystemService(Service.AUDIO_SERVICE);
        findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer=MediaPlayer.create(AudioActivity.this,R.raw.bomb);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });
        findViewById(R.id.btn_increase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_SHOW_UI);
            }
        });
        findViewById(R.id.btn_decrease).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_LOWER,AudioManager.FLAG_SHOW_UI);
            }
        });
        findViewById(R.id.btn_vibrate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(2000);
            }
        });
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                manager.setStreamMute(AudioManager.STREAM_MUSIC,isChecked);
            }
        });
    }
}
