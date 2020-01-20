package com.example.desafio_concrete.networkUtils;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubInterface {

    @GET("search/repositories?q=language:java&sort=stars&order=desc&page=?")
    Call<Repository> gitRepositories(@Query("page") int page);

    @GET("repos/{owner}/{repoName}/pulls")
    Call<List<PullRequest>> gitPullRequests(@Path("owner") String owner,
                               @Path("repoName") String repoName);

}
