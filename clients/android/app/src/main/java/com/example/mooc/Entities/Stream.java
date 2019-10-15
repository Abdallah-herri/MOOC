package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Stream implements Serializable {

    @SerializedName("course")
    private CourseStream course;

    @SerializedName("state")
    private boolean state;

    public CourseStream getCourse() {
        return course;
    }

    public boolean isState() {
        return state;
    }
}
