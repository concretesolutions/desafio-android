package com.example.rafaelanastacioalves.moby.api;

import com.example.rafaelanastacioalves.moby.vo.Pull;
import com.example.rafaelanastacioalves.moby.vo.RepoContainer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIClient {

    @GET("/search/repositories")
    Call<RepoContainer> getRepos(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") String page
    );

    @GET("/repos/{creator}/{repository}/pulls")
    Call<ArrayList<Pull>> getPulls(@Path("creator") String creator,
                                   @Path("repository") String repository);

}
