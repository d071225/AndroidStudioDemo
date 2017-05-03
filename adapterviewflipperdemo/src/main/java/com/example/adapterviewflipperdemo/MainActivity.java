package com.example.adapterviewflipperdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.StackView;

public class MainActivity extends AppCompatActivity {
    private int[] imageIds={R.drawable.welcome_one,R.drawable.welcome_two,R.drawable.welcome_three};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final StackView avf= (StackView) findViewById(R.id.avf);
        Button pre= (Button) findViewById(R.id.pre);
        Button next= (Button) findViewById(R.id.next);
        Button atuo= (Button) findViewById(R.id.atuo);
        avf.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return imageIds.length;
            }

            @Override
            public Object getItem(int position) {
                return imageIds[position%getCount()];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView iv=new ImageView(MainActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                iv.setImageResource(imageIds[position]);
                iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return iv;
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avf.showPrevious();
//                avf.stopFlipping();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avf.showNext();
//                avf.stopFlipping();
            }
        });
        atuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                avf.setFlipInterval(500);
//                avf.startFlipping();
            }
        });
    }

}
