package com.example.mooc.APIClasses;

import android.app.Dialog;
import android.content.Context;

import com.example.mooc.APIClasses.Adapters.CourseAdapter;
import com.example.mooc.APIClasses.Adapters.UserAdapter;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.User;
import com.example.mooc.Others.ProgressDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager<T> {

    private T service;
    private Context context;

    private static Retrofit retrofit_singleton = null;

    public RequestManager(Class<T> service, Context context)
    {
        if(retrofit_singleton == null)
        {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(new AddHeaderInterceptor(context))
                    .addNetworkInterceptor(new AddHeaderInterceptor(context));

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(User.class, new UserAdapter())
                    .registerTypeAdapter(Course.class, new CourseAdapter())
                    .create();

            retrofit_singleton = new Retrofit.Builder()
                    .baseUrl("http://m1-hmin205.scienceontheweb.net/public/index.php/")
//                    .baseUrl("http://192.168.137.1/MOOC/server/public/index.php/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }

        this.service = retrofit_singleton.create(service);
        this.context = context;
    }

    public T getService()
    {
        return service;
    }

    public<U> void request (final Call<U> call, final AbstractHandler<U> callback) {

        final Dialog dialog = ProgressDialog.getProgressDialog(context);
        dialog.show();

        call.enqueue(new Callback<U>() {

            @Override
            public void onResponse(Call<U> call, Response<U> response)
            {
                dismissDialog();

                callback.onResponse(call, response, context);
            }

            @Override
            public void onFailure(Call<U> call, Throwable t)
            {
                dismissDialog();

                callback.onFailure(call, t, context);
            }

            public void onInseccuessful()
            {
                dismissDialog();

                callback.onInseccuessful(context);
            }

            public void dismissDialog()
            {
                dialog.dismiss();
            }
        });
    }
}
