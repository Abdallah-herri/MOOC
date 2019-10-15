package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Planning implements Serializable {

    @SerializedName("begin")
    private int begin;

    @SerializedName("end")
    private int end;

    @SerializedName("course")
    private Course course;

    @SerializedName("userAssigned")
    private List<User> user_assigned = new ArrayList<>();

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public Course getCourse() {
        return course;
    }

    public List<User> getUser_assigned() {
        return user_assigned;
    }
}
