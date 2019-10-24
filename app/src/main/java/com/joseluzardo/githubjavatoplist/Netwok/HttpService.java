package com.joseluzardo.githubjavatoplist.Netwok;

import com.joseluzardo.githubjavatoplist.Models.Pulls.PullsItem;
import com.joseluzardo.githubjavatoplist.Models.Repos.JavaList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class HttpService {

    //INTERFACE REPOS

    public interface listRepos{
        @GET("search/repositories")
        Call<JavaList> list(@Query("q") String q , @Query("sort") String sort, @Query("page") String page);
    }

    public static listRepos getRepos(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(listRepos.class);

    }

    //INTERFACE PRs

    public interface listPRs{
        @GET("repos/{user}/{repo}/pulls")
        Call<List<PullsItem>> list(@Path("user") String user , @Path("repo") String repo);
    }

    public static listPRs getPullRequest(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(listPRs.class);

    }

}
