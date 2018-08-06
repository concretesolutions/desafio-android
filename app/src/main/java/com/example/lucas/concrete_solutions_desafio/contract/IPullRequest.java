package com.example.lucas.concrete_solutions_desafio.contract;


import com.example.lucas.concrete_solutions_desafio.model.PullRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IPullRequest {
    @Headers({
            "Accept:application/vnd.github.symmetra-preview+json"
    })
    @GET("/repos/{fullname}/pulls")
    Call<ArrayList<PullRequest>> getPullRequests(@Path(value = "fullname", encoded = true) String url);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
            .build();
}
