package com.example.javapop.APIClientGithub.Retrofit;

import com.example.javapop.Models.PullRequestList;
import com.example.javapop.Models.RepositoryList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("search/repositories?")
    Call<RepositoryList> getRepository(@Query("q") String language,
                                       @Query("sort") String sort,
                                       @Query("page") int page);

    @GET("repos/{ownerGit}/{repository}/pulls")
    Call<List<PullRequestList>> getPullRequest(@Path("ownerGit") String mOwnerGit,
                                               @Path("repository") String mRepository);
}
