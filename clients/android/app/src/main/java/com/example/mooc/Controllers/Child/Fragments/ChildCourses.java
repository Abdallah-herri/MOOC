package com.example.mooc.Controllers.Child.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mooc.APIClasses.Interfaces.ChildAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Controllers.Common.CommonAbstractFragment;
import com.example.mooc.DisplayAdapters.CourseAdapter;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class ChildCourses extends CommonAbstractFragment
{

    Context context;
    UserStudent student;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.child_fragment_crourses, container, false);
        context = getFragmentContext();
        student = SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USER, UserStudent.class);

        getCourses(view);

        return view;
    }


    public void getCourses (View v)
    {

        final TextView tv_empty_courses = v.findViewById(R.id.tv_child_empty_courses);
        final LinearLayout ll_child_courses = v.findViewById(R.id.ll_child_courses);
        final ListView lv_child_list_courses = v.findViewById(R.id.lv_child_list_courses);


        RequestManager<ChildAPI> requestManager = new RequestManager<>(ChildAPI.class, context);
        ChildAPI childApi = requestManager.getService();


        Call<RetrievedData<List<Course>>> call = childApi.getAllCourses();

        requestManager.request(call, new QueryHandler<List<Course>>() {

            @Override
            public void onSuccess(List<Course> response, Context context) {
                if(response.size() == 0)
                {
                    ll_child_courses.setVisibility(View.INVISIBLE);
                    tv_empty_courses.setVisibility(View.VISIBLE);
                    return;
                }

                tv_empty_courses.setVisibility(View.INVISIBLE);
                CourseAdapter adapter = new CourseAdapter(context, (ArrayList<Course>)response);
                lv_child_list_courses.setAdapter(adapter);
            }
        });
    }


}
