package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.File;

/**
 * Created by DY on 2017/3/29.
 */

public class DBActivity extends Activity {

    private SQLiteDatabase db;
    private EditText et_title;
    private EditText et_content;
    private Button btn_insert;
    private ListView lv_db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        lv_db = (ListView) findViewById(R.id.lv_db);
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+ File.separator+"my.db",null);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=et_title.getText().toString();
                String content=et_content.getText().toString();
                try{
                    insertDb(title, content);
                    Cursor cursor = db.rawQuery("select * from news_info", null);
                    initListView(cursor);
                }catch (SQLException e) {
                    db.execSQL("create table news_info(_id integer" +
                            " primary key autoincrement," +
                            "news_title varchar(50)," +
                            "news_content varchar(255))");
                    insertDb(title, content);
                    Cursor cursor = db.rawQuery("select * from news_info", null);
                    initListView(cursor);
                }
            }
        });
    }

    private void initListView(Cursor cursor) {
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.listview_db_item,cursor,new String[]{"news_title","news_content"},new int[]{R.id.tv_title,R.id.tv_content}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv_db.setAdapter(adapter);
    }

    private void insertDb(String title, String content) {
//        db.execSQL("insert into news_info values(null,?,?)",new String[]{title,content});
        ContentValues values=new ContentValues();
        values.put("news_title",title);
        values.put("news_conten",content);
        db.insert("news_info",null,values);
    }
}
