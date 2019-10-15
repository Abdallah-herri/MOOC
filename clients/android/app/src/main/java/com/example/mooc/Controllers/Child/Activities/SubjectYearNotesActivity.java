package com.example.mooc.Controllers.Child.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mooc.DisplayAdapters.ExerciseNoteAdapter;
import com.example.mooc.Entities.Note;
import com.example.mooc.R;

import java.util.ArrayList;

public class SubjectYearNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity_subject_year_notes);

        ActionBar header = getSupportActionBar();
        Bundle extras = getIntent().getExtras();

        header.setTitle(extras.getString("title"));
        ArrayList<Note> marksList = (ArrayList<Note>) extras.getSerializable("marks");

        final ListView lv_child_list_marks = findViewById(R.id.lv_child_list_marks);

        ExerciseNoteAdapter adapter = new ExerciseNoteAdapter(this, marksList);
        lv_child_list_marks.setAdapter(adapter);
    }
}
