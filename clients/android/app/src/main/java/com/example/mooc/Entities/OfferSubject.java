package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OfferSubject implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("price")
    private float price;

    @SerializedName("nbSchoolSubject")
    private int nb_school_subject;

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getNb_school_subject() {
        return nb_school_subject;
    }
}
