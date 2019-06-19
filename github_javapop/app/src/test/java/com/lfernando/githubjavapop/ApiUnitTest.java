package com.lfernando.githubjavapop;

import com.google.gson.Gson;
import com.lfernando.githubjavapop.constants.Constants;
import com.lfernando.githubjavapop.model.PullRequest;
import com.lfernando.githubjavapop.model.Repo;
import com.lfernando.githubjavapop.network.GithubApi;
import com.lfernando.githubjavapop.network.RepositoryReponse;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApiUnitTest {

    @Test
    public void listRepoApiTest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubApi service = retrofit.create(GithubApi.class);
        Call<RepositoryReponse> repos = service.listRepos("1");
        repos.enqueue(new Callback<RepositoryReponse>() {
            @Override
            public void onResponse(Call<RepositoryReponse> call, Response<RepositoryReponse> response) {
                List<Repo> repos = response.body().getItems();
                assertNotNull(repos);
                assertNotNull(repos.get(0).getName());
                assertNotNull(repos.get(0).getOwner().getLogin());
            }

            @Override
            public void onFailure(Call<RepositoryReponse> call, Throwable t) {

            }
        });
    }

    @Test
    public void listPrs() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubApi service = retrofit.create(GithubApi.class);
        Call<List<PullRequest>> prs = service.listPrs("CyC2018", "CS-Notes");
        prs.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> prs = response.body();
                assertNotNull(prs);
                assertNotNull(prs.get(0).getTitle());
                assertNotNull(prs.get(0).getUser().getLogin());
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                assertNotNull(t.getCause());
            }
        });
    }
}
