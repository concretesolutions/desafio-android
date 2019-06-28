package com.brunorfreitas.desafioconcrete.data.API;

import com.brunorfreitas.desafioconcrete.data.Model.PullRequestRepositoryResponse;
import com.brunorfreitas.desafioconcrete.data.Model.RepositoryResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPointGitRetrofit {

    @GET("search/repositories")
    Call<RepositoryResponse> getRepositoryList(
            @Query("q") String q,
            @Query("sort") String sort,
            @Query("page") int page
    );

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<PullRequestRepositoryResponse>> getListPullRequestRepositoryResponse(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
