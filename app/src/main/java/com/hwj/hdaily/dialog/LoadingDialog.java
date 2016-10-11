package com.hwj.hdaily.dialog;

import android.app.Dialog;
import android.content.Context;

import com.hwj.hdaily.R;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingStyle);
        setContentView(R.layout.dialog_loading);
    }
}
