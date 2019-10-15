package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public abstract class File implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("filename")
    private String filename;

    @SerializedName("mime")
    private String mime;

    @SerializedName("time")
    private int time;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFilename() {
        return filename;
    }

    public String getMIME() {
        return mime;
    }

    public int getTime() {
        return time;
    }
}
