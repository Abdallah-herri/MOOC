package com.example.mooc.APIClasses;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserStudent;
import com.example.mooc.Others.ProgressDialog;
import com.example.mooc.Others.SPHandler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddHeaderInterceptor implements Interceptor {

    private Context context;

    public AddHeaderInterceptor(Context context)
    {
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        User user = SPHandler.getUser(context);

        Request original = chain.request();
        Response response;


        if(user != null)
        {

            String session_key = user.getSession().get(0).getSessionkey();

            Request request = original.newBuilder()
                    .header("session-key", session_key)
                    .method(original.method(), original.body())
                    .build();

            response = chain.proceed(request);

            return response;
        }

        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .build();

        response = chain.proceed(request);


        return response;

    }
}