package com.example.brunovsiq.concreteapp.networking;

import com.example.brunovsiq.concreteapp.models.Repository;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {


    //https://api.github.com/search/repositories?q=language:Java&sort=stars&page=1

    @GET("search/repositories?q=language:Java&sort=stars&page={pageNumber}")
    Call<JSONObject> listRepos(@Path("pageNumber") Integer page);
}
