package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.service.WallPaperService;

/**
 * Created by DY on 2017/4/7.
 */

public class AlarmChangeWallpaperActivity extends Activity {

    private PendingIntent pi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        Intent intent=new Intent(this, WallPaperService.class);
        pi = PendingIntent.getService(this,0,intent,0);
        final AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,0,2000, pi);
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.cancel(pi);
            }
        });

    }
}
