package com.jcjm.desafioandroid.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.jcjm.desafioandroid.datasource.gitrepositories.GitRepositoryDataSourceClass;
import com.jcjm.desafioandroid.datasource.gitrepositories.GitRepositoryDataSourceFactory;
import com.jcjm.desafioandroid.model.GitRepository;
import com.jcjm.desafioandroid.repository.Repository;
import com.jcjm.desafioandroid.util.Constant;

import io.reactivex.disposables.CompositeDisposable;

public class PagingGitRepositoriesViewModel extends ViewModel {

    private GitRepositoryDataSourceFactory gitRepositoryDataSourceFactory;
    private LiveData<PagedList<GitRepository>> listLiveData;

    private LiveData<String> progressLoadStatus = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PagingGitRepositoriesViewModel(Repository repository) {
        gitRepositoryDataSourceFactory = new GitRepositoryDataSourceFactory(repository, compositeDisposable);
        initializePaging();
    }


    private void initializePaging() {

        PagedList.Config pagedListConfig =
                new PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(Constant.ITEMS_PER_PAGE).build();

        listLiveData = new LivePagedListBuilder<>(gitRepositoryDataSourceFactory, pagedListConfig)
                .build();

        progressLoadStatus = Transformations.switchMap(gitRepositoryDataSourceFactory.getMutableLiveData(), GitRepositoryDataSourceClass::getProgressLiveStatus);

    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public LiveData<PagedList<GitRepository>> getListLiveData() {
        return listLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}