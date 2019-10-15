package com.example.mooc.APIClasses.Interfaces;

import com.example.mooc.APIClasses.RetrievedData;
import com.example.mooc.Entities.History;
import com.example.mooc.Entities.SchoolSubjectYear;
import com.example.mooc.Entities.User;
import com.example.mooc.Entities.UserStudent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ParentAPI {


    @GET("parent/get/childs")
    Call<RetrievedData<List<UserStudent>>> getAllChildren();


    @FormUrlEncoded
    @POST("parent/student_assignment")
    Call<RetrievedData<UserStudent>> assignParentToChild(
            @Field("student_key") String student_key
    );

    @GET("parent/get/history/{id}")
    Call<RetrievedData<List<History>>> getChildHistory(
            @Path("id") int id
    );

    @GET("parent/get/lastactivity/{id}")
    Call<RetrievedData<Integer>> getChildLastActivity(
            @Path("id") int id
    );

    @GET("get/subject_year")
    Call<RetrievedData<List<SchoolSubjectYear>>> getAllSubjects();

    @FormUrlEncoded
    @POST("parent/subscribe/child")
    Call<RetrievedData<Boolean>> subscribeChild(
            @Field("child_id") int child_id,
            @Field("subject_year_id[]") ArrayList<Integer> chosenSubjects
    );
}
