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
import com.example.mooc.DisplayAdapters.CourseNoteAdapter;
import com.example.mooc.DisplayAdapters.HistoryAdapter;
import com.example.mooc.Entities.History;
import com.example.mooc.Entities.Note;
import com.example.mooc.Entities.SchoolSubjectYear;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;


public class ChildHistory extends CommonAbstractFragment
{

    Context context;
    ChildAPI childApi;
    UserStudent student;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.child_fragment_history, container, false);
        context = getFragmentContext();
        student = SPHandler.getSavedObjectFromPreference(context, SPHandler.RESSOURCES, SPHandler.USER, UserStudent.class);

        final TextView tv_empty_history = view.findViewById(R.id.tv_child_empty_history);
        final LinearLayout ll_child_history = view.findViewById(R.id.ll_child_history);
        final ListView lv_child_list_history = view.findViewById(R.id.lv_child_list_history);

        RequestManager<ChildAPI> requestManager = new RequestManager<>(ChildAPI.class, getFragmentContext());
        ChildAPI childApi = requestManager.getService();

        Call<RetrievedData<List<History>>> call = childApi.getAllHistory();

        requestManager.request(call, new QueryHandler<List<History>>() {

            @Override
            public void onSuccess(List<History> response, Context context) {
                if(response.size() == 0)
                {
                    ll_child_history.setVisibility(View.INVISIBLE);
                    tv_empty_history.setVisibility(View.VISIBLE);
                    return;
                }

                tv_empty_history.setVisibility(View.INVISIBLE);
                HistoryAdapter adapter = new HistoryAdapter(context, (ArrayList<History>) response);
                lv_child_list_history.setAdapter(adapter);
            }
        });

        return view;
    }
}
