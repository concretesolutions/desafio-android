package br.com.erico.desafio_android.controls.interfaces;

import br.com.erico.desafio_android.models.User;
import br.com.erico.desafio_android.models.RepositoryResponse;
import br.com.erico.desafio_android.models.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @GET("search/repositories?q=language:Java&sort=stars")
    Call<RepositoryResponse> getRepositories(@Query("page") Integer page);

    @GET("repos/{user}/{repo}/pulls")
    Call<List<PullRequest>> getPullRequests(@Path("user") String userName, @Path("repo") String repoName);

    @GET("users/{user}")
    Call<User> getUser(@Path("user") String username);

}
