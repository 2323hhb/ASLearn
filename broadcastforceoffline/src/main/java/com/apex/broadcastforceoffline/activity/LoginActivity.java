package com.apex.broadcastforceoffline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.apex.broadcastforceoffline.R;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LoginActivity extends BaseActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;       ;
    private Button loginButton;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit =  findViewById(R.id.account);
        passwordEdit =  findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        loginButton = findViewById(R.id.login);
        boolean isRemeber = pref.getBoolean("remember_password",false);
        if (isRemeber){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        loginButton.setOnClickListener(s->{
            String account = accountEdit.getText().toString();
            save(String.valueOf(accountEdit));
            String password = passwordEdit.getText().toString();

            if (account.equals("admin") && password.equals("123456")){
                editor = pref.edit();
                if (rememberPass.isChecked()){
                    editor.putBoolean("remember_password",true);
                    editor.putString("account",account);
                    editor.putString("password",password);
                }else {
                    editor.clear();
                }
                editor.apply();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(LoginActivity.this, "账户密码不可用", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void save(String accountEdit){
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer !=null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}