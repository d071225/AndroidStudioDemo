package com.migu.androidjdxmdemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv_result;
    private Button btn_search;
    private AutoCompleteTextView autoCompleteTextView;
    private final String DIC_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"dictionary2";
    private final String DIC_NAME="dictionary.db";
    private SQLiteDatabase sqLiteDatabase;
    private final int REQUEST_CODE=0;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.actv);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,games);
//        autoCompleteTextView.setAdapter(adapter);
        openDateBase();
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Cursor cursor=sqLiteDatabase.rawQuery("select english as _id from t_words where english like ?",new String[]{s.toString()+"%"});
                MyCursorAdapter adapter=new MyCursorAdapter(MainActivity.this,cursor,true);
                autoCompleteTextView.setAdapter(adapter);
            }
        });
//        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.e("123","item===>"+parent.getItemAtPosition(position).toString());
//                autoCompleteTextView.setText(parent.getItemAtPosition(position).toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询指定的单词
                String sql = "select chinese from t_words where english=?";
                Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]
                        {autoCompleteTextView.getText().toString()});
                String result = "未找到该单词.";
                //  如果查找单词，显示其中文的意思
                if (cursor.getCount() > 0)
                {
                    //  必须使用moveToFirst方法将记录指针移动到第1条记录的位置
                    cursor.moveToFirst();
                    result = cursor.getString(cursor.getColumnIndex("chinese")).replace("&amp;", "&");
                }
                //将结果显示到TextView中
                tv_result.setText(autoCompleteTextView.getText()+"\n"+result.toString());
            }
        });
    }
    public class MyCursorAdapter extends CursorAdapter{
        public MyCursorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view=View.inflate(context,R.layout.cursor_item,null);
            TextView textView= (TextView) view.findViewById(R.id.tv_content);
            textView.setText(cursor.getString(cursor.getColumnIndex("_id")));
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView textView= (TextView) view.findViewById(R.id.tv_content);
            textView.setText(cursor.getString(cursor.getColumnIndex("_id")));
        }

        @Override
        public CharSequence convertToString(Cursor cursor) {
            return cursor==null?"":cursor.getString(cursor.getColumnIndex("_id"));
        }
    }
    private void openDateBase(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                //已经禁止提示了
                Toast.makeText(MainActivity.this, "您已禁止该权限，需要重新开启。", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);

            }

        } else {
                writeToFile();
        }
    }
    public void writeToFile(){
        filePath = DIC_PATH+ File.separator+DIC_NAME;
        File file=new File(DIC_PATH);
        Log.e("123","file.exists()===>"+file.exists());
        if (!file.exists()){
            Log.e("123","!file.exists()===>");
            file.mkdir();
        }
        if (!(new File(filePath)).exists()) {
            InputStream inputStream = getResources().openRawResource(R.raw.dictionary);
            try {
                FileOutputStream baos = new FileOutputStream(filePath);
                byte[] bytes = new byte[1024];
                int len = 0;

                while ((len = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                baos.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(filePath, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("123","requestCode===>"+requestCode);
        if(requestCode==REQUEST_CODE){
            writeToFile();
        }
    }
}
