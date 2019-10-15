package com.example.mooc.Entities;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class CourseType implements Serializable {

    public static final int LESSON = 1;
    public static final int EXERCISE = 2;
    public static final int STREAM = 3;


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("courses")
    private List<Course> courses = new ArrayList<>();

    public CourseType(@Type int season) { }

    @IntDef({LESSON, EXERCISE, STREAM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}