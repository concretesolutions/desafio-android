package com.desafio.javapop.api;

import com.desafio.javapop.model.PullRequest;
import com.desafio.javapop.model.ResultadoRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubServico {
    @GET("search/repositories?")
    Call<ResultadoRepo> getRepositorio(@Query("q") String q,
                                       @Query("sort") String sort,
                                       @Query("page") int page);


    @GET("repos/{login}/{name}/pulls")
    Call<List<PullRequest>> getPull(@Path("login") String login, @Path("name") String nome);
}
