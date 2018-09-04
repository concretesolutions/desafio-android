package com.example.vladi.consultagit.io;

import com.example.vladi.consultagit.io.response.PullsResponse;
import com.example.vladi.consultagit.io.response.RepositoriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET("search/repositories")
    Call<RepositoriesResponse> getRepositories(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") String page);

    @GET("repos/{owner}/{repo}/pulls")
    Call<PullsResponse> getPulls(
            @Path ("owner") String authorName,
            @Path ("repo") String repositoryName);

    }


