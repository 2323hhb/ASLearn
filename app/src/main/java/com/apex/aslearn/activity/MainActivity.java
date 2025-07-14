package com.apex.aslearn.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.apex.aslearn.R;
import com.google.android.material.tabs.TabLayout;

import java.net.NetworkInterface;


public class MainActivity extends BaseActivity {

    private IntentFilter intentFilter;

    public static final String TAG = "MainActivity";

    private NetworkChangeReceiver networkChangeReceiver;
    //onCreate活动创建前一定会执行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        //R表示资源Resources，这里是引入布局
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        //add action为添加广播接收器想要接收什么样的信息
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
        Button testBtn1 = findViewById(R.id.testBtn1);
        Button testBtn2 = findViewById(R.id.testBtn2);
        Button testBtn3 = findViewById(R.id.testBtn3);
        //注册按钮1监听器
        testBtn1.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
            //传递数据给下一个活动
//        intent.putExtra("extra_data",data);
//        //传递数据给上一个活动
//        startActivityForResult(intent,1);
//        隐式
//        Intent intent = new Intent("com.example.myapplication.ACTION_START").setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.SecondActivity");
//        intent.addCategory("com.example.myapplication.MY_CATEGORY");
//        Intent intent = new Intent(Intent.ACTION_VIEW).setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.ThirdActivity");
//        intent.setData(Uri.parse("www.baidu.com"));
            startActivity(intent);
            Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
        });
        //注册按钮2监听器
        testBtn2.setOnClickListener(v->{
//            String data = "测试Second";
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
//            intent.putExtra("extra_data",data);
            Toast.makeText(this,"进入聊天",Toast.LENGTH_SHORT).show();
        });

        //注册广播发送按钮
        testBtn3.setOnClickListener(v -> {
            Intent intent = new Intent("com.apex.aslearn.broadcast.MY_BROADCAST");
            //需要指定广播的包名
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
            //有序广播
//            sendOrderedBroadcast(intent);
        });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar); // 将 ToolBar 设置为应用的标题栏
//        toolbar.setTitle();
        Log.d("MainActivity","onCreate excuted");
//        Button button=findViewById(R.id.testButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(ButtonActivity.this,"按钮被点击了",Toast.LENGTH_SHORT).show();
//            }
//        });
//
    }

//    public boolean onCreateOptionsMenus(Menu menu){
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }


//    private void onClickButton1(View view) {
////        String data = "测试成功";
//        //显式
//        Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
//        //传递数据给下一个活动
////        intent.putExtra("extra_data",data);
////        //传递数据给上一个活动
////        startActivityForResult(intent,1);
//        //隐式
////        Intent intent = new Intent("com.example.myapplication.ACTION_START").setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.SecondActivity");
////        intent.addCategory("com.example.myapplication.MY_CATEGORY");
////        Intent intent = new Intent(Intent.ACTION_VIEW).setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.ThirdActivity");
////        intent.setData(Uri.parse("www.baidu.com"));
//        startActivity(intent);
//        Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
////        Log.d("MainActivity",data);
//        //处理逻辑
//    }

//    private void onClickButton2(View view) {
//        String data = "测试Second";
//        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//        startActivity(intent);
//        intent.putExtra("extra_data",data);
//        Toast.makeText(this,"取消",Toast.LENGTH_SHORT).show();
//        //处理逻辑
//    }
//
//    @Override
//    public void onClick(View v) {
//            if(v.getId() == R.id.testBtn1){
//                onClickButton1(v);
//            }else if( v.getId() == R.id.testBtn2){
//                onClickButton2(v);
//            }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        //动态广播接收器最后要取消注册！！
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    protected void onPause() {
        Log.d(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    //当网络发生变化时执行
    private class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取当前网络状态（注意：getActiveNetworkInfo() 在 Android 10+ 已弃用）
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            // 检查网络是否可用
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "Network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Network is unavailable", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(context,"network change",Toast.LENGTH_SHORT).show();
        }
    }
}