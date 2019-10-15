package com.example.mooc.Controllers.Parent.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mooc.APIClasses.Interfaces.ParentAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Controllers.Common.CommonAbstractFragment;
import com.example.mooc.DisplayAdapters.StudentAdapter;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class ParentChildren extends CommonAbstractFragment
{
    private View view;
    private Context context;
    private RequestManager<ParentAPI> requestManager;
    private ParentAPI parentApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.parent_fragment_children, container, false);
        context = getFragmentContext();

        requestManager = new RequestManager<>(ParentAPI.class, context);
        parentApi = requestManager.getService();

        getAllChildren();
        Listeners();

        return view;
    }

    public void getAllChildren()
    {
        final TextView tv_parent_empty_children = view.findViewById(R.id.tv_parent_empty_children);
        final LinearLayout ll_parent_children = view.findViewById(R.id.ll_parent_children);
        final ListView lv_parent_list_children = view.findViewById(R.id.lv_parent_list_children);


        Call<RetrievedData<List<UserStudent>>> call = parentApi.getAllChildren();

        requestManager.request(call, new QueryHandler<List<UserStudent>>()
        {
            @Override
            public void onSuccess(List<UserStudent> response, Context context)
            {
                if(response.size() == 0)
                {
                    ll_parent_children.setVisibility(View.INVISIBLE);
                    tv_parent_empty_children.setVisibility(View.VISIBLE);
                    return;
                }

                tv_parent_empty_children.setVisibility(View.GONE);
                StudentAdapter adapter = new StudentAdapter(context, (ArrayList<UserStudent>)response);
                lv_parent_list_children.setAdapter(adapter);
            }
        });

    }

    public void Listeners()
    {
        final EditText et_student_key = view.findViewById(R.id.et_parent_child_key);
        Button btn = view.findViewById(R.id.bt_parent_add_child);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RetrievedData<UserStudent>> call = parentApi.assignParentToChild(
                    et_student_key.getText().toString()
                );

                requestManager.request(call, new QueryHandler<UserStudent>()
                {
                    @Override
                    public void onSuccess(UserStudent response, Context context)
                    {
                        String message = getResources().getString(R.string.parent_child_add_success);
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                        getAllChildren();
                    }
                });
            }
        });
    }

}
