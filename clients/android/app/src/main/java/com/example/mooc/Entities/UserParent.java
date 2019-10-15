package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserParent extends User {

    @SerializedName("childs")
    private List<UserStudent> childs = new ArrayList<>();

    public List<UserStudent> getChilds() {
        return childs;
    }

}
