package com.example.brunovsiq.concreteapp.networking;

import com.example.brunovsiq.concreteapp.models.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    @GET("search/repositories?q=language:Java&sort=stars&page={pageNumber}")
    Call<List<Repository>> listRepos(@Path("pageNumber") String page);
}
