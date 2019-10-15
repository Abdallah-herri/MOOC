package com.example.mooc.DisplayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mooc.Entities.History;
import com.example.mooc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HistoryAdapter extends ArrayAdapter<History> {
    Context context;

    public HistoryAdapter(Context context, ArrayList<History> histories) {
        super(context, 0, histories);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final History item = getItem(position);
        String dateAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(item.getTime() * 1000L));

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_history, parent, false);
        }

        final TextView message  = v.findViewById(R.id.history_message);
        final TextView lesson   = v.findViewById(R.id.history_lesson);
        final TextView time   = v.findViewById(R.id.history_time);

        int ressource = context.getResources().getIdentifier(item.getMessage(), "string", context.getPackageName());
        String messageString = context.getString(ressource);

        message.setText(messageString);
        time.setText(dateAsText);
        lesson.setText(item.getCourse().getTitle());

        return v;
    }
}