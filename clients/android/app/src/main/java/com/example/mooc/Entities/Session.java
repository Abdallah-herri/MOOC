package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Session implements Serializable {

    @SerializedName("session_key")
    private String session_key;

    @SerializedName("time")
    private int time;

    public String getSessionkey() {
        return session_key;
    }

    public int getTime() {
        return time;
    }
}
