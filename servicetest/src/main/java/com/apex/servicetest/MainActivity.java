package com.apex.servicetest;

import static androidx.core.app.ServiceCompat.startForeground;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private MyService.DownlodaBinder downlodaBinder;
    private static final int REQUEST_CODE = 101;

    //该两个方法会在活动与服务绑定与链接断开时候调用，
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downlodaBinder = (MyService.DownlodaBinder) service;
            downlodaBinder.startDownload();
            downlodaBinder.getProcess();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQUEST_CODE);
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button startServiceBtn = findViewById(R.id.start_service);
        Button stopServiveBtn = findViewById(R.id.stop_service);
        Button bindServiceBtn= findViewById(R.id.bind_service);
        Button unbindServiceBtn = findViewById(R.id.unbind_service);
        startServiceBtn.setOnClickListener(s->{
            Intent startServiceIntent = new Intent(this, MyService.class);
            startService(startServiceIntent);
        });
        stopServiveBtn.setOnClickListener(s->{
            Intent stopServiceIntent = new Intent(this, MyService.class);
            stopService(stopServiceIntent);
        });
        bindServiceBtn.setOnClickListener(s->{
            Intent bindIntent = new Intent(this,MyService.class);
            //第三个标志位表示活动与服务绑定后自动创建服务
            bindService(bindIntent,connection,BIND_AUTO_CREATE);
        });
        unbindServiceBtn.setOnClickListener(s->{
            unbindService(connection);
        });


    }
}