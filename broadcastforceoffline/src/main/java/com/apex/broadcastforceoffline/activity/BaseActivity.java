package com.apex.broadcastforceoffline.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.apex.broadcastforceoffline.collector.ActivityCollector;

public class BaseActivity extends AppCompatActivity {

    private ForceOfflineReceiver receiver;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        Log.d("BaseActivity",getClass().getSimpleName());
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override
    protected void onResume() {
        //当活动失去栈顶位置就会取消注册
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.apex.broadcastforceoffline.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    class ForceOfflineReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("You are forced to be offline. Please try to login again");
            builder.setCancelable(false);//防止用户关闭对话框继续使用程序
            builder.setPositiveButton("OK", (dialog, which) -> {
                ActivityCollector.finishAll();
                Intent i = new Intent(context, LoginActivity.class);
                context.startActivity(i);
            });
            builder.show();//显示对话框
        }
    }
}
