package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("login")
    private String login;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    @SerializedName("type")
    private UserType type;

    @SerializedName("session")
    private List<Session> session = new ArrayList<>();

    @SerializedName("planning")
    private List<Planning> planning = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public UserType getType() {
        return type;
    }

    public List<Session> getSession() {
        return session;
    }

    public List<Planning> getPlanning() {
        return planning;
    }
}
