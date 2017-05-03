package com.example.androidfengkuangdemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by DY on 2017/3/16.
 */

public class ActivityDialog extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        CalendarView cv= (CalendarView) findViewById(R.id.cv);
        DatePicker dp= (DatePicker) findViewById(R.id.dp);
        Button select= (Button) findViewById(R.id.select);
        Button btn= (Button) findViewById(R.id.btn);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(ActivityDialog.this,"你选择的是"+year+"年"+month+"月"+dayOfMonth+"日",0).show();
            }
        });
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(ActivityDialog.this,"你选择的是"+year+"年"+monthOfYear+"月"+dayOfMonth+"日",0).show();
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog= new DatePickerDialog(ActivityDialog.this,null,2017,03,15);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });
    }
}
