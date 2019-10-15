package com.example.mooc.Listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.mooc.R;

public class SharingButtonListener implements View.OnClickListener
{
    private Context context;
    private TextView view_text;
    private String title;

    public SharingButtonListener(Context context, TextView view_text, String title)
    {
        this.context = context;
        this.view_text = view_text;
        this.title = title;
    }

    @Override
    public void onClick(View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        String content = view_text.getText().toString();
        String info = context.getResources().getString(R.string.share_via);

        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(sharingIntent, info));
    }
}
