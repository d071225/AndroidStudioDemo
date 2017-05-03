package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.bitmap;

/**
 * Created by DY on 2017/4/1.
 */

public class AllPictrueActivity extends Activity {

    private ListView lv;
    private Button btn_add;
    private Button btn_view;
    private ContentResolver resolver;
    private List<String> names=new ArrayList<String>();
    private List<String> descs=new ArrayList<String>();
    private List<String> datas=new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn_view = (Button) findViewById(R.id.view);
        btn_add = (Button) findViewById(R.id.add);
        lv = (ListView) findViewById(R.id.show);
        try {
            FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"yasuotupian"+File.separator+"ceshi");
            fos.write("ceshi".getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        resolver = getContentResolver();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME,"jinta");
                values.put(MediaStore.Images.Media.DESCRIPTION,"金塔");
                values.put(MediaStore.Images.Media.MIME_TYPE,"image/jpeg");
                Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jinta);
                try {
                    OutputStream outputStream = resolver.openOutputStream(uri);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"Aiss-141545-com_093.jpg");
//                Log.e("123","图片磁盘大小为:"+file.length()/1024+"KB");
                Cursor cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
                List<Map<String,String>> mapList=new ArrayList<Map<String, String>>();
                while (cursor.moveToNext()){
                    Map<String,String> map=new HashMap<String, String>();
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                    String desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                    byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//                    Bitmap bitmap = BitmapFactory.decodeFile(new String(data, 0, data.length - 1));
//                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                    if (bitmap!=null){
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                    }

//                    if (cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))/1024>28) {
                        names.add(name);
                        descs.add(desc);
                        datas.add(new String(data, 0, data.length - 1));
                        map.put("name", name);
                        map.put("desc", desc);
                        mapList.add(map);
                        Log.e("123","图片大小"+cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))/1024+"KB");
//                    }
                }
                SimpleAdapter adapter=new SimpleAdapter(AllPictrueActivity.this,mapList,R.layout.line,
                        new String[]{"name","desc"},new int[]{R.id.name,R.id.desc});
                lv.setAdapter(adapter);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View dialogItem=View.inflate(AllPictrueActivity.this,R.layout.view,null);
                ImageView picture = (ImageView) dialogItem.findViewById(R.id.image);
//                picture.setImageBitmap(compressImage(BitmapFactory.decodeFile(datas.get(position))));
//                picture.setImageBitmap(compressBitmap(BitmapFactory.decodeFile(datas.get(position)),names.get(position),position));
//                Bitmap bitmap = BitmapFactory.decodeFile(datas.get(position));
//                ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
//                Log.e("123","图片大小："+baos.toByteArray().length/1024+"KB");
//                Bitmap bitmap = BitmapFactory.decodeFile(datas.get(position));
//                Log.e("123","图片大小："+bitmap.getAllocationByteCount()/1024+"KB");
                picture.setImageBitmap(getBitmap(datas.get(position),720,1280,names.get(position)));
//                picture.setImageBitmap(equalRatioScale(datas.get(position),720,1280));
                AlertDialog.Builder builder=new AlertDialog.Builder(AllPictrueActivity.this);
                builder.setView(dialogItem);
                builder.setPositiveButton("确定",null);
                builder.show();
            }
        });
    }

    private Bitmap compressBitmap(Bitmap bitmap,String name,int position) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        if (bitmap!=null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
            Log.e("123","图片大小："+baos.toByteArray().length/1024+"KB");
//            int options = 100;
//            while (baos.toByteArray().length / 1024 > 100) {
//                baos.reset();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//                options -= 10;
//            }
            try {
//                File file=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+File.separator+"yasuotupian"+File.separator+name);
                FileOutputStream fos = new FileOutputStream(new File(name));
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            Bitmap bitmap1 = BitmapFactory.decodeStream(bais);
            return bitmap1;
        }
        return null;
    }
    public Bitmap getBitmap(String bitmapPath,int reqWight,int reqHeight,String name){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        Bitmap bit = BitmapFactory.decodeFile(bitmapPath, options);
        int inSampleSize=1;
//        int wight=options.outWidth;
//        int height=options.outHeight;
//        if (wight>reqWight||height>reqHeight){
//            int weightRadio=wight/reqWight;
//            int heightRadio=height/reqHeight;
//            inSampleSize=weightRadio>heightRadio?weightRadio:heightRadio;
//            Log.e("123","weightRadio:"+weightRadio+",heightRadio:"+heightRadio+",inSampleSize:"+inSampleSize);
//        }
//        Log.e("123","压缩前宽:"+wight+",压缩前高:"+height);
        options.inSampleSize=inSampleSize;
        options.inJustDecodeBounds=false;
        Bitmap bitmap = BitmapFactory.decodeFile(bitmapPath, options);
        Log.e("123","压缩后的图片大小："+bitmap.getAllocationByteCount()/1024+"KB"+"，压缩后宽："+bitmap.getWidth()+"，压缩后高："+bitmap.getHeight());
        writeToFile(bitmap,name);
        return bitmap;
    }
    /**
     * @param path 图片路径
     * @param targetSize 缩放后期待的长边(图片长和宽大的那一个边)的长度
     * @param targetW 期待的缩放后宽度
     * @param targetH 期待的缩放后高度
     * @return
     */
    public static Bitmap equalRatioScale(String path,int targetW,int targetH){
        // 获取option
        BitmapFactory.Options options = new BitmapFactory.Options();
        // inJustDecodeBounds设置为true,这样使用该option decode出来的Bitmap是null，
        // 只是把长宽存放到option中
        options.inJustDecodeBounds = true;
        // 此时bitmap为null
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int inSampleSize = 2; // 1是不缩放
//        // 计算宽高缩放比例
//        int inSampleSizeW = options.outWidth / targetW;
//        int inSampleSizeH = options.outHeight / targetH;
//        // 最终取大的那个为缩放比例，这样才能适配，例如宽缩放3倍才能适配屏幕，而
//        // 高不缩放就可以，那样的话如果按高缩放，宽在屏幕内就显示不下了
//        if (inSampleSizeW > inSampleSizeH) {
//            inSampleSize = inSampleSizeW;
//        }else {
//            inSampleSize = inSampleSizeH;
//        }
//        // 设置缩放比例
        options.inSampleSize = inSampleSize;
        // 一定要记得将inJustDecodeBounds设为false，否则Bitmap为null
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFile(path, options);
        Log.e("123","压缩后的图片大小："+bitmap.getByteCount()/1024+"KB");
        return bitmap;
    }
    private void writeToFile(Bitmap bitmap,String name) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        File fileDic=new File(this.getFilesDir()+File.separator+"saixuan");
        if (!fileDic.exists()){
            fileDic.mkdir();
        }
        try {
            FileOutputStream fos=new FileOutputStream(this.getFilesDir()+File.separator+"saixuan"+File.separator+name);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
