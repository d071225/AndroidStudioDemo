package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.receiver.ProximityReceiver;

/**
 * Created by DY on 2017/4/19.
 */

public class ProximityGPSActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LocationManager lm= (LocationManager) getSystemService(LOCATION_SERVICE);
//        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,-1,new Intent(this, ProximityReceiver.class),0);
//        lm.addProximityAlert(31.230416,121.473701,5000,-1,pendingIntent);
        TextView textView=new TextView(this);
        textView.setText("GPS测试");
        setContentView(textView);
        // 定位服务的常量
        String locService = Context.LOCATION_SERVICE;
        // 定位服务管理器实例
        final LocationManager locationManager;
        // 通过getSystemService方法获得LocationManager实例
        locationManager = (LocationManager) getSystemService(locService);
        // 定义"疯狂软件教育中心"的经度、纬度
        double longitude = 113.401863;
        double latitude = 23.132636;
        // 定义半径（5公里）
        float radius = 5000;
        // 定义Intent
        Intent intent = new Intent(this, ProximityReceiver.class);
        // 将Intent包装成PendingIntent
        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);
        // 添加临近警告
        locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 300, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                locationManager.getLastKnownLocation(provider);
            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }
}
