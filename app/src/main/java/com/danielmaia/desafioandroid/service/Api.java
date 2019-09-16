package com.danielmaia.desafioandroid.service;

import com.danielmaia.desafioandroid.service.responses.RepoResponse;
import com.danielmaia.desafioandroid.service.responses.PullResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepoResponse> getRepoList(@Query("page") int page);

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<PullResponse>> getPullRequest(@Path("owner") String owner,
                                            @Path("repo") String repo);
}
