package com.example.mooc.Controllers.Parent.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mooc.APIClasses.Interfaces.ParentAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Controllers.Parent.ParentMainActivity;
import com.example.mooc.DisplayAdapters.HistoryAdapter;
import com.example.mooc.DisplayAdapters.SchoolSubjectYearAdapter;
import com.example.mooc.Entities.History;
import com.example.mooc.Entities.SchoolSubjectYear;
import com.example.mooc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

public class CourseChoiceActivity extends AppCompatActivity {

    private ListView lv_parent_course_list;
    private Button submit;
    private ArrayList<Integer> chosenSubjects;
    private RequestManager<ParentAPI> requestManager;
    private ParentAPI parentApi;
    private int id_student;
    private SchoolSubjectYearAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_course_offer_activity);

        ActionBar header = getSupportActionBar();
        Bundle extras = getIntent().getExtras();

        header.setTitle(extras.getString("title"));
        id_student = extras.getInt("id");
        chosenSubjects = new ArrayList<>();

        lv_parent_course_list = findViewById(R.id.lv_parent_course_list);
        submit = findViewById(R.id.btn_parent_course_list);

        requestManager = new RequestManager<>(ParentAPI.class, this);
        parentApi = requestManager.getService();

        Call<RetrievedData<List<SchoolSubjectYear>>> call = parentApi.getAllSubjects();

        requestManager.request(call, new QueryHandler<List<SchoolSubjectYear>>()
        {
            @Override
            public void onSuccess(List<SchoolSubjectYear> response, Context context)
            {
                adapter = new SchoolSubjectYearAdapter(context, android.R.layout.simple_list_item_multiple_choice, (ArrayList<SchoolSubjectYear>) response, chosenSubjects);
                lv_parent_course_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv_parent_course_list.setAdapter(adapter);
                lv_parent_course_list.setItemsCanFocus(true);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenSubjects.size() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Choisissez au moins un élément", Toast.LENGTH_LONG).show();
                    return;
                }

                Call<RetrievedData<Boolean>> call = parentApi.subscribeChild(
                        id_student,
                        adapter.getChosenSubjects()
                );

                requestManager.request(call, new QueryHandler<Boolean>()
                {
                    @Override
                    public void onSuccess(Boolean response, Context context)
                    {
                        Toast.makeText(context, "Inscription effectué avec succès", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(context, ParentMainActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
        });



    }
}
