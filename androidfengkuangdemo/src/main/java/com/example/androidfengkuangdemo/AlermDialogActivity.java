package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Created by DY on 2017/4/7.
 */

public class AlermDialogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("123","===AlermDialogActivity===");
        AudioManager manager= (AudioManager) getSystemService(AUDIO_SERVICE);
        final MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.alarm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        new AlertDialog.Builder(this)
                .setTitle("闹钟")
                .setMessage("闹钟响了")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        finish();
                    }
                }).show();
    }
}
