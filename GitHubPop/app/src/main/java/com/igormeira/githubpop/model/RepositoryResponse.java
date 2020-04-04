package com.igormeira.githubpop.model;

import androidx.lifecycle.MutableLiveData;

import com.igormeira.githubpop.apirequests.GitHubClient;
import com.igormeira.githubpop.apirequests.GitHubService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryResponse {
    private ArrayList<Repository> repositories = new ArrayList<>();
    private MutableLiveData<List<Repository>> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Repository>> getMutableLiveData() {

        final GitHubService service = new GitHubClient().getGitHubService();

        Call<Repositories> call = service.getRepositories(1);
        call.enqueue(new Callback<Repositories>() {
            @Override
            public void onResponse(Call<Repositories> call, Response<Repositories> response) {
                Repositories repositoriesBody = response.body();
                if (repositoriesBody != null && repositoriesBody.getRepositories() != null) {
                    repositories = (ArrayList<Repository>) repositoriesBody.getRepositories();
                    mutableLiveData.setValue(repositories);
                }
            }

            @Override
            public void onFailure(Call<Repositories> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }
}
