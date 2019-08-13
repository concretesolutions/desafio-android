package com.example.brunovsiq.concreteapp.screens;

import android.app.Application;

import com.example.brunovsiq.concreteapp.models.Repository;
import com.example.brunovsiq.concreteapp.models.RepositoryDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by Morris on 03,June,2019
 */
public class MainActivityViewModel extends AndroidViewModel {

    private Executor executor;
    public static LiveData<PagedList<Repository>> repositoryPagedList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        //UserDataService userDataService = RetrofitInstance.getService();
        RepositoryDataSourceFactory factory = new RepositoryDataSourceFactory();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(2)
                .setPageSize(4)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);

        repositoryPagedList = (new LivePagedListBuilder<Long, Repository>(factory, config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Repository>> getUserPagedList() {
        return repositoryPagedList;
    }
}
