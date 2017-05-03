package com.example.imagerloaderdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.imagerloaderdemo.glide.OkHttpProgressGlideModule;
import com.shizhefei.view.largeimage.LargeImageView;

public class MainActivity extends AppCompatActivity {

    private LargeImageView largeImageView;
    private ImageView gifiImageView;
    private TextView size;
    private int y=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://img.tuku.cn/file_big/201502/3d101a2e6cbd43bc8f395750052c8785.jpg";
        String gifUrl = "http://i.kinja-img.com/gawker-media/image/upload/s--B7tUiM5l--/gf2r69yorbdesguga10i.gif";
        largeImageView = (LargeImageView) findViewById(R.id.iv);
        gifiImageView = (ImageView) findViewById(R.id.gif);
        size = (TextView) findViewById(R.id.cache_size);
        size.setText("缓存大小：" + GlideCatchUtil.getInstance().getCacheSize());
        Button clear= (Button) findViewById(R.id.btn);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GlideCatchUtil.getInstance().cleanCatchDisk();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                size.setText("缓存大小：" + GlideCatchUtil.getInstance().getCacheSize());
                            }
                        });

                    }
                }).start();

            }
        });
        int x=y+1;
        Log.e("123", "y的值为" + x);
        for (int i=0;i<10;i++) {
            if (i==2){
                i=y;
            }
            Log.e("123","i的值为"+i);
        }
        final Glide glide = Glide.get(this);
        OkHttpProgressGlideModule a = new OkHttpProgressGlideModule();
        a.registerComponents(this, glide);
        ViewPropertyAnimation.Animator animator=new ViewPropertyAnimation.Animator(){

            @Override
            public void animate(View view) {
//                view.setAlpha(0f);
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view, "rotationX", 0f, 360f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        };
        Glide.with(this).load(gifUrl).asGif().animate(animator).into(gifiImageView);
        Glide.with(this).load(url).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                largeImageView.setImageDrawable(resource);
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(largeImageView, "rotationX", 0f, 360f);
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        });
//        Glide.with(this).load(url).downloadOnly(new ProgressTarget<String, File>(url, null) {
//            @Override
//            public void onProgress(long bytesRead, long expectedLength) {
//
//            }
//
//            @Override
//            public void onResourceReady(File resource, GlideAnimation<? super File> animation) {
//                super.onResourceReady(resource, animation);
//                largeImageView.setImage(new FileBitmapDecoderFactory(resource));
//            }
//
//            @Override
//            public void onLoadFailed(Exception e, Drawable errorDrawable) {
//                super.onLoadFailed(e, errorDrawable);
//                largeImageView.setImage(R.mipmap.ic_launcher);
//            }
//
//            @Override
//            public void getSize(SizeReadyCallback cb) {
//                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//            }
//        });
    }
}
