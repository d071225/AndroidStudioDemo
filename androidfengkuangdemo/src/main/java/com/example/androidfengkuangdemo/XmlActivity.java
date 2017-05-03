package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.ChangeTheme;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by DY on 2017/3/20.
 */

public class XmlActivity extends Activity {

    private TextView tv;
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChangeTheme.setNight(this);
        setContentView(R.layout.activity_xml);
        tv = (TextView) findViewById(R.id.tv);
        Button start= (Button) findViewById(R.id.btn);
        Button change= (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ChangeTheme.flag){
                    ChangeTheme.flag=false;
                }else {
                    ChangeTheme.flag=true;
                }
                XmlActivity.this.recreate();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XmlResourceParser xrp=getResources().getXml(R.xml.books);
                StringBuilder sb=new StringBuilder();
                try {
                    while (xrp.getEventType()!= XmlResourceParser.END_TAG){
                        if (xrp.getEventType()==XmlResourceParser.START_TAG){
                            String tagName = xrp.getName();
                            if (tagName.equals("book")){
                                String price = xrp.getAttributeValue(null, "price");
                                sb.append("价格：");
                                sb.append(price);
                                String date=xrp.getAttributeValue(1);
                                sb.append("出版日期："+date);
                                sb.append("书名：");
                                sb.append(xrp.nextText());
                            }
                            sb.append("\n");
                            Log.e("123","解析内容===》"+sb.toString());
                        }
                        xrp.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv.setText(sb.toString());
            }
        });

    }
}
