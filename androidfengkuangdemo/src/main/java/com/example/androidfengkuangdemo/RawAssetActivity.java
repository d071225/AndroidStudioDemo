package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by DY on 2017/3/22.
 */

public class RawAssetActivity extends Activity {

    private MediaPlayer mediaPlayer1,mediaPlayer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_asset);
        // 直接根据声音文件的ID来创建MediaPlayer
        mediaPlayer1 = MediaPlayer.create(this, R.raw.bomb);
        // 获取该应用的AssetManager
        AssetManager am = getAssets();
        try
        {
            // 获取指定文件对应的AssetFileDescriptor
            AssetFileDescriptor afd = am.openFd("shot.mp3");
            mediaPlayer2 = new MediaPlayer();
            // 使用MediaPlayer加载指定的声音文件
            mediaPlayer2.setDataSource(afd.getFileDescriptor());
//            mediaPlayer2.prepare();
//            mediaPlayer2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mediaPlayer2.start();
//                    Log.e("123","onPrepared===>");
//                }
//            });
            mediaPlayer2.prepareAsync();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        // 获取第一个按钮，并为它绑定事件监听器
        Button playRaw = (Button) findViewById(R.id.btn_raw);
        playRaw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 播放声音
                mediaPlayer1.start();
            }
        });
        // 获取第二个按钮，并为它绑定事件监听器
        Button playAsset = (Button) findViewById(R.id.btn_asset);
        playAsset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 播放声音
                mediaPlayer2.start();
            }
        });
    }
}
