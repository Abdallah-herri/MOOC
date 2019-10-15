package com.example.mooc.APIClasses.Interfaces;

import com.example.mooc.APIClasses.RetrievedData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserAPI {

    @GET("is_connected")
    Call<RetrievedData<Boolean>> isConnected();
}
