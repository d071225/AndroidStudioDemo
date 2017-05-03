package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 2017/4/6.
 */

public class GroupSendMessageActivity extends Activity {

    private EditText et_to_num;
    private Cursor cursor;
    private List<String> sendList=new ArrayList<String>();
    private TextView tv_select;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        et_to_num = (EditText) findViewById(R.id.et_to_num);
        tv_select = (TextView) findViewById(R.id.tv_select);
        final EditText et_content= (EditText) findViewById(R.id.et_content);
        final SmsManager manager=SmsManager.getDefault();
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PendingIntent pendingIntent=PendingIntent.getActivity(GroupSendMessageActivity.this,0,new Intent(),0);
                manager.sendTextMessage(et_to_num.getText().toString(),null,et_content.getText().toString(),pendingIntent,null);
                Toast.makeText(GroupSendMessageActivity.this,"群发成功",0).show();
            }
        });
        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                View view=View.inflate(GroupSendMessageActivity.this,R.layout.alertdialog_item_word,null);
                final ListView lv= (ListView) view.findViewById(R.id.lv_word);
                final MyAdapter adapter=new MyAdapter();
                lv.setAdapter(adapter);
                new AlertDialog.Builder(GroupSendMessageActivity.this)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                sendList.clear();
                                Log.e("123","checkbox总量："+lv.getCount()+"curser总量："+cursor.getCount());
                                for (int i=0;i<lv.getCount();i++){
                                    CheckBox checkBox= (CheckBox) lv.getAdapter().getView(i,null,lv);
//                                    adapter.notifyDataSetChanged();
                                    Log.e("123","checkbox是否选中："+checkBox.isChecked()+"游标i:"+i+"号码："+checkBox.getText().toString());
//                                    if (checkBox.isChecked()){
//                                        sendList.add(checkBox.getText().toString());
//                                    }
                                }
                                tv_select.setText(sendList.toString());
                            }
                        }).show();
            }
        });
    }
    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("123","===调用getView===");
            cursor.moveToPosition(position);
            final CheckBox cb=new CheckBox(GroupSendMessageActivity.this);
            String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    .replace(" ","").replace("-","");
            cb.setText(number);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.e("123","===调用onCheckedChanged===");
                    if (isChecked){
                        if (!sendList.contains(cb.getText().toString()))
                        sendList.add(cb.getText().toString());
                    }else{
                        sendList.remove(cb.getText().toString());
                    }
                }
            });
//            Log.e("123","===调用cb.isChecked()==="+cb.isChecked());
//            cb.setChecked(true);
            if (isChecked(number)){
                cb.setChecked(true);
            }
            return cb;
        }
    }
    public boolean isChecked(String num){
        for (String number:sendList) {
            if (number.equals(num)){
                return true;
            }
        }
        return false;
    }
}
