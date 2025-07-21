package com.apex.actiontest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ; // 也可以


        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button testbtn = findViewById(R.id.test);
        testbtn.setOnClickListener(s->{
            loadAndPrintTestFlows(this);
        });




    }

    public void loadAndPrintTestFlows(Context context) {
//        System.out.println("Absolute path = " + file.getAbsolutePath());

        try {
//            File file = new File(filePath);
//            if (!file.exists()) {
//                TLog.e("XML 文件不存在: " + filePath);
//                return;
//            }

            @SuppressLint("ResourceType")
            InputStream inputStream = context.getResources().openRawResource(R.xml.bsp_system_test);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "null");

            List<TestFlow> testFlowList = new ArrayList<>();
            List<TestFlow> parentStack = new ArrayList<>();
            String prefixActionClass = "";
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("test_flows")) {
                        prefixActionClass = parser.getAttributeValue(null, "prefixActionClass");
                    } else if (parser.getName().equals("test_flow")) {
                        int id = Integer.parseInt(parser.getAttributeValue(null, "id"));
                        String name = parser.getAttributeValue(null, "name");
                        String desc = parser.getAttributeValue(null, "description");
                        String action = parser.getAttributeValue(null, "action");
                        String param = parser.getAttributeValue(null, "param");
                        String timeoutStr = parser.getAttributeValue(null, "timeOut");
                        int timeOut = TextUtils.isEmpty(timeoutStr) ? 0 : Integer.parseInt(timeoutStr);

                        TestFlow flow = new TestFlow(id, name, desc, action, false, TestFlow.Status.PENDING, null, timeOut, param, parentStack.size());

                        // 添加到父级或根列表
                        if (!parentStack.isEmpty()) {
                            parentStack.get(parentStack.size() - 1).addSubFlow(flow);
                        } else {
                            testFlowList.add(flow);
                        }
                        parentStack.add(flow);
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("test_flow")) {
                        parentStack.remove(parentStack.size() - 1);
                    }
                }
                eventType = parser.next();
            }

            inputStream.close();

            // 拼接 resolvedAction
            for (TestFlow root : testFlowList) {
                resolveResolvedAction(root, "");
            }

            // 打印结构
            TLog.i("===== TestFlow 结构树 START =====");
            printTestFlowTree(testFlowList);
            TLog.i("===== TestFlow 结构树 END =====");

        } catch (Exception e) {
            e.printStackTrace();
            TLog.e("解析 XML 异常: " + e.getMessage());
        }
    }

    // 拼接 resolvedAction
    private void resolveResolvedAction(TestFlow flow, String parentPrefix) {
        String action = flow.getAction();
        String resolved;

        if (TextUtils.isEmpty(action)) {
            resolved = parentPrefix;
        } else if (action.contains(".")) {
            resolved = action;
        } else if (!TextUtils.isEmpty(parentPrefix)) {
            resolved = parentPrefix + "." + action;
        } else {
            resolved = action;
        }

        Log.d("ResolvedAction",resolved);
        flow.setResolvedAction(resolved);

        if (flow.getSubFlows() != null) {
            for (TestFlow sub : flow.getSubFlows()) {
                resolveResolvedAction(sub, resolved);
            }
        }
    }

    // 打印结构树
    private void printTestFlowTree(List<TestFlow> flows) {
        for (TestFlow flow : flows) {
            printFlowRecursive(flow, 0);
        }
    }

    private void printFlowRecursive(TestFlow flow, int indent) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < indent; i++) prefix.append("  ");
        TLog.i(String.format("%s• [ID:%d] %s | action=%s | resolved=%s",
                prefix, flow.getId(), flow.getName(),
                flow.getAction(), flow.getResolvedAction()));

        if (flow.getSubFlows() != null) {
            for (TestFlow sub : flow.getSubFlows()) {
                printFlowRecursive(sub, indent + 1);
            }
        }
    }

}