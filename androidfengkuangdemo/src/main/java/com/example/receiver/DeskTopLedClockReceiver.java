package com.example.receiver;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;

import com.example.androidfengkuangdemo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by DY on 2017/4/18.
 */

public class DeskTopLedClockReceiver extends AppWidgetProvider {
    private int[] nums={R.drawable.su01,R.drawable.su02,R.drawable.su03,
            R.drawable.su04,R.drawable.su05,R.drawable.su06,
            R.drawable.su07,R.drawable.su08,R.drawable.su09,R.drawable.su10};
    private int[] imageIds={R.id.iv_01,R.id.iv_02,R.id.iv_04,R.id.iv_05,R.id.iv_07,R.id.iv_08};
    private Context context;
    private AppWidgetManager appWidgetManager;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(),R.layout.receiver_ledclock);
            SimpleDateFormat sdf=new SimpleDateFormat("HHmmss");
            String time = sdf.format(new Date());
//            Log.e("123","当前时间："+time);
            for (int i = 0; i <time.length() ; i++) {
                int num = time.charAt(i) - 48;
//                Log.e("123","time.charAt(i)："+time.charAt(i)+",num:"+num);
                remoteViews.setImageViewResource(imageIds[i],nums[num]);
            }
            ComponentName componentName=new ComponentName(context,DeskTopLedClockReceiver.class);
            appWidgetManager.updateAppWidget(componentName,remoteViews);
        }
    };
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        this.context=context;
        this.appWidgetManager=appWidgetManager;
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },0,1000);
    }
}
