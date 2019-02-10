package com.example.rpanaquecavana.gitandroid;

import com.example.rpanaquecavana.gitandroid.Modelos.RepoGit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostService
{
     String API_ROUTE = "search/repositories";

     @GET(API_ROUTE)
     Call<RepoGit> getRepositorio(
             @Query("q") String q ,
             @Query("sort") String sort,
             @Query("page") int page);

}
