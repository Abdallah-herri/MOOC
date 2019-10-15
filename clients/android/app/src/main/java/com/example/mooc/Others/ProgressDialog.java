package com.example.mooc.Others;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;

import com.example.mooc.R;

public class ProgressDialog {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Dialog getProgressDialog(Context context) {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.progress);

        dialog = builder.create();
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return dialog;
    }


}
