package com.br.apigithub.services;

import com.br.apigithub.beans.GithubRepository;
import com.br.apigithub.beans.Pull;
import com.br.apigithub.interfaces.GithubEndpoints;
import com.br.apigithub.interfaces.IGithubServiceProvider;
import com.br.apigithub.interfaces.INotifyViewModelAboutService;
import com.br.apigithub.interfaces.RetrofitServiceContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rlima on 04/10/18.
 */

public class GithubServiceProvider implements IGithubServiceProvider {

    private RetrofitServiceContract service;

    public GithubServiceProvider(RetrofitServiceContract service) {
        this.service = service;
    }

    @Override
    public void listReposJava(final String sort, final Integer page, final INotifyViewModelAboutService listener) {
        service.getRetrofitService().getRetrofit().create(GithubEndpoints.class).listRepositoriesJava(sort, page).enqueue(new Callback<GithubRepository>() {
            @Override
            public void onResponse(Call<GithubRepository> call, Response<GithubRepository> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.returnListRepos(response.body());
                } else {
                    listener.returnListRepos(null);
                }
            }

            @Override
            public void onFailure(Call<GithubRepository> call, Throwable t) {
                listener.notifyOnError(t);
            }
        });
    }

    @Override
    public void getPulls(String ownerRepo, String repoName, Integer page, final INotifyViewModelAboutService listener) {
        service.getRetrofitService().getRetrofit().create(GithubEndpoints.class).getPullsFromRepo(ownerRepo, repoName, page).enqueue(new Callback<List<Pull>>() {
            @Override
            public void onResponse(Call<List<Pull>> call, Response<List<Pull>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.returnPullRequests(response.body());
                } else {
                    listener.returnPullRequests(null);
                }
            }

            @Override
            public void onFailure(Call<List<Pull>> call, Throwable t) {
                listener.notifyOnError(t);
            }
        });
    }

}
