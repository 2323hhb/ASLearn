package com.apex.aslearn.utils;

import android.content.Context;
import android.os.Handler;


/**
 * @ClassName: ApplicationUtils
 * @Description: java类作用描述
 * @Author: chentao
 * @CreateDate: 2023/9/13
 * @UpdateUser: updater
 * @UpdateDate: 2023/9/13
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */
public class ApplicationUtils {

    private static Context sInstance;

    private static Handler sHandler;

    public static void initInstance(Context context) {
        sInstance = context;
        sHandler = new Handler();
    }

    public static Context getInstance() {
        return sInstance;
    }

    public static Handler getHandler() {
        return sHandler;
    }
}
