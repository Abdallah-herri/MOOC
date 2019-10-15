package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CourseExecise extends Course {

    @SerializedName("completedExercises")
    private List<Deposit> completed_exercises = new ArrayList<>();

    @SerializedName("notes")
    private List<Note> notes = new ArrayList<>();
}
