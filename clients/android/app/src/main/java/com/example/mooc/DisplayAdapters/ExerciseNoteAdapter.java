package com.example.mooc.DisplayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mooc.Controllers.Child.Activities.CourseLessonsActivity;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.Note;
import com.example.mooc.R;

import java.util.ArrayList;


public class ExerciseNoteAdapter extends ArrayAdapter<Note> {
    Context context;

    public ExerciseNoteAdapter(Context context, ArrayList<Note> marks) {
        super(context, 0, marks);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final Note item = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_note_course, parent, false);
        }

        final TextView title  = v.findViewById(R.id.child_adapter_note_title);
        final TextView year   = v.findViewById(R.id.child_adapter_note_value);

        title.setText(item.getCourse().getTitle());
        year.setText(Float.toString(item.getNote()));

        return v;
    }
}