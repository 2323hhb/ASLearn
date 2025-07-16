package com.apex.notificationtest;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
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


    //  Android 8.0（API 级别 26）开始，所有通知都必须属于一个通知渠道（Notification Channel），
    //  每个渠道可以单独配置声音、震动、LED 灯等行为，用户也可以在系统设置中针对特定渠道进行控制。
    //  一般使用字符串定义
    String channelId = "your_channel_id";

    private static final int REQUEST_CODE = 101;//区分多个权限请求

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


        Button sendNoticeBtn = findViewById(R.id.send_notice);
        sendNoticeBtn.setOnClickListener(s->{
            Intent intent = new Intent(this,NotificationActivity.class);
            // 创建Intent数组并将单个Intent放入其中
            Intent[] intentArray = new Intent[]{intent};
            //在 Android 12（API 级别 31）及以上版本中，
            // 创建 PendingIntent 时必须明确指定 FLAG_IMMUTABLE 或 FLAG_MUTABLE 标志,提高应用的安全性。
            PendingIntent activities = PendingIntent
                    .getActivities(this, 0, intentArray, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.POST_NOTIFICATIONS},
                            REQUEST_CODE);
                }
            }
            //新特性
            Context context = getApplicationContext(); // 或者在Activity中直接使用this
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.android)
                    .setContentTitle("This is content titile")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("According to the information from Moji Weather, Fuzhou is cloudy today. The lowest temperature is 27℃, and the highest can reach 37℃. The air quality is excellent, with a PM2.5 index of 23, and the humidity is 60%. It is blowing a southeast breeze at level 3."))
                    .setContentText("This is content Text")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.android))
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(activities)
                    .setAutoCancel(true)//点开后图标会消失
                    .setPriority(NotificationCompat.PRIORITY_MAX);
            // 构建通知对象
            Notification notification = builder.build();
            // 添加通知ID（例如1）
            manager.notify(1, notification);
        });


    }
}