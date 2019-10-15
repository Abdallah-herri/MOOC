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

import com.example.mooc.APIClasses.FileHandler;
import com.example.mooc.APIClasses.Interfaces.ChildAPI;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.Controllers.Child.Activities.CourseExcerciceActivity;
import com.example.mooc.Controllers.Child.Activities.CourseLessonsActivity;
import com.example.mooc.Controllers.Others.PDFViewer;
import com.example.mooc.Entities.FileCourse;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


public class LessonAdapter extends ArrayAdapter<FileCourse> {
    Context context;

    public LessonAdapter(Context context, ArrayList<FileCourse> files) {
        super(context, 0, files);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final FileCourse item = getItem(position);
        final int id_lesson = item.getId();

        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.child_adapter_lesson, parent, false);
        }

        final TextView title  = v.findViewById(R.id.lesson_title);

        Button read = v.findViewById(R.id.course_read);

        title.setText(item.getName());

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestManager<ChildAPI> requestManager = new RequestManager<>(ChildAPI.class, context);
                ChildAPI childApi = requestManager.getService();

                Call<ResponseBody> call = childApi.getCourseFile(
                        id_lesson
                );

                requestManager.request(call, new FileHandler() {
                    @Override
                    public void onSuccess(ResponseBody response, Context context) {
                        if(response.contentType().toString().compareTo("application/json") == 0) {
                            Intent activity = new Intent(context, CourseExcerciceActivity.class);

                            try {
                                String str_data = response.string();
                                final Gson gson = new Gson();
                                ArrayList<String> data = gson.fromJson(str_data, ArrayList.class);
                                activity.putExtra("data", data);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            context.startActivity(activity);
                        }
                        else {
                            Intent activity = new Intent(context, PDFViewer.class);
                            try {
                                activity.putExtra("course", response.bytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            context.startActivity(activity);
                        }


                    }
                });
            }
        });

        return v;
    }
}