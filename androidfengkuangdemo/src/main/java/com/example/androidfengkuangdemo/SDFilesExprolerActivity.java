package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DY on 2017/3/29.
 */

public class SDFilesExprolerActivity extends Activity {

    private TextView tv_path;
    private ListView lv_files;
    private Button btn_back;
    private File[] currentFiles;
    private File currentParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        tv_path = (TextView) findViewById(R.id.tv_path);
        lv_files = (ListView) findViewById(R.id.lv_files);
        btn_back = (Button) findViewById(R.id.btn_back);

        File root=new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        if (root.exists()){
            currentParent =root;
            currentFiles = root.listFiles();
            initListView(currentFiles);
        }
        lv_files.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentFiles[position].isDirectory()){
                    File[] tem = currentFiles[position].listFiles();
                    if (tem.length==0||tem==null){
                        Toast.makeText(SDFilesExprolerActivity.this,"当前文件夹为空",0).show();
                    }else {
                        currentParent=currentFiles[position];
                        currentFiles=tem;
                        initListView(currentFiles);
                    }
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!currentParent.getCanonicalPath().equals(Environment.getExternalStorageDirectory().getAbsolutePath())){
                        currentParent = currentParent.getParentFile();
                        currentFiles=currentParent.listFiles();
                        initListView(currentFiles);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initListView(File[] files) {
        List<Map<String,Object>> lists=new ArrayList<Map<String, Object>>();
        for (int i=0;i<files.length;i++){
            Map<String,Object> listItem=new HashMap<String,Object>();
            if (files[i].isDirectory()){
                listItem.put("icon",R.drawable.folder);
            }else{
                listItem.put("icon",R.drawable.file);
            }
            Log.e("123","文件名"+files[i].getName());
            listItem.put("filename",files[i].getName());
            lists.add(listItem);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,lists,R.layout.listview_files_item,new String[]{"icon","filename"},new int[]{R.id.iv_file,R.id.tv_file_name});
        lv_files.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        try {
            tv_path.setText(currentParent.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
