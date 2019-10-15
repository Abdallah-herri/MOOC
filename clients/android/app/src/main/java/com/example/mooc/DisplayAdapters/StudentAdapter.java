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

import com.example.mooc.APIClasses.Interfaces.ParentAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Controllers.Child.Activities.CourseLessonsActivity;
import com.example.mooc.Controllers.Parent.Activities.CourseChoiceActivity;
import com.example.mooc.Controllers.Parent.Activities.HistoryChildActivity;
import com.example.mooc.Entities.History;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class StudentAdapter extends ArrayAdapter<UserStudent> {
    Context context;

    public StudentAdapter(Context context, ArrayList<UserStudent> users) {
        super(context, 0, users);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        final UserStudent item = getItem(position);
        final String fullnameString = item.getFirstName() + " "+item.getLastName();
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.parent_adapter_child, parent, false);
        }

        final TextView fullname  = v.findViewById(R.id.tv_parent_child_fullname);
        final Button details = v.findViewById(R.id.bt_parent_child_add_course);
        final Button history = v.findViewById(R.id.bt_parent_child_follow);

        fullname.setText(fullnameString);

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseChoiceActivity.class);

                intent.putExtra("title", fullnameString);
                intent.putExtra("id", item.getId());

                context.startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestManager<ParentAPI> requestManager = new RequestManager<>(ParentAPI.class, context);
                ParentAPI parentApi = requestManager.getService();

                Call<RetrievedData<List<History>>> call = parentApi.getChildHistory(
                        item.getId()
                );

                requestManager.request(call, new QueryHandler<List<History>>()
                {
                    @Override
                    public void onSuccess(List<History> response, Context context)
                    {
                        if(response.size() == 0)
                        {
                            Toast.makeText(context, context.getResources().getString(R.string.parent_child_empty_history), Toast.LENGTH_LONG).show();
                            return;
                        }

                        Intent intent = new Intent(context, HistoryChildActivity.class);

                        intent.putExtra("title", fullnameString);
                        intent.putExtra("id", item.getId());
                        intent.putExtra("history", (ArrayList<History>) response);

                        context.startActivity(intent);
                    }
                });
            }
        });
        return v;
    }

}