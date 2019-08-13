package com.example.brunovsiq.concreteapp.models;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import io.reactivex.subjects.PublishSubject;

public class RepositoryDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<RepositoryDataSource> mutableLiveData;
    private RepositoryDataSource dataSource;

    public RepositoryDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {

        dataSource = new RepositoryDataSource();
        mutableLiveData.postValue(dataSource);
        return dataSource;
    }
}
