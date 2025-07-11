package com.apex.aslearn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;

public class ThirdActivity extends BaseActivity implements View.OnClickListener{

    private EditText editText;
//    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        Button testBtn3 = findViewById(R.id.testBtn3);
        Button testBtn4 = findViewById(R.id.testBtn4);
        editText = findViewById(R.id.edit_text1);
//        progressBar = findViewById(R.id.progress_circular);
        testBtn3.setOnClickListener(this);
        testBtn4.setOnClickListener(this);
//        testBtn3.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
//        startActivity(intent);
//        Toast.makeText(this,"返回成功",Toast.LENGTH_SHORT).show();
        if(v.getId() == R.id.testBtn3){
            String id = String.valueOf(v.getId());
            Log.d("Btnid",id);
//            int process = progressBar.getProgress();
//            process = process + 10;
//            progressBar.setProgress(process);
//            if (progressBar.getVisibility() == View.GONE){
//                progressBar.setVisibility(View.VISIBLE);
//            }else {
//                progressBar.setVisibility(View.GONE);
//            }
            String inputText = editText.getText().toString();
            Toast.makeText(ThirdActivity.this,inputText,Toast.LENGTH_SHORT).show();
        }else if( v.getId() == R.id.testBtn4){
            Intent intent = new Intent(ThirdActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this,"返回成功",Toast.LENGTH_SHORT).show();
        }

    }
}