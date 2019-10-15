package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserStudent extends User implements Serializable {

    @SerializedName("student_key")
    private String student_key;

    @SerializedName("completed_exercise")
    private List<Deposit> completed_exercises = new ArrayList<>();

    @SerializedName("notes")
    private List<Note> notes = new ArrayList<>();

    @SerializedName("assigned_teacher")
    private List<StudentSupport> assigned_teacher = new ArrayList<>();

    @SerializedName("parents")
    private List<UserParent> parents = new ArrayList<>();

    @SerializedName("assigned_courses")
    private List<SchoolSubjectYear> assigned_courses = new ArrayList<>();

    public String getStudentKey() { return student_key; }

    public List<Deposit> getCompleted_exercises() {
        return completed_exercises;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<StudentSupport> getAssigned_teacher() {
        return assigned_teacher;
    }

    public List<UserParent> getParents() {
        return parents;
    }

    public List<SchoolSubjectYear> getAssigned_courses() {
        return assigned_courses;
    }
}
