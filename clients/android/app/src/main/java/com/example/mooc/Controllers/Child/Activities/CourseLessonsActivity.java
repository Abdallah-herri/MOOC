package com.example.mooc.Controllers.Child.Activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.mooc.APIClasses.Interfaces.ChildAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.DisplayAdapters.LessonAdapter;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.FileCourse;
import com.example.mooc.R;

import java.util.ArrayList;

import retrofit2.Call;

public class CourseLessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity_course_lessons);
        ActionBar header = getSupportActionBar();
        Bundle extras = getIntent().getExtras();

        header.setTitle(extras.getString("title"));
        int id_course = extras.getInt("course");

        final ListView lv_child_list_lessons = findViewById(R.id.lv_child_list_lessons);

        RequestManager<ChildAPI> requestManager = new RequestManager<>(ChildAPI.class, this);
        ChildAPI childApi = requestManager.getService();

        Call<RetrievedData<Course>> call = childApi.getCourseDetails(
                id_course
        );

        requestManager.request(call, new QueryHandler<Course>() {

            @Override
            public void onSuccess(Course response, Context context) {
                if(response.getFiles().size() == 0)
                {
                    finish();
                    return;
                }

                LessonAdapter adapter = new LessonAdapter(context, (ArrayList<FileCourse>)response.getFiles());
                lv_child_list_lessons.setAdapter(adapter);
            }
        });
    }
}
