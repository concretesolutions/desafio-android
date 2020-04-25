package com.concrete.challenge.githubjavapop.api;

import com.concrete.challenge.githubjavapop.BuildConfig;
import com.concrete.challenge.githubjavapop.domain.PullRequest;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryApi {

    private Retrofit retrofit;
    private WebserviceApi webservice;

    public RepositoryApi() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        webservice = retrofit.create(WebserviceApi.class);
    }

    public void getRepositories(Integer page, final RepositoryCallback<RepositoriesResponse> repositoryCallback ) {
        webservice.getRepositories(page).enqueue(new Callback<RepositoriesResponse>() {
            @Override
            public void onResponse(Call<RepositoriesResponse> call, Response<RepositoriesResponse> response) {
                if(response.isSuccessful()) {
                    if(repositoryCallback != null) repositoryCallback.onResponse(response.body());
                } else {
                    if(repositoryCallback != null) repositoryCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<RepositoriesResponse> call, Throwable t) {
                if(repositoryCallback != null) repositoryCallback.onFailure();
            }
        });
    }

    public void getPullRequests(String userName, String repositoryName, final RepositoryCallback<ArrayList<PullRequest>> repositoryCallback ) {
        webservice.getPullRequests(userName, repositoryName).enqueue(new Callback<ArrayList<PullRequest>>() {
            @Override
            public void onResponse(Call<ArrayList<PullRequest>> call, Response<ArrayList<PullRequest>> response) {
                if(response.isSuccessful()) {
                    if(repositoryCallback != null) repositoryCallback.onResponse(response.body());
                } else {
                    if(repositoryCallback != null) repositoryCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PullRequest>> call, Throwable t) {
                if(repositoryCallback != null) repositoryCallback.onFailure();
            }
        });
    }

    public void getUser(String name, final RepositoryCallback<User> repositoryCallback ) {
        webservice.getUser(name).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()) {
                    if(repositoryCallback != null) repositoryCallback.onResponse(response.body());
                } else {
                    if(repositoryCallback != null) repositoryCallback.onFailure();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if(repositoryCallback != null) repositoryCallback.onFailure();
            }
        });
    }

    public interface RepositoryCallback <T> {
        void onResponse(T result);
        void onFailure();
    }
}
