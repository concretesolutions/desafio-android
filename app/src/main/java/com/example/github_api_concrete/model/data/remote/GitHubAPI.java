package com.example.github_api_concrete.model.data.remote;

import com.example.github_api_concrete.model.pojo.repos.RepositoriesResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubAPI {

    @GET("search/repositories")
    Observable<RepositoriesResult> getAllRepositories(@Query("q") String language,
                                                      @Query("sort") String sort,
                                                      @Query("page") int page);

    @GET("repos/{login}/{name}/pulls")
    Observable<RepositoriesResult> getAllPRs(@Path("login") String login,
                                             @Path("name") String name);
}
