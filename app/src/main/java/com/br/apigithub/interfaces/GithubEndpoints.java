package com.br.apigithub.interfaces;

import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Pull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rlima on 04/10/18.
 */

public interface GithubEndpoints {
    @GET("/search/repositories?q=language:Java")
    Call<GithubRepository> listRepositoriesJava(@Query("sort") String sort, @Query("page") Integer page); //&sort=stars&page=1
//"https://api.github.com/repos/iluwatar/java-design-patterns/pulls{/number}"
    @Headers({
            "User-Agent: com.br.apigithub"
    })
    @GET("/repos/{user}/{repo}/pulls")
    Call<List<Pull>> getPullsFromRepo(@Path("user") String user, @Path("repo") String repo, @Query("page") Integer page);

    @Headers({
            "User-Agent: com.br.apigithub"
    })
    @GET("{fullNameRepo}/pulls/{number}")
    Call<Pull> getSinglePull(@Path("fullNameRepo") String repo, @Path("number") Integer number);
}
