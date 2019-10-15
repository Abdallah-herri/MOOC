package com.example.mooc.Controllers.Parent.Activities;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mooc.APIClasses.Interfaces.ParentAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.DisplayAdapters.HistoryAdapter;
import com.example.mooc.Entities.History;
import com.example.mooc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;

public class HistoryChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_child_activity);

        ActionBar header = getSupportActionBar();
        Bundle extras = getIntent().getExtras();

        header.setTitle(extras.getString("title"));
        ArrayList<History> historyList = (ArrayList<History>) extras.getSerializable("history");
        int id_student = extras.getInt("id");

        final TextView lastActive = findViewById(R.id.parent_children_history_time);
        final ListView lv_parent_children_list_history = findViewById(R.id.lv_parent_children_list_history);

        RequestManager<ParentAPI> requestManager = new RequestManager<>(ParentAPI.class, this);
        ParentAPI parentApi = requestManager.getService();

        Call<RetrievedData<Integer>> call = parentApi.getChildLastActivity(
            id_student
        );

        requestManager.request(call, new QueryHandler<Integer>()
        {
            @Override
            public void onSuccess(Integer response, Context context)
            {
                String lastActiveAsText = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(new Date(response * 1000L));

                lastActive.setText(lastActiveAsText);
            }
        });

        HistoryAdapter adapter = new HistoryAdapter(this, historyList);
        lv_parent_children_list_history.setAdapter(adapter);
    }
}
