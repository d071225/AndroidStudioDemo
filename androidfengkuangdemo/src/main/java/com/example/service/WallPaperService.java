package com.example.service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.androidfengkuangdemo.R;

import java.io.IOException;

/**
 * Created by DY on 2017/4/7.
 */

public class WallPaperService extends Service {
    private WallpaperManager manager;
    private int[] wallpapers={R.drawable.shuangta,
            R.drawable.lijiang,
            R.drawable.qiao,
            R.drawable.shui};
    private int count;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        try {
            manager.setResource(wallpapers[count++%wallpapers.length]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        manager=WallpaperManager.getInstance(this);
    }
}
