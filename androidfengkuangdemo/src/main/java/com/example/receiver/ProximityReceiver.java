package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by DY on 2017/4/19.
 */

public class ProximityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isProximity = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        Log.e("123","isProximity:"+isProximity);
        if (isProximity){
            Toast.makeText(context,"您已经进入了XXX范围之内",0).show();
        }else{
            Toast.makeText(context,"您已经离开了XXX范围",0).show();
        }
    }
}
