package com.apex.databasetest.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.apex.databasetest.Helper.MyDataBaseHelper;
import com.apex.databasetest.R;

public class MainActivity extends AppCompatActivity {

    private Button createDataButton;
    private Button addDataButton;
    private Button updateDataButton;
    private Button delDataButton;
    private Button queryButton;
    private MyDataBaseHelper dataBaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //更新数据库，传入更大的version，从而让onUpgrade可以得到执行
        dataBaseHelper = new MyDataBaseHelper(this,"BookStore.db",null,2);
        createDataButton = findViewById(R.id.create_database);
        addDataButton = findViewById(R.id.addData);
        updateDataButton = findViewById(R.id.updateData);
        delDataButton = findViewById(R.id.delData);
        queryButton = findViewById(R.id.queryData);
        createDataButton.setOnClickListener(s->{
            //创建更新数据库
            dataBaseHelper.getWritableDatabase();
        });
        addDataButton.setOnClickListener(s->{
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name","Love Can");
            values.put("author","DavidTao");
//            values.put("page",8);
            values.put("price","56");
            db.insert("Book",null,values);
            values.clear();
            values.put("name","Ershier");
            values.put("author","DavidTao2");
//            values.put("page",8);
            values.put("price","18");
            db.insert("Book",null,values);
            Toast.makeText(this, "Insert Data Successed", Toast.LENGTH_SHORT).show();
        });
        updateDataButton.setOnClickListener(s->{
            SQLiteDatabase updb = dataBaseHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price",520);
            updb.update("Book",values,"name = ?",new String[]{"Love Can"});
            Toast.makeText(this, "Update Data Successed", Toast.LENGTH_SHORT).show();
        });
        delDataButton.setOnClickListener(s->{
            SQLiteDatabase deldb = dataBaseHelper.getWritableDatabase();
            deldb.delete("Book","author = ?",new String[]{"DavidTao2"});
            Toast.makeText(this, "Delete Data Successed", Toast.LENGTH_SHORT).show();
        });
        queryButton.setOnClickListener(s->{
            SQLiteDatabase querydb = dataBaseHelper.getWritableDatabase();
            //query有6个参数
            Cursor cursor = querydb.query("Book",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                    Log.d("MainActivity","book name is "+name);
                }while(cursor.moveToNext());
            }
            cursor.close();
        });

    }
}