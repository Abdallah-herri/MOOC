package com.example.mooc.DisplayAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mooc.Entities.SchoolSubjectYear;
import com.example.mooc.R;

import java.util.ArrayList;

public class SchoolSubjectYearAdapter extends ArrayAdapter<SchoolSubjectYear> {
    private Context context;
    private ArrayList<Integer> chosenSubjects;

    public SchoolSubjectYearAdapter(Context context, int ressource, ArrayList<SchoolSubjectYear> histories, ArrayList<Integer> chosenSubjects) {
        super(context, ressource, histories);

        this.context = context;
        this.chosenSubjects = chosenSubjects;
    }

    public ArrayList<Integer> getChosenSubjects () {
        return chosenSubjects;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final SchoolSubjectYear item = getItem(position);

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.parent_adapter_course, parent, false);
        }

        final TextView year  = v.findViewById(R.id.parent_course_title);

        year.setText(item.getSchoolYear().getName() + " - " + item.getSchoolSubject().getName());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = v.findViewById(R.id.parent_course_checkbox);
                Integer value = new Integer(item.getId());

                if(cb.isChecked())
                {
                    cb.setChecked(false);
                    chosenSubjects.remove(value);
                }
                else
                {
                    cb.setChecked(true);
                    chosenSubjects.add(value);
                }

            }
        });
        return v;
    }
}