package com.example.desafioconcrete.WebConnections;

import com.example.desafioconcrete.Objects.DetailsPull;
import com.example.desafioconcrete.Objects.DetailsRepository;
import com.example.desafioconcrete.Objects.GithubApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("repositories")
    Call<GithubApiResponse> getRepositoryValues(@Query("q") String language, @Query("sort") String sort, @Query("page") String page);

    @GET("{owner}/{name}/pulls")
    Call<List<DetailsPull>> getPullValues(@Path("owner") String owner, @Path("name") String name);
}
