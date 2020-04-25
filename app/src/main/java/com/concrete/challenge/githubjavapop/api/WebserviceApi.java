package com.concrete.challenge.githubjavapop.api;

import com.concrete.challenge.githubjavapop.domain.PullRequest;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebserviceApi {

    @GET("/search/repositories?q=language:Java&sort=stars")
    Call<RepositoriesResponse> getRepositories(@Query("page") Integer page);

    @GET("/repos/:owner/:repo/pulls")
    Call<ArrayList<PullRequest>> getPullRequests(@Path("owner") String userName, @Path("repo") String repositoryName);

    @GET("/users/:username")
    Call<User> getUser(@Path("username") String name);
}
