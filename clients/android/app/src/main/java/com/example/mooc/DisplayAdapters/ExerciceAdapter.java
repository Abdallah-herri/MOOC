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


public class ExerciceAdapter extends ArrayAdapter<String> {
    Context context;

    public ExerciceAdapter(Context context, ArrayList<String> marks) {
        super(context, 0, marks);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final String item = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_exercice, parent, false);
        }

        final TextView desc  = v.findViewById(R.id.exercice_desc);

        desc.setText(item);

        return v;
    }
}