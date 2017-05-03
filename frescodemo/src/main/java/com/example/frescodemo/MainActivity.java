package com.example.frescodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        String url = "http://ww3.sinaimg.cn/large/610dc034jw1f6m4aj83g9j20zk1hcww3.jpg";
        SimpleDraweeView sdv= (SimpleDraweeView) findViewById(R.id.sdv);
        SimpleDraweeView sdv1= (SimpleDraweeView) findViewById(R.id.sdv1);
        GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder(getResources());
        /**
         * 设置淡入淡出效果的显示时间
         * */
        GenericDraweeHierarchy hierarchy = builder.setFadeDuration(1000)
                .setPlaceholderImage(R.mipmap.ic_launcher)
                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
                .setProgressBarImage(new ProgressBarDrawable()) // 显示进度条（Fresco自带的进度条）
                        .build();
        DraweeController placeHolderDraweeController = Fresco.newDraweeControllerBuilder()
                .setUri("http://avatar.csdn.net/4/E/8/1_y1scp.jpg") //为图片设置url
                .setTapToRetryEnabled(true)   //设置在加载失败后,能否重试
                .setOldController(sdv.getController())
                .build();
        sdv.setController(placeHolderDraweeController);
        sdv.setHierarchy(hierarchy);
        sdv1.setImageURI(url);
//        // 代码设置SimpleDraweeView的属性（会覆盖XML设置的所有属性，即在XML中有在这里没有的属性都会失效）
//        // 注意：一个GenericDraweeHierarchy是不能被多个SimpleDraweeView共用的
//        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
//                .setFadeDuration(3000)
//                .setPlaceholderImage(R.mipmap.ic_launcher)
//                .setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY)
//                .setProgressBarImage(new ProgressBarDrawable()) // 显示进度条（Fresco自带的进度条）
//                .build();
//        // 设置图片圆角
//        RoundingParams roundingParams = new RoundingParams();
//        roundingParams.setRoundAsCircle(false); // 不将图片剪切成圆形
//        roundingParams.setCornersRadius(200);
//        hierarchy.setRoundingParams(roundingParams);
//        // 为SimpleDraweeView设置属性
//        sdv.setHierarchy(hierarchy);
    }
}
