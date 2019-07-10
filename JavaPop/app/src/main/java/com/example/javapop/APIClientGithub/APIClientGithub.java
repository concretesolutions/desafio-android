package com.example.javapop.APIClientGithub;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.javapop.APIClientGithub.Retrofit.RetrofitService;
import com.example.javapop.Events.PullRequestEvent;
import com.example.javapop.Events.RepositoryListEvent;
import com.example.javapop.Models.PullRequestList;
import com.example.javapop.Models.Repository;
import com.example.javapop.Models.RepositoryList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIClientGithub {
    private static final String TAG = "APIClientGithub";
    private static final String LANGUAGE = "language:Java";
    private static final String SORT = "stars";
    private RetrofitService mService;

    public APIClientGithub(RetrofitService service) {
        this.mService = service;
    }

    public void getListRepositoryCall(int page) {
        Call<RepositoryList> repositoryList = mService.getRepository(LANGUAGE, SORT, page);
        repositoryList.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(@NonNull Call<RepositoryList> call, @NonNull Response<RepositoryList> response) {
                if (response.isSuccessful()) {
                    RepositoryList repositoriesList = response.body();
                    List<Repository> repositories = repositoriesList.getRepositoriesList();

                    EventBus.getDefault().post(new RepositoryListEvent(repositories));
                    Log.d(TAG, "repository " + repositories);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    Log.d(TAG, "error " + responseBody);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RepositoryList> call, @NonNull Throwable t) {
                Log.e(TAG, "error " + t);
            }
        });
    }

    public void getPullRequest(String mOwnerGit, String mRepository) {
        Call<List<PullRequestList>> pullRequest = mService.getPullRequest(mOwnerGit, mRepository);
        pullRequest.enqueue(new Callback<List<PullRequestList>>() {
            @Override
            public void onResponse(Call<List<PullRequestList>> call, Response<List<PullRequestList>> response) {
                if (response.isSuccessful()) {
                    List<PullRequestList> pullRequests = response.body();

                    EventBus.getDefault().post(new PullRequestEvent(pullRequests));
                    Log.d(TAG, "repository " + pullRequests);
                } else {
                    ResponseBody responseBody = response.errorBody();
                    Log.d(TAG, "error " + responseBody);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequestList>> call, Throwable t) {
                Log.e(TAG, "error " + t);
            }
        });
    }
}
