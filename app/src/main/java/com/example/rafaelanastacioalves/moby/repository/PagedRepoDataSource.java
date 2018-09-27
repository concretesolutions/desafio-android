package com.example.rafaelanastacioalves.moby.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.vo.Repo;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class PagedRepoDataSource extends PageKeyedDataSource<String, Repo> {

    private final String gitRepoLanguage;
    private final String gitSortParam;
    private MutableLiveData<Boolean> loadStatus = new MutableLiveData<Boolean>();

    public PagedRepoDataSource(String gitRepoLanguage, String gitSortParam) {
        this.gitRepoLanguage = gitRepoLanguage;
        this.gitSortParam = gitSortParam;
        loadStatus.postValue(Boolean.TRUE);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Repo> callback) {
        loadStatus.postValue(Boolean.TRUE);
        GitHubRepository repository = new GitHubRepository();
        Single<List<Repo>> observable = repository.getRepoList(String.valueOf(1), gitRepoLanguage, gitSortParam);
        observable.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoList -> {
                            loadStatus.setValue(Boolean.FALSE);
                            callback.onResult(
                                    repoList,
                                    "",
                                    String.valueOf(1 + 1)
                            );
                        },
                        error -> Timber.w("Subsctibe On Error: " + error.getMessage())
                );

    }

    public MutableLiveData<Boolean> getLiveLoadStatus() {
        return loadStatus;
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Repo> callback) {
        // no need to go back here
    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Repo> callback) {
        loadStatus.postValue(Boolean.TRUE);
        GitHubRepository repository = new GitHubRepository();
        Single<List<Repo>> observable = repository.getRepoList(String.valueOf(params.key), gitRepoLanguage, gitSortParam);
        observable.subscribeOn(Schedulers.computation())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoList -> {
                            loadStatus.postValue(Boolean.FALSE);
                            callback.onResult(
                                    repoList,
                                    String.valueOf(Integer.valueOf(params.key) + 1)
                            );
                        },
                        error -> Timber.w("Subsctibe On Error: " + error.getMessage())

                );
    }



}







