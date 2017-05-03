package com.example.myimageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by DY on 2017/3/13.
 */
public class ImagerLoder {
    ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private  ImageCache imageCache=new ImageCache();
    private Bitmap mBitmap;
    private Bitmap bitmap;

    public void displayImage(final String url, final ImageView imageView){
        bitmap = imageCache.get(url);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                bitmap =downloadImage(url);
                Log.e("123","bitmap===>"+ bitmap.toString());
                if (bitmap ==null){
                    return;
                }
                if (imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                imageCache.put(url, bitmap);
            }
        });
    }
    public Bitmap downloadImage(final String imageUrl){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(imageUrl);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    mBitmap = BitmapFactory.decodeStream(conn.getInputStream());
//                    BitmapFactory.Options ops=new BitmapFactory.Options();
//                    ops.inJustDecodeBounds=false;
//                    ops.inSampleSize=4;
//                    mBitmap = BitmapFactory.decodeStream(conn.getInputStream(),null,ops);
                    Log.e("123","mBitmap===>"+mBitmap+"conn.getInputStream()===>"+conn.getInputStream().toString());
                    conn.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return mBitmap;
    }
}
