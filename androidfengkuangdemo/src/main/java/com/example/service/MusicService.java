package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.androidfengkuangdemo.MusicActivity;
import com.example.receiver.ServiceMusicReceiver;

import java.io.IOException;

/**
 * Created by DY on 2017/4/10.
 */

public class MusicService extends Service {
    private String[] musics={ "A-Lin.mp3", "周二珂 - 告白气球.mp3",
            "徐良、阿悄 - 犯贱.mp3"};
    private static AssetManager manager;
    private static MediaPlayer mp;
    public static int current=0;
    public static int state=0x11;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager = getAssets();
        mp = new MediaPlayer();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(MusicActivity.CTL_ACTION);
        Log.e("123","===onCreate===");
        ServiceMusicReceiver receiver=new ServiceMusicReceiver(this,musics,mp);
        registerReceiver(receiver,intentFilter);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                Log.e("123","===onCompletion===");
                Intent intent=new Intent(MusicActivity.UPDATE_ACTION);
                sendBroadcast(intent);
                preparePlay(musics[current%musics.length]);
            }
        });
    }
    public static void preparePlay(String music){
        try {
            Log.e("123","===preparePlay===");
            AssetFileDescriptor fd = manager.openFd(music);
            mp.reset();
            mp.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(),fd.getLength());
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
