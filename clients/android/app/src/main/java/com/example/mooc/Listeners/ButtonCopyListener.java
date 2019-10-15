package com.example.mooc.Listeners;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonCopyListener implements View.OnClickListener
{
    private Context context;
    private TextView view_text;
    private String info;

    public ButtonCopyListener(Context context, TextView view_text, String info)
    {
        this.context = context;
        this.view_text = view_text;
        this.info = info;
    }

    @Override
    public void onClick(View v)
    {
        String src_text = view_text.getText().toString();
        final ClipboardManager clipboardManager = (ClipboardManager) (context.getSystemService(context.CLIPBOARD_SERVICE));

        // Create a new ClipData.
        ClipData clipData = ClipData.newPlainText(null, src_text);

        // Set it as primary clip data to copy text to system clipboard.
        clipboardManager.setPrimaryClip(clipData);

        if (info != null && info.length() != 0)
        {
            Toast.makeText(context, info, Toast.LENGTH_LONG).show();
        }
    }
}
