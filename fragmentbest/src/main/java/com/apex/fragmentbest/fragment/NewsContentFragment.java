package com.apex.fragmentbest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.apex.fragmentbest.R;

public class NewsContentFragment extends Fragment {
    private View view;
    //重写Fragment的onCreateView()方法，在这个方法中通过inflate()将定义的新闻内容布局动态加载进来
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        view=inflater.inflate(R.layout.news_content_frag,container,false);
        return view;
    }

    //将新闻的标题和内容显示在界面上
    public void refresh(String newsTitle,String newsContent){
        //找到新闻内容线性布局id
        View visibilityLayout=view.findViewById(R.id.visibility_layout);
        //设置为布局可见
        visibilityLayout.setVisibility(View.VISIBLE);
        //获取到新闻标题和内容的控件
        TextView newsTitleText=view.findViewById(R.id.news_title);
        TextView newsContentText=view.findViewById(R.id.news_content);
        //将传递进来的标题和内容添加到控件当中去
        newsTitleText.setText(newsTitle);//刷新新闻的标题
        newsContentText.setText(newsContent);//刷新新闻的内容
    }
}

