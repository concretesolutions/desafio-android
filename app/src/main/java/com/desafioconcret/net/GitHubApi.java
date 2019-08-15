package com.desafioconcret.net;

import com.desafioconcret.pojo.json.PullRequests;
import com.desafioconcret.pojo.json.TopRepositorios;

import java.util.List;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("search/repositories?")
    Observable<TopRepositorios> getTopRepositorios(@Query("q") String q, @Query("sort") String sort, @Query("page") Integer page);
//    Call<TopRepositorios> getTopRepositorios (@Query("q") String q, @Query("sort") String sort, @Query("page") Integer page);


    @GET("repos/{criador}/{repositorio}/pulls")
    Observable<List<PullRequests>> getPulls(@Path("criador") String creator, @Path("repositorio") String repositorie);


}
