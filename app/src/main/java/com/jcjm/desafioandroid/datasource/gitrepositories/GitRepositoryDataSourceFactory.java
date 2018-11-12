package com.jcjm.desafioandroid.datasource.gitrepositories;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.jcjm.desafioandroid.model.GitRepository;
import com.jcjm.desafioandroid.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;


public class GitRepositoryDataSourceFactory extends DataSource.Factory<Integer, GitRepository> {

    private MutableLiveData<GitRepositoryDataSourceClass> liveData;
    private Repository repository;
    private CompositeDisposable compositeDisposable;

    public GitRepositoryDataSourceFactory(Repository repository, CompositeDisposable compositeDisposable) {
        this.repository = repository;
        this.compositeDisposable = compositeDisposable;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<GitRepositoryDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @Override
    public DataSource<Integer, GitRepository> create() {
        GitRepositoryDataSourceClass dataSourceClass = new GitRepositoryDataSourceClass(repository, compositeDisposable);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
