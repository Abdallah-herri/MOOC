package com.example.mooc.APIClasses;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Response;

public abstract class QueryHandler<T> extends AbstractHandler<RetrievedData<T>>
{

    @Override
    public void onResponse(Call<RetrievedData<T>> call, Response<RetrievedData<T>> response, Context currentContext)
    {
        super.onResponse(call, response, currentContext);

        if(response.body().isError())
        {
            String package_context = currentContext.getPackageName();
            String recievedMessage = response.body().getMessage();

            int ressource = currentContext.getResources().getIdentifier(recievedMessage, "string", package_context);
            String message = currentContext.getString(ressource);

            Toast.makeText(currentContext, message, Toast.LENGTH_LONG).show();

            return;
        }

       onSuccess(response.body().getData(), currentContext);
    }

    public abstract void onSuccess(T response, Context context);
}
