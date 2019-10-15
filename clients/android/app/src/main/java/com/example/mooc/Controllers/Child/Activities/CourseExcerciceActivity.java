package com.example.mooc.Controllers.Child.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mooc.DisplayAdapters.ExerciceAdapter;
import com.example.mooc.DisplayAdapters.LessonAdapter;
import com.example.mooc.Entities.FileCourse;
import com.example.mooc.R;

import java.util.ArrayList;

public class CourseExcerciceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_excercice);

        Bundle extras = getIntent().getExtras();
        ArrayList<String> data = extras.getStringArrayList("data");
        ListView lv_child_list_exercice = findViewById(R.id.lv_child_list_exercice);

        ExerciceAdapter adapter = new ExerciceAdapter(this, data);
        lv_child_list_exercice.setAdapter(adapter);
    }
}
