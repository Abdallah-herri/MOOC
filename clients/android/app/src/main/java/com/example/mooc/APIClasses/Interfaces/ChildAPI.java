package com.example.mooc.APIClasses.Interfaces;

import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Entities.Course;
import com.example.mooc.Entities.History;
import com.example.mooc.Entities.Note;
import com.example.mooc.Entities.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChildAPI {

    @FormUrlEncoded
    @POST("sign_in")
    Call<RetrievedData<User>> loginClient(
            @Field("login") String login,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("sign_up")
    Call<RetrievedData<User>> signupClient(
            @Field("login") String login,
            @Field("password") String password,
            @Field("last_name") String last_name,
            @Field("first_name") String first_name,
            @Field("user_type") String user_type
    );

    @GET("student/get/courses")
    Call<RetrievedData<List<Course>>> getAllCourses();

    @GET("student/get/course/{id}")
    Call<RetrievedData<Course>> getCourseDetails(
            @Path("id") int id
    );

    @GET("files/get/{id}")
    Call<ResponseBody> getCourseFile(
            @Path("id") int id
    );

    @GET("student/get/notes")
    Call<RetrievedData<List<Note>>> getAllNotes();

    @GET("student/get/history")
    Call<RetrievedData<List<History>>> getAllHistory();
}
