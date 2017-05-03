package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by DY on 2017/3/28.
 */

public class FileActivity extends Activity{
    private final static String FILENAME="ceshi1.text";
    private EditText write_tv;
    private EditText read_tv;
    private File externalStorageDirectory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        write_tv = (EditText) findViewById(R.id.write_tv);
        read_tv = (EditText) findViewById(R.id.read_tv);
        externalStorageDirectory = Environment.getExternalStorageDirectory();
        findViewById(R.id.write).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFile(write_tv.getText().toString());
            }
        });
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read_tv.setText(readFile());
            }
        });

    }

    private String readFile() {
        try {
//            FileInputStream fis=new FileInputStream(externalStorageDirectory.getAbsolutePath()+File.separator+FILENAME);
//            FileInputStream fis=new FileInputStream(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"yasuotupian"+File.separator+FILENAME);
            FileInputStream fis=new FileInputStream(this.getFilesDir()+File.separator+"yasuotupian"+File.separator+FILENAME);
            byte[] bytes=new byte[1024];
            int len=0;
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            while ((len=fis.read(bytes))!=-1){
                baos.write(bytes,0,len);
            }
            return baos.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void writeFile(String s) {
        try {
//            FileOutputStream fos=new FileOutputStream(externalStorageDirectory.getAbsolutePath()+ File.separator+FILENAME);
//            FileOutputStream fos=new FileOutputStream(this.getCacheDir()+FILENAME);
//            FileOutputStream fos=new FileOutputStream(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"yasuotupian"+File.separator+FILENAME);
//            FileOutputStream fos=new FileOutputStream(this.getCacheDir()+File.separator+"yasuotupian"+File.separator+FILENAME);
            File file=new File(this.getFilesDir()+File.separator+"yasuotupian");
            if (!file.exists()){
                file.mkdir();
            }
            File zfile=new File(this.getFilesDir()+File.separator+"yasuotupian"+File.separator+FILENAME);
            FileOutputStream fos=new FileOutputStream(zfile);
            fos.write(s.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
