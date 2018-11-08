package com.example.luisguzman.desafio_android.interfaz;

import com.example.luisguzman.desafio_android.modal.DataList;
import com.example.luisguzman.desafio_android.modal.DataListPull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "https://api.github.com/";

    @GET("search/repositories")
    Call<DataList> getRepos(
            @Query("q") String language,
            @Query("sort") String sort,
            @Query("page") String page);

    @GET("repos/{owner}/{repo}/pulls")
    Call<List<DataListPull>> getPulls(
            @Path("owner") String owner,
            @Path("repo") String repo);

}
