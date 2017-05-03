package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

/**
 * Created by DY on 2017/3/30.
 */

public class ResultActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ListView lv= (ListView) findViewById(R.id.lv_db);
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        List<Map<String,String>> lists= (List<Map<String, String>>) extras.getSerializable("data");
        SimpleAdapter adapter=new SimpleAdapter(this,lists,R.layout.listview_db_item,new String[]{"word","detail"},new int[]{R.id.tv_title,R.id.tv_content});
        lv.setAdapter(adapter);
    }
}
