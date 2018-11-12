package com.jcjm.desafioandroid.network;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiCallInterface {

   
    @GET("/search/repositories")
    Observable<JsonElement>  getRepositories(@Query("q") String q, @Query("sort") String sort, @Query("page")String page );

    @GET("repos/{login}/{repositorio}/pulls")
    Observable<JsonArray> getPulls(@Path("login") String login, @Path("repositorio") String repositorio);

}
