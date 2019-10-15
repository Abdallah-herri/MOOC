package com.example.mooc.DisplayAdapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mooc.Controllers.Child.Activities.CourseLessonsActivity;
import com.example.mooc.Entities.Course;
import com.example.mooc.R;

import java.util.ArrayList;


public class CourseAdapter extends ArrayAdapter<Course> {
    Context context;

    public CourseAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final Course item = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_course, parent, false);
        }

        final TextView title = v.findViewById(R.id.course_title);
        TextView type = v.findViewById(R.id.course_type);

        Button teacher_info = v.findViewById(R.id.course_getTeacher);
        Button read = v.findViewById(R.id.course_read);

        title.setText(item.getTitle());
        type.setText(item.getType().getName());

        teacher_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setMessage(item.getTeacher().getLastName() + " " + item.getTeacher().getFirstName() + "\n" +
                        "email : " + item.getTeacher().getLogin()+ "\n");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "contacter",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(context, CourseLessonsActivity.class);
                activity.putExtra("course", item.getId());
                activity.putExtra("title", item.getTitle());

                context.startActivity(activity);
            }
        });



        return v;
    }
}