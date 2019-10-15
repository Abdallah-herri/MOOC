package com.example.mooc.APIClasses;

import android.content.Context;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public abstract class FileHandler extends AbstractHandler<ResponseBody>
{

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response, Context currentContext) {
        super.onResponse(call, response, currentContext);

        onSuccess(response.body(), currentContext);
    }

    public abstract void onSuccess(ResponseBody response, Context context);
}
