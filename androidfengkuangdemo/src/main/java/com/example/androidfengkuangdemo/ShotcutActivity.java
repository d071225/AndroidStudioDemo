package com.example.androidfengkuangdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

/**
 * Created by DY on 2017/4/17.
 */
public class ShotcutActivity extends Activity {

    private ImageView iv_flower;
    private Animation anim;
    private Animation reverse;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            iv_flower.setAnimation(reverse);
//            set2 = new AnimatorSet();
            set1.playTogether(animator4, animator5, animator6,animator8);
            set1.setDuration(3000).start();
        }
    };
        private AnimationSet set;
    private AnimatorSet set1;
    private ObjectAnimator animator4;
    private ObjectAnimator animator5;
    private ObjectAnimator animator6;
    private ObjectAnimator animator7;
    private AnimatorSet set2;
    private ObjectAnimator animator8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shotcut);
        iv_flower = (ImageView) findViewById(R.id.iv_flower);
//        anim = AnimationUtils.loadAnimation(this, R.anim.anim);
//        reverse = AnimationUtils.loadAnimation(this, R.anim.reverse);
//        set = new AnimationSet(true);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv_flower, "alpha", 1.0f, 0.5f);
        ObjectAnimator animator2 =ObjectAnimator.ofFloat(iv_flower, "scaleX", 1.0f, 0.5f);
        ObjectAnimator animator3 =ObjectAnimator.ofFloat(iv_flower, "scaleY", 1.0f, 0.5f);
        ObjectAnimator animator4 =ObjectAnimator.ofFloat(iv_flower, "rotation", 0, 1800);
        animator5 = ObjectAnimator.ofFloat(iv_flower, "alpha", 0.5f, 1.0f);
        animator6 = ObjectAnimator.ofFloat(iv_flower, "scaleX", 0.5f, 1.0f);
        animator7 = ObjectAnimator.ofFloat(iv_flower, "scaleY", 0.5f, 1.0f);
        animator8 = ObjectAnimator.ofFloat(iv_flower, "rotation", 1800, 0);
        set1 = new AnimatorSet();
        set1.playTogether(animator1, animator2, animator3,animator4);
        set1.setDuration(3000).start();
        set1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                handler.sendEmptyMessage(0);
                set2 = new AnimatorSet();
                set2.playTogether(animator5, animator6, animator7,animator8);
                set2.setDuration(3000).start();
            }
        });
        findViewById(R.id.btn_shotcut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                String title = getResources().getString(R.string.title);
                Parcelable iconResource = Intent.ShortcutIconResource.fromContext(ShotcutActivity.this, R.mipmap.ic_launcher);
                Intent myinIntent=new Intent(ShotcutActivity.this,ShotcutActivity.class);
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME,title);
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,iconResource);
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,myinIntent);
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        iv_flower.startAnimation(set);
//        iv_flower.startAnimation(anim);
//        Timer timer=new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.sendEmptyMessage(0);
//            }
//        },4000);
    }
}
