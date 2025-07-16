package com.apex.androidthreadtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private static final int UPDATE_TEXT = 1;
    //该方法会导致内存泄露，已经弃用
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler(){
//        @SuppressLint("HandlerLeak")
//        public void handleMessage(Message message){
//            switch (message.what){
//                case UPDATE_TEXT:
//                    textView.setText("Nice to meet u");
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
    //这边是使用Looper异步处理消息
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            //message.what是一个字段
            switch (message.what) {
                case UPDATE_TEXT:
                    textView.setText("Nice to meet u");
                    return true;
                default:
                    return false;
            }
        }
    });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text);
        Button changeButton = findViewById(R.id.changeText);
        changeButton.setOnClickListener(s->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = new Message();
                    message.what = UPDATE_TEXT;
                    handler.sendMessage(message);
                }
            }).start();
        });
    }
}