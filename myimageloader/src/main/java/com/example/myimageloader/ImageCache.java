package com.example.myimageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by DY on 2017/3/13.
 */
public class ImageCache {
    private LruCache<String,Bitmap> mImageCache;
    public ImageCache(){
        initImageCache();
    }

    private void initImageCache() {
        int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        int cacheMemory=maxMemory/4;
        mImageCache=new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }
    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }
    public Bitmap get(String url){
        return mImageCache.get(url);
    }
}
