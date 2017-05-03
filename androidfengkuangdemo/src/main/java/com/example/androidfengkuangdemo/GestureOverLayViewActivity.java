package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DY on 2017/3/30.
 */

public class GestureOverLayViewActivity extends Activity {

    private GestureLibrary gestureLibrary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestureoverlayview);
        GestureOverlayView gestureOverlayView= (GestureOverlayView) findViewById(R.id.gesture);
        gestureOverlayView.setGestureColor(Color.RED);
        gestureOverlayView.setGestureStrokeWidth(4);
        gestureLibrary = GestureLibraries.fromFile(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"myGesture");
        if (gestureLibrary.load()){
            Toast.makeText(this,"手势文件装载成功",0).show();
        }else{
            Toast.makeText(this,"手势文件装载失败",0).show();
        }
        gestureOverlayView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
                //保存手势
//                View view=View.inflate(GestureOverLayViewActivity.this,R.layout.alertdialog_item,null);
//                ImageView iv_gesture= (ImageView) view.findViewById(R.id.iv_gesture);
//                final EditText et_name= (EditText) view.findViewById(R.id.et_name);
//                Bitmap bitmap = gesture.toBitmap(128, 128, 10, Color.RED);
//                iv_gesture.setImageBitmap(bitmap);
//                AlertDialog.Builder builder=new AlertDialog.Builder(GestureOverLayViewActivity.this);
//                builder.setView(view);
//                builder.setPositiveButton("保存", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        GestureLibrary gestureLibrary=GestureLibraries.fromFile(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"myGesture");
//                        gestureLibrary.addGesture(et_name.getText().toString(),gesture);
//                        gestureLibrary.save();
//                    }
//                });
//                builder.setNegativeButton("取消",null);
//                builder.show();
//                //识别手势
                ArrayList<Prediction> recognize = gestureLibrary.recognize(gesture);
                ArrayList<String> result=new ArrayList<String>();
                for (int i=0;i<recognize.size();i++){
                    Log.e("123","与手势【"+recognize.get(i).name+"】相识度为:"+recognize.get(i).score);
                    if (recognize.get(i).score>2.0){
                        result.add("与手势【"+recognize.get(i).name+"】相识度为:"+recognize.get(i).score);
                    }
                }
                if (result.size()>0){
                    ArrayAdapter<Object> adapter=new ArrayAdapter<Object>(GestureOverLayViewActivity.this,android.R.layout.simple_dropdown_item_1line,result.toArray());
                    new AlertDialog.Builder(GestureOverLayViewActivity.this).setAdapter(adapter,null)
                            .setPositiveButton("确定",null).show();
                }else{
                    Toast.makeText(GestureOverLayViewActivity.this,"没有找到匹配的手势",0).show();
                }
            }
        });
    }
}
