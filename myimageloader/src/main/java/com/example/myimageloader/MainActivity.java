package com.example.myimageloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView iv= (ImageView) findViewById(R.id.iamge);
        Button loder= (Button) findViewById(R.id.loder);
        Button clear= (Button) findViewById(R.id.clear);
        final ImagerLoder imagerLoder=new ImagerLoder();
//        final String url="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=图片&step_word=&hs=0&pn=49&spn=0&di=81508173450&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=4195805644%2C827754888&os=520010365%2C3798680275&simid=3348721995%2C380165808&adpicid=0&lpn=0&ln=1984&fr=&fmq=1489371824417_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fdl.bizhi.sogou.com%2Fimages%2F2012%2F03%2F14%2F124196.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fktzit_z%26e3Bf5257_z%26e3Bv54AzdH3F1jpwtsAzdH3Ftgu5AzdH3F8d98lm&gsm=0&rpstart=0&rpnum=0";
        final String url="https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
        loder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagerLoder.displayImage(url,iv);
//                iv.setImageBitmap(imagerLoder.downloadImage(url));
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setImageBitmap(null);
            }
        });
    }
}
