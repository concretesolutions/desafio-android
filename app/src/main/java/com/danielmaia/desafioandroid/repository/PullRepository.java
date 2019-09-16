package com.danielmaia.desafioandroid.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.danielmaia.desafioandroid.database.RepoDatabase;
import com.danielmaia.desafioandroid.App;
import com.danielmaia.desafioandroid.model.Pull;
import com.danielmaia.desafioandroid.service.Api;
import com.danielmaia.desafioandroid.service.RetrofitApi;
import com.danielmaia.desafioandroid.service.responses.PullResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PullRepository {

    private static final String TAG = PullRepository.class.getSimpleName();
    private Api apiRequest;

    MutableLiveData<List<Pull>> listPullMutableData = new MutableLiveData<>();
    MutableLiveData<Integer> openedCount = new MutableLiveData<Integer>();
    MutableLiveData<Integer> closedCount = new MutableLiveData<Integer>();

    public PullRepository() {
        apiRequest = RetrofitApi.getRetrofitInstance().create(Api.class);
    }

    public LiveData<List<Pull>> getReposOnServer(String owner, String repo) {
        apiRequest.getPullRequest(owner, repo).enqueue(new Callback<List<PullResponse>>() {
            @Override
            public void onResponse(Call<List<PullResponse>> call, Response<List<PullResponse>> response) {
                Log.d(TAG, "onResponse response:: " + response.body());

                if (response.body() != null) {
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            RepoDatabase.getInstance(App.getInstance()).getPullDao().deleteAll();

                            List<Pull> pullList = new ArrayList<>();

                            if (response.body() != null) {
                                for (PullResponse pullResponse : response.body()) {
                                    Pull pull = new Pull();

                                    pull.setId(pullResponse.getId());
                                    pull.setRepo(repo);
                                    pull.setTitle(pullResponse.getTitle());
                                    pull.setBody(pullResponse.getBody());
                                    pull.setState(pullResponse.getState());
                                    pull.setUrl(pullResponse.getHtml_url());
                                    pull.setAvatar(pullResponse.getUser().getAvatar_url());
                                    pull.setOwner(pullResponse.getUser().getLogin());

                                    RepoDatabase.getInstance(App.getInstance()).getPullDao().insert(pull);
                                    pullList.add(pull);
                                }
                            }
                            listPullMutableData.postValue(pullList);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<PullResponse>> call, Throwable t) {
                listPullMutableData.setValue(null);
            }
        });

        return listPullMutableData;
    }

    public MutableLiveData<Integer> getOpenedCount(String repo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Pull> pullList = RepoDatabase.getInstance(App.getInstance())
                            .getPullDao().getPullOpened(repo);

                    openedCount.postValue(pullList.size());
                } catch (Exception e) {
                    openedCount.postValue(0);
                }
            }
        }).start();

        return openedCount;
    }

    public MutableLiveData<Integer> getClosedCount(String repo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Pull> pullList = RepoDatabase.getInstance(App.getInstance())
                            .getPullDao().getPullClosed(repo);

                    closedCount.postValue(pullList.size());
                } catch (Exception e) {
                    closedCount.postValue(-1);
                }
            }
        }).start();

        return closedCount;
    }
}



















