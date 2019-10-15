package com.example.mooc.Entities;

import android.support.annotation.IntDef;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class UserType implements Serializable {

    public static final int ADMIN = 1;
    public static final int TEACHER = 2;
    public static final int PARENT = 3;
    public static final int STUDENT = 4;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("users")
    private List<User> users = new ArrayList<>();

    public UserType(@Type int season) { }

    @IntDef({ADMIN, TEACHER, PARENT, STUDENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type { }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }
}