package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Note implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("note")
    private float note;

    @SerializedName("course")
    private Course course;

    @SerializedName("student")
    private UserStudent student;

    public int getId() {
        return id;
    }

    public float getNote() {
        return note;
    }

    public Course getCourse() {
        return course;
    }

    public UserStudent getStudent() {
        return student;
    }
}
