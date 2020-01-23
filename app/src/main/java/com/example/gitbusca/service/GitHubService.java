package com.example.gitbusca.service;

import com.example.gitbusca.model.GitCatalogo;
import com.example.gitbusca.model.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {

    public static final String BASE_URL = "https://api.github.com/";
    public static final String REPOS = "repos/";





    //@GET("repositories?q=language:Java&sort=stars&page=1")
    @GET("search/repositories")
    Call<GitCatalogo> listarCatalogo(
            @Query("q") String query,
            @Query("sort") String sort,
            @Query("page") int page);

    @GET("pulls")
    Call<List<Pull>> listarPulls();

}
