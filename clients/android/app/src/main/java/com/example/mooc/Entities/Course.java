package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Course implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("time")
    private int time;

    @SerializedName("teacher")
    private UserTeacher teacher;

    @SerializedName("subject_year")
    private SchoolSubjectYear subject_year;

    @SerializedName("type")
    private CourseType type;

    @SerializedName("files")
    private List<FileCourse> files = new ArrayList<>();

    @SerializedName("planning")
    private Planning planning;

    @SerializedName("history")
    private History history;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getTime() {
        return time;
    }

    public UserTeacher getTeacher() {
        return teacher;
    }

    public SchoolSubjectYear getSubjectYear() {
        return subject_year;
    }

    public CourseType getType() {
        return type;
    }

    public List<FileCourse> getFiles() {
        return files;
    }

    public Planning getPlanning() {
        return planning;
    }
}
