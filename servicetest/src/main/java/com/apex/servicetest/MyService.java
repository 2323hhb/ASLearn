package com.apex.servicetest;

import static androidx.core.app.ServiceCompat.startForeground;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private DownlodaBinder mbinder = new DownlodaBinder();

    String channelId = "your_channel_id";
    class DownlodaBinder extends Binder{
        public void startDownload(){
            Log.d("MyService","startDownload excute");
        }
        public int getProcess(){
            Log.d("MyService","getProcess excute");
            return 0;
        }
    }
    public MyService() {
    }

    @Override
    public void onDestroy() {
        Log.d("MyService","onDestroy");
        super.onDestroy();
    }

    //每次服务启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand excute");
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    channelId,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_LOW // 使用 IMPORTANCE_LOW 或更高
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }


    @SuppressLint("ForegroundServiceType")
    @Override
    public void onCreate() {
        Log.d("MyService","onCreate excute");
        super.onCreate();
        createNotificationChannel();
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // 创建Intent数组并将单个Intent放入其中
//        Intent[] intentArray = new Intent[]{intent};
        //在 Android 12（API 级别 31）及以上版本中，
        // 创建 PendingIntent 时必须明确指定 FLAG_IMMUTABLE 或 FLAG_MUTABLE 标志,提高应用的安全性。
        PendingIntent activities = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
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
        startForeground(1,notification);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }
}