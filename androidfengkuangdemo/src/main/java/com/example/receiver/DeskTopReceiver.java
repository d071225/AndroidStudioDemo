package com.example.receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.androidfengkuangdemo.R;
import com.example.androidfengkuangdemo.ShotcutActivity;

/**
 * Created by DY on 2017/4/18.
 */
public class DeskTopReceiver extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.receiver_desktop);
        remoteViews.setImageViewResource(R.id.iv,R.drawable.android);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,new Intent(context, ShotcutActivity.class),0);
        remoteViews.setOnClickPendingIntent(R.id.iv,pendingIntent);
        ComponentName componentName=new ComponentName(context,DeskTopReceiver.class);
        appWidgetManager.updateAppWidget(componentName,remoteViews);
    }
}
