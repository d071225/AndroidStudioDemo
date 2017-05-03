package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.key;
import static android.R.attr.publicKey;

/**
 * Created by DY on 2017/3/30.
 */

public class SQLiteOpenHelperActivity extends Activity {

    private EditText et_insert_word;
    private EditText et_insert_detail;
    private EditText et_query;
    private MyDatabaseHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        et_insert_word = (EditText) findViewById(R.id.et_insert_word);
        et_insert_detail = (EditText) findViewById(R.id.et_insert_detail);
        et_query = (EditText) findViewById(R.id.et_query);
        helper = new MyDatabaseHelper(this,"myWord.db",null,1);
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=et_insert_word.getText().toString();
                String detail=et_insert_detail.getText().toString();
                ContentValues values=new ContentValues();
                values.put("word",word);
                values.put("detail",detail);
                helper.getReadableDatabase().insert("dict",null,values);
                Toast.makeText(SQLiteOpenHelperActivity.this,"生词添加成功",0).show();
            }
        });
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=et_query.getText().toString();
                Cursor cursor = helper.getReadableDatabase().rawQuery("select * from dict where word like ? or detail like ?",
                        new String[]{"%" + key + "%", "%" + key + "%"});
                if(cursor.getCount()!=0){
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("data",cursorToList(cursor));
                    Intent intent=new Intent(SQLiteOpenHelperActivity.this,ResultActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(SQLiteOpenHelperActivity.this,"没有查到相关的信息",0).show();
                }
            }
        });
    }
    public ArrayList<Map<String,String>> cursorToList(Cursor cursor){
        ArrayList<Map<String,String>> mapArrayList=new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<String,String>();
            Log.e("123","word:"+cursor.getString(1)+"detail:"+cursor.getString(2));
            map.put("word",cursor.getString(1));
            map.put("detail",cursor.getString(2));
            mapArrayList.add(map);
        }
        return mapArrayList;
    }
}
