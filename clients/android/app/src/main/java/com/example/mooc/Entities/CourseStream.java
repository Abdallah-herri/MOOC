package com.example.mooc.Entities;

import com.google.gson.annotations.SerializedName;

public class CourseStream extends Course
{
    @SerializedName("stream")
    private Stream stream;

    public Stream getStream() {
        return stream;
    }
}
