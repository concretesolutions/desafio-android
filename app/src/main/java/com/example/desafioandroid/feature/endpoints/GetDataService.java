package com.example.desafioandroid.feature.endpoints;

import com.example.desafioandroid.model.Base;
import com.example.desafioandroid.model.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<Base> getBaseResponse(@Query("page") int page);

    @GET("repos/{user}/{repository}/pulls")
    Call<List<Pull>> getPullsResponse(@Path("user") String user, @Path("repository") String repository);
}
