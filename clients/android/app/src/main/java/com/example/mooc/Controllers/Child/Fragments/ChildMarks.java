package com.example.mooc.Controllers.Child.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
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
import com.example.mooc.DisplayAdapters.CourseNoteAdapter;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.Note;
import com.example.mooc.Entities.SchoolSubjectYear;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;


public class ChildMarks extends CommonAbstractFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.child_fragment_marks, container, false);

        final TextView tv_empty_marks = view.findViewById(R.id.tv_child_empty_marks);
        final LinearLayout ll_child_marks = view.findViewById(R.id.ll_child_marks);
        final ListView lv_child_list_marks = view.findViewById(R.id.lv_child_list_marks);

        RequestManager<ChildAPI> requestManager = new RequestManager<>(ChildAPI.class, getFragmentContext());
        ChildAPI childApi = requestManager.getService();

        Call<RetrievedData<List<Note>>> call = childApi.getAllNotes();

        requestManager.request(call, new QueryHandler<List<Note>>() {

            @Override
            public void onSuccess(List<Note> response, Context context) {
                if(response.size() == 0)
                {
                    ll_child_marks.setVisibility(View.INVISIBLE);
                    tv_empty_marks.setVisibility(View.VISIBLE);
                    return;
                }

                HashMap<String, ArrayList<Note>> marksMap = new HashMap<>();
                HashMap<String, Float> marksAvgMap = new HashMap<>();
                ArrayList<String> subjectYearList = new ArrayList<>();

                SchoolSubjectYear subjectYear;

                for (Note n : response)
                {
                    subjectYear = n.getCourse().getSubjectYear();
                    String name = subjectYear.getSchoolYear().getName() + " - " + subjectYear.getSchoolSubject().getName();

                    if(!marksMap.containsKey(name))
                    {
                        marksMap.put(name, new ArrayList<Note>());
                        subjectYearList.add(name);
                    }

                    ArrayList<Note> temp = marksMap.get(name);
                    temp.add(n);

                }

                Float avg;

                for (String e : subjectYearList)
                {
                    avg = 0f;

                    for (Note n : marksMap.get(e))
                    {
                        avg += n.getNote();
                    }

                    avg /= marksMap.get(e).size();
                    marksAvgMap.put(e, avg);
                }

                tv_empty_marks.setVisibility(View.INVISIBLE);
                CourseNoteAdapter adapter = new CourseNoteAdapter(context, marksMap, marksAvgMap, subjectYearList);
                lv_child_list_marks.setAdapter(adapter);
            }
        });

        return view;
    }
}
