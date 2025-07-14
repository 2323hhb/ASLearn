package com.apex.sharepreferencestest;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;

    private Button restoreButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        saveButton = findViewById(R.id.saveData);
        saveButton.setOnClickListener(s->{
            SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
            editor.putString("name","David");
            editor.putInt("age",56);
            editor.apply();
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();//调用apply方法进行提交
        });
        restoreButton = findViewById(R.id.restoreData);
        restoreButton.setOnClickListener(s->{
            SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
            String name = pref.getString("name","");
            int age = pref.getInt("age",0);
            Log.d("MainActivity","name is "+name);
            Log.d("MainActivity","age is " + age);
        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}