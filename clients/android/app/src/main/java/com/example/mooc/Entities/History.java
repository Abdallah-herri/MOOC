package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class History implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("message")
    private String message;

    @SerializedName("time")
    private int time;

    @SerializedName("student")
    private UserStudent student;

    @SerializedName("course")
    private Course course;

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getTime() {
        return time;
    }

    public UserStudent getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
