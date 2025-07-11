package com.apex.aslearn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.apex.aslearn.R;


public class MainActivity extends BaseActivity {

    //onCreate活动创建前一定会执行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        //R表示资源Resources，这里是引入布局
        setContentView(R.layout.activity_main);
        Button testBtn1 = findViewById(R.id.testBtn1);
        Button testBtn2 = findViewById(R.id.testBtn2);
        //注册监听器
        testBtn1.setOnClickListener(v ->{
            Intent intent = new Intent(MainActivity.this,ThirdActivity.class);
            //传递数据给下一个活动
//        intent.putExtra("extra_data",data);
//        //传递数据给上一个活动
//        startActivityForResult(intent,1);
            //隐式
//        Intent intent = new Intent("com.example.myapplication.ACTION_START").setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.SecondActivity");
//        intent.addCategory("com.example.myapplication.MY_CATEGORY");
//        Intent intent = new Intent(Intent.ACTION_VIEW).setClassName(/* TODO: provide the application ID. For example: */ getPackageName(), "com.example.myapplication.ThirdActivity");
//        intent.setData(Uri.parse("www.baidu.com"));
            startActivity(intent);
            Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show();
        });
        testBtn2.setOnClickListener(v->{
            String data = "测试Second";
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            intent.putExtra("extra_data",data);
            Toast.makeText(this,"取消",Toast.LENGTH_SHORT).show();
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
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.testBtn1:
//                onClickButton1(v);
//                break;
//            case R.id.testBtn2:
//                onClickButton2(v);
//                break;
//            default:
//                break;
//    }
}