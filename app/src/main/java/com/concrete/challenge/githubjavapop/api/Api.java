package com.concrete.challenge.githubjavapop.api;

import com.concrete.challenge.githubjavapop.BuildConfig;
import com.concrete.challenge.githubjavapop.domain.PullRequest;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private Retrofit retrofit;
    private Webservice webservice;

    public Api() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        webservice = retrofit.create(Webservice.class);
    }

    public Single<RepositoriesResponse> getRepositories(Integer page) {
        return webservice.getRepositories(page);
    }

    public Single<ArrayList<PullRequest>> getPullRequests(String userName, String repositoryName, int page) {
        return webservice.getPullRequests(userName, repositoryName, page);
    }

    public Single<User> getUser(String name) {
        return webservice.getUser(name);
    }
}
