package com.example.mooc.Controllers.Guest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mooc.APIClasses.Interfaces.UserAPI;
import com.example.mooc.APIClasses.QueryHandler;
import com.example.mooc.APIClasses.RequestManager;
import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Controllers.Child.ChildMainActivity;
import com.example.mooc.Controllers.Parent.ParentMainActivity;
import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserParent;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Entities.UserType;
import com.example.mooc.Others.SPHandler;
import com.example.mooc.R;

import retrofit2.Call;

public class StartActivity extends AppCompatActivity {

    User user;

    RequestManager<UserAPI> userRequestManager;
    UserAPI userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        user = SPHandler.getUser(this);

        userRequestManager = new RequestManager<>(UserAPI.class, this);
        userApi = userRequestManager.getService();

        if(user != null)
        {
            Call<RetrievedData<Boolean>> call = userApi.isConnected();

            userRequestManager.request(call, new QueryHandler<Boolean>() {

                @Override
                public void onSuccess(Boolean response, Context context) {
                    Intent intent;

                    if(!response)
                    {
                        goToGuest();
                    }

                    if (user.getType().getId() == UserType.STUDENT)
                    {
                        intent = new Intent(getApplicationContext(), ChildMainActivity.class);
                    }
                    else
                    {
                        intent = new Intent(getApplicationContext(), ParentMainActivity.class);
                    }

                    startActivity(intent);
                }
            });
        }
        else
        {
            goToGuest();
        }
    }

    public void goToGuest() {
        SPHandler.clearSharedPreferences(this, SPHandler.RESSOURCES);

        Intent intent = new Intent(getApplicationContext(), GuestMainActivity.class);
        startActivity(intent);
    }

}
