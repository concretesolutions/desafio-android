package br.com.githubjavapop.service;

import java.util.List;

import br.com.githubjavapop.entidade.PullRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PullRequestService {
    @GET("/repos/{login}/{name}/pulls")
    Call<List<PullRequest>> getPullRequest(@Path("login") String login, @Path("name") String name);
}
