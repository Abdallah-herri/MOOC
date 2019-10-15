package com.example.mooc.APIClasses;

import com.google.gson.annotations.SerializedName;

public class RetrievedData<T> {
    @SerializedName("error")
    private boolean error;

    @SerializedName("data")
    private T data;

    @SerializedName("message")
    private String message;

    public boolean isError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
