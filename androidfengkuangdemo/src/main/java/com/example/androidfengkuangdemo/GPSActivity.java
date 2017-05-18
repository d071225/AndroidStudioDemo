package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.receiver.ProximityReceiver;

import java.util.List;

/**
 * Created by DY on 2017/4/19.
 */

public class GPSActivity extends Activity {

    private TextView tv_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        ListView lv_gps= (ListView) findViewById(R.id.lv_gps);
        tv_show = (TextView) findViewById(R.id.tv_show);
        final LocationManager lm= (LocationManager) getSystemService(LOCATION_SERVICE);
        //获取locationprovider
        Criteria criteria=new Criteria();
        criteria.setCostAllowed(false);
        criteria.setAltitudeRequired(true);
        criteria.setBearingRequired(true);
        List<String> providers = lm.getProviders(criteria,false);
        Log.e("123","providers.size():"+providers.size());
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,providers);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,providers);
        Log.e("123","equels"+adapter.equals(adapter1)+"=="+(adapter==adapter1));
        lv_gps.setAdapter(adapter);
        //获取定位信息
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateView(location);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,-1,new Intent(this, ProximityReceiver.class),0);
        lm.addProximityAlert(31.230416,121.473701,5000,-1,pendingIntent);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateView(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                updateView(lm.getLastKnownLocation(provider));
            }

            @Override
            public void onProviderDisabled(String provider) {
                updateView(null);
            }
        });
    }

    private void updateView(Location location) {
        if (location!=null){
            StringBuilder sb=new StringBuilder();
            sb.append("实时位置信息:\n");
            sb.append("经度：");
            sb.append(location.getLongitude()+"\n");
            sb.append("纬度：");
            sb.append(location.getLatitude()+"\n");
            sb.append("高度：");
            sb.append(location.getAltitude()+"\n");
            sb.append("速度：");
            sb.append(location.getSpeed()+"\n");
            sb.append("方向：");
            sb.append(location.getBearing()+"\n");
            tv_show.setText(sb.toString());
        }else{
            tv_show.setText("无GPS的信息");
        }
    }
}
