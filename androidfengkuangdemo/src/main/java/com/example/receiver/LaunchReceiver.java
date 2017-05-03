package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DY on 2017/4/11.
 */

public class LaunchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        int level = extras.getInt("level");
        int scale = extras.getInt("scale");
        Toast.makeText(context,"电量改变",0).show();
        Log.e("LaunchReceiver","当前电量："+level+",总电量："+scale);
    }
}
