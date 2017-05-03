package com.example.batterydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DY on 2017/4/11.
 */

public class BatteryReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int level = extras.getInt("level");
        int scale = extras.getInt("scale");
        Log.e("BatteryReceiver","当前电量为："+level);
        if (scale%2==0){
            Toast.makeText(context,"电量下降了2%，当前电量为："+level,0).show();
        }
    }
}
