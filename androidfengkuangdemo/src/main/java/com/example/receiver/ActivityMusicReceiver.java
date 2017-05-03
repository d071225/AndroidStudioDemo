package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidfengkuangdemo.R;
import com.example.service.MusicService;

/**
 * Created by DY on 2017/4/10.
 */

public class ActivityMusicReceiver extends BroadcastReceiver {
    private String[] titles;
    private String[] authors;
    private TextView tv_title;
    private TextView tv_author;
    private Button btn_play;
    public ActivityMusicReceiver(String[] titles, String[] authors, TextView tv_title,TextView tv_author,Button btn_play) {
        this.titles=titles;
        this.authors=authors;
        this.tv_title=tv_title;
        this.tv_author=tv_author;
        this.btn_play=btn_play;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("123","===current==="+ MusicService.current+"===state==="+MusicService.state);
        if (MusicService.current!=-1){
            tv_title.setText(titles[MusicService.current%3]);
            tv_author.setText(authors[MusicService.current%3]);
        }
        switch (MusicService.state){
            case 0x11:
                btn_play.setBackgroundResource(R.drawable.play);
                break;
            case 0x12:
                btn_play.setBackgroundResource(R.drawable.pause);
                break;
            case 0x13:
                btn_play.setBackgroundResource(R.drawable.play);
                break;
        }
    }
}
