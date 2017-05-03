package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by DY on 2017/4/7.
 */

public class AlarmActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button=new Button(this);
        setContentView(button);
        button.setText("设置闹钟");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                new TimePickerDialog(AlarmActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        PendingIntent pi=PendingIntent.getActivity(AlarmActivity.this,0,new Intent(AlarmActivity.this,AlermDialogActivity.class),0);
                        AlarmManager manager= (AlarmManager) getSystemService(ALARM_SERVICE);
                        Calendar c=Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        c.set(Calendar.HOUR,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        manager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
                        Toast.makeText(AlarmActivity.this,"闹钟设置成功",0).show();
                    }
                },calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),false).show();
            }
        });
    }
}
