package com.example.lucas.concrete_solutions_desafio.contract;

import com.example.lucas.concrete_solutions_desafio.model.RepositoryList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface IRepository {
    @Headers({
            "Accept:application/vnd.github.mercy-preview+json"
    })
    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositoryList> getRepositories(@Query("page") String pageNumber);

     Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create())
            .build();
}
