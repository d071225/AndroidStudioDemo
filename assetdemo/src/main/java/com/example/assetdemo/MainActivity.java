package com.example.assetdemo;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn= (Button) findViewById(R.id.btn);
        AssetManager manager=getAssets();
        try {
            AssetFileDescriptor afd = manager.openFd("shot.mp3");
            mp = new MediaPlayer();
            mp.setDataSource(afd.getFileDescriptor());
//            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Snackbar.make(btn,"snackbar",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
