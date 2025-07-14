package com.apex.broadcastforceoffline.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.apex.broadcastforceoffline.R;

public class MainActivity extends BaseActivity {

    private Button offlineButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        offlineButton = findViewById(R.id.forceOffline);
        offlineButton.setOnClickListener(s->{
            Intent intent = new Intent("com.apex.broadcastforceoffline.FORCE_OFFLINE");
            sendBroadcast(intent);
        });

    }
}