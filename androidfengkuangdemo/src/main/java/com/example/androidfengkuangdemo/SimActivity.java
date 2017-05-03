package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DY on 2017/4/6.
 */

public class SimActivity extends Activity {
    private String[] statusNames,simState,phoneType;
    private List<String> values=new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);
        ListView simLv= (ListView) findViewById(R.id.lv);
        TelephonyManager tm= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        statusNames=getResources().getStringArray(R.array.statusNames);
        simState=getResources().getStringArray(R.array.simState);
        phoneType=getResources().getStringArray(R.array.phoneType);
        values.add(tm.getDeviceId());
        values.add(tm.getDeviceSoftwareVersion()!=null?tm.getDeviceSoftwareVersion():"未知");
        values.add(tm.getNetworkOperator());
        values.add(tm.getNetworkOperatorName());
        values.add(phoneType[tm.getNetworkType()%phoneType.length]);
        values.add(tm.getCellLocation()!=null?tm.getCellLocation().toString():"未知位置");
        values.add(tm.getSimCountryIso());
        values.add(tm.getSimSerialNumber());
        values.add(simState[tm.getSimState()]);
        ArrayList<Map<String,String>> datas=new ArrayList<Map<String, String>>();
        for (int i=0;i<values.size();i++){
            Map<String,String> map=new HashMap<String,String>();
            map.put("name",statusNames[i]);
            map.put("value",values.get(i));
            datas.add(map);
        }
        simLv.setAdapter(new SimpleAdapter(this,datas,R.layout.listview_db_item,new String[]{"name","value"},new int[]{R.id.tv_title,R.id.tv_content}));
    }
}
