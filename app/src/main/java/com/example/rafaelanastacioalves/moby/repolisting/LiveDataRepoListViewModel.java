package com.example.rafaelanastacioalves.moby.repolisting;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.rafaelanastacioalves.moby.vo.Repo;
import com.example.rafaelanastacioalves.moby.repository.DataSourceFactory;

import java.util.concurrent.Executors;

import static android.arch.lifecycle.Transformations.switchMap;

public class LiveDataRepoListViewModel extends ViewModel {

    private LiveData<PagedList<Repo>> mMainEntityPagedList = setupPagedList();
    private LiveData<Boolean> mIsLoading;


    @NonNull
    private LiveData setupPagedList() {

        DataSourceFactory dataSourceFactory = new DataSourceFactory("Java", "start");
        mIsLoading = switchMap(dataSourceFactory.getDataSource(), input -> input.getLiveLoadStatus());

        return new LivePagedListBuilder(dataSourceFactory,10)
                .setFetchExecutor(Executors.newFixedThreadPool(5))
                .build();
    }

    public LiveData<PagedList<Repo>> getMainEntityList() {
        return mMainEntityPagedList;
    }


    public LiveData<Boolean> getLoadStatus() {
        return mIsLoading;
    }
}
