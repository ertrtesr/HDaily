package com.hwj.hdaily.utils;

import android.widget.Toast;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/2
 */
public class ToastUtils {
    public static void showToast(String text) {
        Toast.makeText(UIUtils.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showToastSafely(final String text) {
        UIUtils.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                showToast(text);
            }
        });
    }
}
