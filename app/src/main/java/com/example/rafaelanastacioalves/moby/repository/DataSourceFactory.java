package com.example.rafaelanastacioalves.moby.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.example.rafaelanastacioalves.moby.vo.Repo;

public class DataSourceFactory extends DataSource.Factory<String,Repo> {


    private final String gitRepoLanguage;
    private final String gitSortParam;

    private MutableLiveData<PagedRepoDataSource> sourceLiveData = new MutableLiveData<PagedRepoDataSource>();

    public DataSourceFactory(String gitRepoLanguage, String gitSortParam ){
        this.gitRepoLanguage = gitRepoLanguage;
        this.gitSortParam = gitSortParam;
    }
    @Override
    public DataSource<String, Repo> create() {
        PagedRepoDataSource source = new PagedRepoDataSource(gitRepoLanguage, gitSortParam);
        sourceLiveData.postValue(source);
        return source;
    }

    public MutableLiveData<PagedRepoDataSource> getDataSource() {
        return sourceLiveData;
    }
}
