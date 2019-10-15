package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

public class FileDesposit extends File{

    @SerializedName("deposit")
    private Deposit deposit;

    public Deposit getDeposit() {
        return deposit;
    }
}
