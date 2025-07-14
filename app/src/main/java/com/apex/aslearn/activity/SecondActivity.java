package com.apex.aslearn.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apex.aslearn.R;
import com.apex.aslearn.bean.Msg;
import com.apex.aslearn.view.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends BaseActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter msgAdapter;

//    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        initMsgs();
        inputText = findViewById(R.id.input_text);
        send = findViewById(R.id.send);
        msgRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(msgAdapter);
        send.setOnClickListener(v -> {
            String content = inputText.getText().toString();
            if (!"".equals(content)){
                Msg msg = new Msg(content,Msg.TYPE_SEND);
                msgList.add(msg);
                msgAdapter.notifyItemInserted(msgList.size() - 1);//有新消息刷新显示
                msgRecyclerView.scrollToPosition(msgList.size() - 1);//将msgRecyclerView定位到最后一行
                inputText.setText("");//清空输入框
            }
        });
//        Intent intent = getIntent();
//        String data = intent.getStringExtra("extra_data");
//        Log.d("SecondActivity",data);
//        imageView = findViewById(R.id.imageView);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

    }
    private void initMsgs(){
        Msg msg1 = new Msg("Hell0 David",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?",Msg.TYPE_SEND);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Huang.",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}