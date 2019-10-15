package com.example.mooc.Others;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.mooc.R;

public abstract class BasicAlertDialog extends AlertDialog.Builder {


    public BasicAlertDialog(@NonNull Context context, String message) {
        super(context);

        this.setTitle(message);
        this.setPositiveButton(context.getResources().getString(R.string.alert_btn_pos), positiveListener());
        this.setNegativeButton(context.getResources().getString(R.string.alert_btn_neg), negativeListener());
    }

    public DialogInterface.OnClickListener positiveListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnPositive();
            }
        };
    }

    public DialogInterface.OnClickListener negativeListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnNegative();
            }
        };
    }

    public abstract void OnPositive();

    public void OnNegative(){
        return;
    };
}
