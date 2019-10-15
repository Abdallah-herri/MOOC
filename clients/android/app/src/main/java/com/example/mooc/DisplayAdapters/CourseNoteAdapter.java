package com.example.mooc.DisplayAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mooc.Controllers.Child.Activities.CourseLessonsActivity;
import com.example.mooc.Controllers.Child.Activities.SubjectYearNotesActivity;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.Note;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseNoteAdapter extends ArrayAdapter<String> {

    private Context context;
    private HashMap<String, ArrayList<Note>> marksMap;
    private HashMap<String, Float> marksAvgMap;

    public CourseNoteAdapter(Context context, HashMap<String, ArrayList<Note>> marksMap, HashMap<String, Float> marksAvgMap, ArrayList<String> subjectYearList) {
        super(context, 0, subjectYearList);

        this.marksMap = marksMap;
        this.marksAvgMap = marksAvgMap;
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final String item = getItem(position);
        final Float avg = marksAvgMap.get(item);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_note_course, parent, false);
        }

        final TextView title  = v.findViewById(R.id.child_adapter_note_title);
        final TextView mark   = v.findViewById(R.id.child_adapter_note_value);

        title.setText(item);
        mark.setText(avg.toString());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(context, SubjectYearNotesActivity.class);
                activity.putExtra("marks", marksMap.get(item));
                activity.putExtra("title", item);

                context.startActivity(activity);
            }
        });



        return v;
    }
}
