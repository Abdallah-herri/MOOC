package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentSupport implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("year")
    private int year;

    @SerializedName("teacher")
    private UserTeacher teacher;

    @SerializedName("student")
    private UserStudent student;

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public UserTeacher getTeacher() {
        return teacher;
    }

    public UserStudent getStudent() {
        return student;
    }
}
