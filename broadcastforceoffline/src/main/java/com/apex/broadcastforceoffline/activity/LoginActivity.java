package com.apex.broadcastforceoffline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.apex.broadcastforceoffline.R;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;
    private EditText passwordEdit;       ;

    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        loginButton.setOnClickListener(s->{
            String account = accountEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            if (account.equals("admin") && password.equals("123456")){
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(LoginActivity.this, "账户密码不可用", Toast.LENGTH_SHORT).show();
            }
        });
    }
}