package com.example.mooc.APIClasses;

import android.content.Context;
import android.widget.Toast;

import com.example.mooc.R;

import retrofit2.Call;
import retrofit2.Response;

public abstract class AbstractHandler<T> {

    public void onResponse(Call<T> call, Response<T> response, Context currentContext) {
        if(!response.isSuccessful())
        {
            onInseccuessful(currentContext);
            return;
        }

    }

    public void onFailure(Call<T> call, Throwable t, Context currentContext) {
        String message = currentContext.getResources().getString(R.string.L_SERVER_DOWN);
        Toast.makeText(currentContext, message,Toast.LENGTH_LONG).show();
    }


    public void onInseccuessful(Context currentContext)
    {
        String message = currentContext.getResources().getString(R.string.L_SERVER_ERROR);
        Toast.makeText(currentContext, message, Toast.LENGTH_LONG).show();
    }
}
