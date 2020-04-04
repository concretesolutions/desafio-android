package com.igormeira.githubpop.repository;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.igormeira.githubpop.model.Repository;

public class RepositoryDataSourceFactory extends DataSource.Factory<Long, Repository> {

    public MutableLiveData<RepositoryDataSource> userLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Long, Repository> create() {
        RepositoryDataSource dataSource = new RepositoryDataSource();
        userLiveDataSource.postValue(dataSource);
        return dataSource;
    }
}
