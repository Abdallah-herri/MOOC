package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deposit implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("course")
    private Course course;

    @SerializedName("student")
    private UserStudent student;

    @SerializedName("files")
    private List<FileDesposit> files = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public UserStudent getStudent() {
        return student;
    }

    public List<FileDesposit> getFiles() {
        return files;
    }
}
