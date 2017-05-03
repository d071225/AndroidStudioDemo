package com.example.daynighttheme;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by 晓勇 on 2015/8/29 0029.
 */
public class ChangeTheme extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeChangeUtil.changeTheme(this);
        setContentView(R.layout.activity_change);
        Button start= (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv);
        Button mChangeBtn = (Button) findViewById(R.id.btn_change);
        mChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ThemeChangeUtil.isChange) {
                    ThemeChangeUtil.isChange = false;
                    setTheme(R.style.NightTheme);
                } else {
                    ThemeChangeUtil.isChange = true;
                    setTheme(R.style.DayTheme);
                }
//                ChangeTheme.this.recreate();//重新创建当前Activity实例
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mIntent);
        finish();
    }
}
