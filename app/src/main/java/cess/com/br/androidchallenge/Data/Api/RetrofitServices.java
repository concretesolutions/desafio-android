package cess.com.br.androidchallenge.Data.Api;

import java.util.ArrayList;
import java.util.Map;

import cess.com.br.androidchallenge.Model.Remote.PR;
import cess.com.br.androidchallenge.Model.Remote.Repo;
import cess.com.br.androidchallenge.Model.Remote.Repositories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface RetrofitServices {

    //language:Java
    //sort=stars
    //page=1
    @GET("search/repositories")
    Call<Repositories> getRepos(@QueryMap Map<String, String> options);

    @GET("repos/{user_name}/{repo_name}/pulls")
    Call<ArrayList<PR>> getPRs(@Path("user_name") String user, @Path("repo_name") String repo);
}

