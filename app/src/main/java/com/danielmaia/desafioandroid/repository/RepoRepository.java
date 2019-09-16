package com.danielmaia.desafioandroid.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.desafioandroid.App;
import com.danielmaia.desafioandroid.database.RepoDatabase;
import com.danielmaia.desafioandroid.model.Repo;
import com.danielmaia.desafioandroid.service.Api;
import com.danielmaia.desafioandroid.service.RetrofitApi;
import com.danielmaia.desafioandroid.service.responses.RepoResponse;
import com.danielmaia.desafioandroid.service.responses.dto.RepoDto;
import com.danielmaia.desafioandroid.util.AppPreferences;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoRepository {

    private static final String TAG = RepoRepository.class.getSimpleName();
    private Api apiRequest;
    MutableLiveData<List<Repo>> listRepoMutableData = new MutableLiveData<>();

    public RepoRepository() {
        apiRequest = RetrofitApi.getRetrofitInstance().create(Api.class);
    }

    public LiveData<List<Repo>> getReposOnServer(int page) {
        apiRequest.getRepoList(page).enqueue(new Callback<RepoResponse>() {
                    @Override
                    public void onResponse(Call<RepoResponse> call, Response<RepoResponse> response) {
                        Log.d(TAG, "onResponse response:: " + response.body());

                        if (response.body() != null) {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    if (page == 1)
                                        RepoDatabase.getInstance(App.getInstance()).getRepoDao().deleteAll();

                                    List<Repo> repoList = new ArrayList<>();

                                    AppPreferences.getInstance().setTotalItems(response.body().getTotal_count());

                                    for (RepoDto repoDto : response.body().getItems()){
                                        Repo repo = new Repo();

                                        repo.setId(repoDto.getId());
                                        repo.setName(repoDto.getName());
                                        repo.setDescription(repoDto.getDescription());
                                        repo.setStargazers_count(repoDto.getStargazers_count());
                                        repo.setForks(repoDto.getForks());

                                        if (repoDto.getOwner() != null) {
                                            repo.setOwnerId(repoDto.getOwner().getId());
                                            repo.setOwner(repoDto.getOwner().getLogin());
                                            repo.setAvatar(repoDto.getOwner().getAvatar_url());
                                        }

                                        RepoDatabase.getInstance(App.getInstance()).getRepoDao().insert(repo);
                                        repoList.add(repo);
                                    }
                                    listRepoMutableData.postValue(repoList);
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<RepoResponse> call, Throwable t) {
                        listRepoMutableData.setValue(null);
                    }
                });

        return listRepoMutableData;
    }


}
