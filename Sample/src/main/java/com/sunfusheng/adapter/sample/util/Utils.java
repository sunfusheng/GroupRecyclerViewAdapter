package com.sunfusheng.adapter.sample.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author sunfusheng on 2018/2/3.
 */
public class Utils {

    private static Toast toast;

    public static void toast(Context context, @StringRes int resId) {
        if (-1 == resId || 0 == resId) {
            return;
        }
        toastIgnoreEmpty(context, context.getString(resId), true);
    }

    public static void toast(Context context, String msg) {
        toastIgnoreEmpty(context, msg, true);
    }

    public static void toastLong(Context context, @StringRes int resId) {
        if (-1 == resId || 0 == resId) {
            return;
        }
        toastIgnoreEmpty(context, context.getString(resId), false);
    }

    public static void toastLong(Context context, String msg) {
        toastIgnoreEmpty(context, msg, false);
    }

    private static void toastIgnoreEmpty(Context context, String msg, boolean isShort) {
        if (!TextUtils.isEmpty(msg)) {
            toast(context, msg, isShort);
        }
    }

    @SuppressLint("ShowToast")
    public static void toast(Context context, String msg, boolean isShort) {
        int duration = isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
