package br.com.appdesafio.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.com.appdesafio.model.entity.UrlEntity;
import br.com.appdesafio.model.persistence.AppDatabase;
import br.com.appdesafio.model.persistence.SharedPreference;
import br.com.appdesafio.model.pojo.Item;
import br.com.appdesafio.model.pojo.PullRequest;
import br.com.appdesafio.model.pojo.Repository;
import br.com.appdesafio.service.IService;
import br.com.appdesafio.task.AppExecutors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListRepository {

    public IService mIservice;
    public SharedPreference sharedPreferences;
    public Context mContext;
    public AppDatabase mAppDatabase;
    public AppExecutors mAppExecutors;

    @Inject
    public ListRepository(final  IService service,
                          final SharedPreference preference,
                          final Application application,
                          final AppExecutors appExecutors,
                          final AppDatabase appDatabase){
        this.mIservice = service;
        this.sharedPreferences = preference;
        this.mContext = application;
        this.mAppExecutors = appExecutors;
        this.mAppDatabase = appDatabase;

    }

    /**
     * method responsible for logging in to the server.
     *
     * @param
     * @return
     */
    public LiveData<Repository> getListRepository(final int page) {
        final MutableLiveData<Repository> data = new MutableLiveData<>();
        final Call<JsonObject> call = this.mIservice.getListRepository("language:Java", "stars", String.valueOf(page));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(final Call<JsonObject> call, final Response<JsonObject> response) {
                Gson gson = new Gson();
                Repository repository = gson.fromJson(response.body(), Repository.class);
                save(repository.getItems());
                data.setValue(repository);

            }


            @Override
            public void onFailure(final Call<JsonObject> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    /**
     * method responsible for logging in to the server.
     *
     * @param
     * @return
     */
    public LiveData<List<PullRequest>> getListPullRequest(final String creator, final String repository) {
        final MutableLiveData<List<PullRequest> > data = new MutableLiveData<>();
        final Call<JsonArray> call = this.mIservice.getListPullRequest(creator, repository);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(final Call<JsonArray> call, final Response<JsonArray> response) {
                Gson gson = new Gson();
                PullRequest pullRequest[] = gson.fromJson(response.body(), PullRequest[].class);
                data.setValue(Arrays.asList(pullRequest));

            }


            @Override
            public void onFailure(final Call<JsonArray> call, final Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public void save(final List<Item> listUrl) {

        final Runnable runnable = () -> {
            for (Item item : listUrl) {
                UrlEntity url = new UrlEntity();
                url.setUserName(item.getOwner().getLogin());
                url.setUrl(item.getOwner().getAvatarUrl());
                mAppDatabase.urlDAO().insertAll(url);
            }


        };

        mAppExecutors.diskIO().execute(runnable);

    }




}
