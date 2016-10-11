package com.hwj.hdaily.utils;

import android.app.Dialog;
import android.content.Context;

import com.hwj.hdaily.dialog.LoadingDialog;

import java.util.HashSet;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public class LoadingUtils {

    public static HashSet<Dialog> mDialogs = new HashSet<>();

    /**
     * 展示loading对话框
     *
     * @param context
     */
    public static void show(Context context) {
        LoadingDialog dialog = new LoadingDialog(context);
        dialog.show();
        mDialogs.add(dialog);
    }

    public static void hide() {
        for (Dialog dialog : mDialogs) {
            dialog.hide();
        }
        mDialogs.clear();
    }
}
