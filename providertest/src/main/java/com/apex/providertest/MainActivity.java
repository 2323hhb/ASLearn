package com.apex.providertest;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private String newBookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(s->{
            ContentResolver contentResolver = getContentResolver();
            Uri uri = Uri.parse("content://com.apex.databasetest.provider/book");

            ContentValues values = new ContentValues();
            values.put("name", "waibuchengxu");
            values.put("author", "lao wai");
//            values.put("pages", 30);
            values.put("price", 6.40);

            Uri newUri = contentResolver.insert(uri, values);
            newBookId = newUri.getPathSegments().get(1);
            Log.d("contentProvider-data", "newBookId: " + newBookId);

            values.clear();
            values.put("name", "waibuchengxu-2");
            values.put("author", "lao wai");
//            values.put("pages", 30);
            values.put("price", 6.40);

            Uri newUri2 = contentResolver.insert(uri, values);
            String newId2 = newUri2.getPathSegments().get(1);
            Log.d("contentProvider-data", "newId2: " + newId2);
        });

        Button btn_query = findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentResolver contentResolver = getContentResolver();
                Uri uri = Uri.parse("content://com.apex.databasetest.provider/book");
                //Cursor cursor = contentResolver.query(uri, new String[]{ "name", "price","author"}, "author=?", new String[]{"lao wai"},null);
                Cursor cursor = contentResolver.query(uri, new String[]{ "id, name", "price", "author"}, null, null,null);

                int index=0;
                if ( cursor != null){
                    while (cursor.moveToNext()) {
                        @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex("price"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));

                        String msg = (++index)+". id:" + id +" | name:" + name + " | author:" + author+ " | price:" + price;

                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Log.d("contentProvider-data", msg);
                    }
                }

                cursor.close(); //记得关闭指针
            }
        });

        //更新数据
        Button btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri uri = Uri.parse("content://com.apex.databasetest.provider/book");

                ContentValues values = new ContentValues();
                values.put("price", 100);  //只更新标明的字段，其它字段值不会被修改
                int updateRows = contentResolver.update(uri, values, "author=?", new String[]{"lao wai"});

                Log.d("contentProvider-data", "影响行数："+updateRows );
            }
        });

        //删除数据
        Button btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentResolver contentResolver = getContentResolver();
                Uri uri = Uri.parse("content://com.apex.databasetest.provider/book");
                int deleteRows = contentResolver.delete(uri, "name=?", new String[]{"waibuchengxu-2"});
                Log.d("contentProvider-data", "book/  影响行数："+ deleteRows );

                Log.d("contentProvider-data", "newBookId: " + newBookId);
                Uri uriItem = Uri.parse("content://com.apex.databasetest.provider/book/" + newBookId);
                int deleteItemRows = contentResolver.delete(uriItem, null, null);

                Log.d("contentProvider-data", "book/id：影响行数："+ deleteItemRows );
            }
        });
    }
}