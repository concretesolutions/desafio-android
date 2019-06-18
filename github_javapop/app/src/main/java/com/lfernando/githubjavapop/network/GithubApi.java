package com.lfernando.githubjavapop.network;

import com.lfernando.githubjavapop.model.PullRequest;
import com.lfernando.githubjavapop.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubApi {
    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositoryReponse> listRepos(@Query("page") String pageNumber);

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<PullRequest>> listPrs(@Path("owner") String owner, @Path("repo") String repository);
}
