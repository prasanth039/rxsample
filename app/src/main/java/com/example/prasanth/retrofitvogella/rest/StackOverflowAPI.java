package com.example.prasanth.retrofitvogella.rest;

import com.example.prasanth.retrofitvogella.model.StackOverflowQuestions;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Prasanth on 10/28/2016.
 */
public interface StackOverflowAPI {
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Call<StackOverflowQuestions> loadQuestions(@Query("tagged") String tags);

    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Observable<StackOverflowQuestions> loadQuestionsUsingRx(@Query("tagged") String tags);
}

