package com.apex.fragmentbest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.apex.fragmentbest.R;
import com.apex.fragmentbest.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        String newsTitle=getIntent().getStringExtra("news_title");//获取传入的新闻标题
        String newsContent=getIntent().getStringExtra("news_content");//获取传入的新闻内容
        //在活动中调用碎片的方法，能够在活动中得到相应碎片的实例，然后就可以调用碎片里面的方法
        //这里是获得了碎片单页模式碎片的实例
        //getFragmentManager()所得到的是所在fragment 的父容器的管理器
        NewsContentFragment newsContentFragment=(NewsContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        //刷新NewsContentFragment界面
        newsContentFragment.refresh(newsTitle,newsContent);
    }

    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
        context.startActivity(intent);
    }
}