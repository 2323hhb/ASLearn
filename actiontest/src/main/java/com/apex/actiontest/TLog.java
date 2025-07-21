package com.apex.actiontest;

import android.text.TextUtils;
import android.util.Log;

/**
 * @ClassName: TLog
 * @Description: java类作用描述
 * @Author: chentao
 * @CreateDate: 2023/9/13
 * @UpdateUser: updater
 * @UpdateDate: 2023/9/13
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */
public class TLog {
    private static String className;
    private static String methodName;
    private static int lineNumber;

    private TLog() {

    }

    public static void e(String message) {
        getMethdNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }

    public static void e(String message, Object... args) {
        getMethdNames(new Throwable().getStackTrace());
        Log.e(className, createLog(String.format(message, args)));
    }

    public static void w(String message) {
        getMethdNames(new Throwable().getStackTrace());
        Log.w(className, createLog(message));
    }

    public static void w(String message, Object... args) {
        getMethdNames(new Throwable().getStackTrace());
        Log.w(className, createLog(String.format(message, args)));
    }

    public static void i() {
        getMethdNames(new Throwable().getStackTrace());
        Log.i(className, createLog(""));
    }

    public static void i(String message) {
        getMethdNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static void i(String message, Object... args) {
        getMethdNames(new Throwable().getStackTrace());
        Log.i(className, createLog(String.format(message, args)));
    }

    public static void d(String message) {
        getMethdNames(new Throwable().getStackTrace());
        Log.d(className, createLog(message));
    }

    public static void d(String message, Object... args) {
        getMethdNames(new Throwable().getStackTrace());
        Log.d(className, createLog(String.format(message, args)));
    }

    public static void v(String message) {
        getMethdNames(new Throwable().getStackTrace());
        Log.v(className, createLog(message));
    }

    public static void v(String message, Object... args) {
        getMethdNames(new Throwable().getStackTrace());
        Log.v(className, createLog(String.format(message, args)));
    }

    private static String createLog(String log) {
        if (TextUtils.isEmpty(log)) {
            return "(" + methodName + ":" + lineNumber + ") => " + methodName + "()";
        } else {
            return "(" + methodName + ":" + lineNumber + ") => " + log;
        }
    }

    private static void getMethdNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }
}
