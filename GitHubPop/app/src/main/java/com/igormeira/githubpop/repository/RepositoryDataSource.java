package com.igormeira.githubpop.repository;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.igormeira.githubpop.apirequests.GitHubClient;
import com.igormeira.githubpop.apirequests.GitHubService;
import com.igormeira.githubpop.model.Repositories;
import com.igormeira.githubpop.model.Repository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryDataSource extends PageKeyedDataSource<Long, Repository> {
    public static int PAGE_SIZE = 30;
    public static long INITIAL_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Repository> callback) {
        GitHubService service = new GitHubClient().getGitHubService();
        Call<Repositories> call = service.getRepositories(INITIAL_PAGE);
        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Repositories repositoriesBody = response.body();
                if (repositoriesBody != null && repositoriesBody.getRepositories() != null) {
                    List<Repository> repositories = (ArrayList<Repository>) repositoriesBody.getRepositories();
                    callback.onResult(repositories, null, INITIAL_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repository> callback) {
        GitHubService service = new GitHubClient().getGitHubService();
        Call<Repositories> call = service.getRepositories(params.key);
        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Repositories repositoriesBody = response.body();
                if (repositoriesBody != null && repositoriesBody.getRepositories() != null) {
                    List<Repository> repositories = repositoriesBody.getRepositories();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(repositories, key);
                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Repository> callback) {
        GitHubService service = new GitHubClient().getGitHubService();
        Call<Repositories> call = service.getRepositories(params.key);
        call.enqueue(new Callback<Repositories>() {
            @Override public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Repositories repositoriesBody = response.body();
                if (repositoriesBody != null && repositoriesBody.getRepositories() != null) {
                    List<Repository> repositories = repositoriesBody.getRepositories();
                    callback.onResult(repositories, params.key + 1);
                }
            }
            @Override public void onFailure(Call<Repositories> call, Throwable t) {
            }
        });

    }
}
