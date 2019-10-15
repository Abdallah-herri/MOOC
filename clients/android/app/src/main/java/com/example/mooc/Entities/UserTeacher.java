package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserTeacher extends User {

    @SerializedName("courses")
    private List<Course> courses = new ArrayList<>();

    @SerializedName("supportedStudents")
    private ArrayList<UserStudent> supported_students = new ArrayList<>();

    @SerializedName("assignedCourses")
    private ArrayList<SchoolSubjectYear> assigned_courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public ArrayList<UserStudent> getSupported_students() {
        return supported_students;
    }

    public ArrayList<SchoolSubjectYear> getAssigned_courses() {
        return assigned_courses;
    }
}
