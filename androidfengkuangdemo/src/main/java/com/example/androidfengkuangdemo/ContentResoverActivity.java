package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DY on 2017/3/30.
 */

public class ContentResoverActivity extends Activity {

    private EditText et_insert_word;
    private EditText et_insert_detail;
    private EditText et_query;

    //    private Uri uri=Uri.parse("content://org.crazyit.image.MyProvider/");
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        et_insert_word = (EditText) findViewById(R.id.et_insert_word);
        et_insert_detail = (EditText) findViewById(R.id.et_insert_detail);
        et_query = (EditText) findViewById(R.id.et_query);
        final ContentResolver resolver=getContentResolver();
        findViewById(R.id.btn_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=et_query.getText().toString();
                Cursor cursor = resolver.query(Words.word.DICT_CONTENT_URI, null, "word like ? or detail like ?", new String[]{
                        "%" + query + "%", "%" + query + "%"}, null);
                if (cursor.getCount()!=0){
                    View view=View.inflate(ContentResoverActivity.this,R.layout.alertdialog_item_word,null);
                    AlertDialog.Builder builder=new AlertDialog.Builder(ContentResoverActivity.this);
                    builder.setView(view);
                    SimpleAdapter adapter=new SimpleAdapter(ContentResoverActivity.this,cursorToList(cursor),R.layout.listview_item_word,
                            new String[]{"word","detail"},new int[]{R.id.tv_word,R.id.tv_detail});
                    builder.setAdapter(adapter,null);
                    builder.show();
                }else{
                    Toast.makeText(ContentResoverActivity.this,"没有找到相关信息",0).show();
                }
            }
        });
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=et_insert_word.getText().toString();
                String detail=et_insert_detail.getText().toString();
                ContentValues values=new ContentValues();
                values.put("word",word);
                values.put("detail",detail);
                resolver.insert(Words.word.DICT_CONTENT_URI,values);
                Toast.makeText(ContentResoverActivity.this,"生词添加成功",0).show();
            }
        });

    }

    private List<? extends Map<String,?>> cursorToList(Cursor cursor) {
        List<Map<String,String>> lists=new ArrayList<Map<String, String>>();
        while (cursor.moveToNext()){
            Map<String,String> map=new HashMap<String,String>();
            map.put("word",cursor.getString(1));
            map.put("detail",cursor.getString(2));
            lists.add(map);
        }
        return lists;
    }
}
