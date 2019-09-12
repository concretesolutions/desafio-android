package com.feliperamoscarvalho.appconsultagithub.data;

import com.feliperamoscarvalho.appconsultagithub.data.model.Pull;
import com.feliperamoscarvalho.appconsultagithub.data.model.RepositorySearchResult;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitEndpoint {

    @GET("/search/repositories")
    Call<RepositorySearchResult> getRepositories(@QueryMap HashMap<String, String> queryMap);


    @GET("/repos/{login}/{repository}/pulls")
    Call<List<Pull>> getPullRequests(@Path("login") String login, @Path("repository") String repository);
}
