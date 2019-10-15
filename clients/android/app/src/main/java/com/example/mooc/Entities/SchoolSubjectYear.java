package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SchoolSubjectYear implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("school_subject")
    private SchoolSubject school_subject;

    @SerializedName("school_year")
    private SchoolYear school_year;

    @SerializedName("courses")
    private List<Course> courses = new ArrayList<>();

    @SerializedName("assigned_students")
    private List<UserStudent> assigned_students = new ArrayList<>();

    @SerializedName("assigned_teachers")
    private List<UserTeacher> assigned_teachers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public SchoolSubject getSchoolSubject() {
        return school_subject;
    }

    public SchoolYear getSchoolYear() {
        return school_year;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<UserStudent> getAssignedStudents() {
        return assigned_students;
    }

    public List<UserTeacher> getAssignedTeachers() {
        return assigned_teachers;
    }
}
