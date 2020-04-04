package com.igormeira.githubpop.apirequests;

import com.igormeira.githubpop.model.PullRequest;
import com.igormeira.githubpop.model.Repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

    // REPOSITORIES //
    @GET("search/repositories?q=language:Java&sort=stars")
    Call<Repositories> getRepositories(@Query("page") long page);

    // PULL REQUESTS //
    @GET("repos/{creator}/{name}/pulls")
    Call<List<PullRequest>> getPullRequests(@Path("creator") String repoCreator,
                                            @Path("name") String repoName);
}
