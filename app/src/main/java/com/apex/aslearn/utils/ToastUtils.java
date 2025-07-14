package com.apex.aslearn.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {

    private ToastUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast toast = null;

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        showShort(ApplicationUtils.getInstance(), message);
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
        showShort(ApplicationUtils.getInstance(), message);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        toast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        toast(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        showLong(ApplicationUtils.getInstance(), message);
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
        showLong(ApplicationUtils.getInstance(), message);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        toast(context, message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        toast(context, message, Toast.LENGTH_LONG);
    }

    public static void toast(Context context, int resId, int duration) {
        String message = context.getResources().getString(resId);
        toast(context, message, duration);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void toast(Context context, CharSequence message, int duration) {
        if (!Looper.getMainLooper().isCurrentThread())
            return;
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), message, duration);
        } else {
            toast.setText(message);
        }
        TLog.d(message.toString());
        toast.show();
    }
}
