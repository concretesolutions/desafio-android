package com.jcjm.desafioandroid.datasource.gitrepositories;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.jcjm.desafioandroid.model.GitRepository;
import com.jcjm.desafioandroid.repository.Repository;
import com.jcjm.desafioandroid.util.Constant;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;


public class GitRepositoryDataSourceClass extends PageKeyedDataSource<Integer, GitRepository> {

    private Repository repository;
    private Gson gson;
    private int sourceIndex;
    private MutableLiveData<String> progressLiveStatus;
    private CompositeDisposable compositeDisposable;

    GitRepositoryDataSourceClass(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        progressLiveStatus = new MutableLiveData<>();
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = builder.setLenient().create();
    }


    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, GitRepository> callback) {

        repository.executeGitRepositoriesApi(sourceIndex)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            JSONArray array = object.getJSONArray("items");

                            ArrayList<GitRepository> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {

                                GitRepository gitRepository = new GitRepository();
                                gitRepository.setName(array.getJSONObject(i).optString("name"));
                                gitRepository.setDescription(array.getJSONObject(i).optString("description"));
                                gitRepository.setStargazers_count(array.getJSONObject(i).optString("stargazers_count"));
                                gitRepository.setForks(array.getJSONObject(i).optString("forks_count"));

                                gitRepository.getOwner().setLogin(array.getJSONObject(i).optJSONObject("owner").optString("login"));
                                gitRepository.getOwner().setAvatar_url(array.getJSONObject(i).optJSONObject("owner").optString("avatar_url"));

                                arrayList.add(gitRepository );
                            }

                            sourceIndex++;
                            callback.onResult(arrayList, null, sourceIndex);
                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)

                );

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GitRepository> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, GitRepository> callback) {

        repository.executeGitRepositoriesApi(params.key)
                .doOnSubscribe(disposable ->
                {
                    compositeDisposable.add(disposable);
                    progressLiveStatus.postValue(Constant.LOADING);
                })
                .subscribe(
                        (JsonElement result) ->
                        {
                            progressLiveStatus.postValue(Constant.LOADED);

                            JSONObject object = new JSONObject(gson.toJson(result));
                            JSONArray array = object.getJSONArray("items");

                            ArrayList<GitRepository> arrayList = new ArrayList<>();

                            for (int i = 0; i < array.length(); i++) {

                                GitRepository gitRepository = new GitRepository();
                                gitRepository.setName(array.getJSONObject(i).optString("name"));
                                gitRepository.setDescription(array.getJSONObject(i).optString("description"));
                                gitRepository.setStargazers_count(array.getJSONObject(i).optString("stargazers_count"));
                                gitRepository.setForks(array.getJSONObject(i).optString("forks_count"));

                                gitRepository.getOwner().setLogin(array.getJSONObject(i).optJSONObject("owner").optString("login"));
                                gitRepository.getOwner().setAvatar_url(array.getJSONObject(i).optJSONObject("owner").optString("avatar_url"));

                                arrayList.add(gitRepository );

                            }

                            long totalpages = object.optLong("total_count") / Constant.ITEMS_PER_PAGE;

                            callback.onResult(arrayList, params.key == totalpages ? null : params.key + 1);

                        },
                        throwable ->
                                progressLiveStatus.postValue(Constant.LOADED)
                );
    }
}
