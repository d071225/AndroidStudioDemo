package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.androidfengkuangdemo.MusicActivity;
import com.example.service.MusicService;

import static com.example.service.MusicService.current;
import static com.example.service.MusicService.state;

/**
 * Created by DY on 2017/4/10.
 */

public class ServiceMusicReceiver extends BroadcastReceiver {
    private String[] musics;
    private MediaPlayer mp;
    private Context context;
    public ServiceMusicReceiver(Context context,String[] musics,MediaPlayer mp) {
        this.context=context;
        this.musics=musics;
        this.mp=mp;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int control = intent.getIntExtra("control", -1);
        Log.e("123","===control==="+control+"===current==="+current);
        switch (control){
            case 1:
                if (MusicService.state==0x11){
                    MusicService.preparePlay(musics[MusicService.current]);
                    MusicService.state=0x12;
                }else if (MusicService.state==0x12){
                    mp.pause();
                    MusicService.state=0x13;
                }else if (MusicService.state==0x13){
                    mp.start();
                    MusicService.state=0x12;
                }
                break;
            case 2:
                if (MusicService.state==0x12||state==0x13){
                    mp.stop();
                    MusicService.state=0x11;
                }
                break;
        }
        Intent intent1=new Intent(MusicActivity.UPDATE_ACTION);
        context.sendBroadcast(intent1);
    }
}
