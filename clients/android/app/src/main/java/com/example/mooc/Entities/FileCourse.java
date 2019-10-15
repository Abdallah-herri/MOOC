package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

public class FileCourse extends File{

    @SerializedName("course")
    private Course course;

    public Course getCourse() {
        return course;
    }
}
