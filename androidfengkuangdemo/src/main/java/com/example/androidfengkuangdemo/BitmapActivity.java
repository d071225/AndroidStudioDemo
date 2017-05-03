package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by DY on 2017/3/23.
 */

public class BitmapActivity extends Activity{

    private String[] images;
    private int current=0;
    private ImageView iv;
    private InputStream open;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);
        iv = (ImageView) findViewById(R.id.iv);
        final AssetManager manager=getAssets();
        try {
            images = manager.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current>=images.length){
                    current=0;
                }
                Log.e("123","current==>"+current);
                while (!images[current].endsWith(".png")&&!images[current].endsWith(".jpg")&&!images[current].endsWith(".gif")){
                    current++;
                    if (current>=images.length){
                        current=0;
                    }
                }
                try {
                    open = manager.open(images[current++]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BitmapDrawable bitmapDrawable= (BitmapDrawable) iv.getDrawable();
                if (bitmapDrawable!=null&&!bitmapDrawable.getBitmap().isRecycled()){
                    bitmapDrawable.getBitmap().recycle();
                }
                iv.setImageBitmap(BitmapFactory.decodeStream(open));
            }
        });
    }
}
