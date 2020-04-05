package com.igormeira.githubpop.repository;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.igormeira.githubpop.model.Repository;
import com.igormeira.githubpop.data.RepositoryData;

import java.util.List;

public class RepositoriesViewModel extends AndroidViewModel {

    private RepositoryData repositoryData;
    private long currentPage;

    public RepositoriesViewModel(@NonNull Application application) {
        super(application);
        repositoryData = new RepositoryData();
        currentPage = 1;
    }

    public LiveData<List<Repository>> getAllRepositories() {
        return repositoryData.getRepositoriesData(currentPage);
    }

    public LiveData<List<Repository>> addDataToList() {
        currentPage += 1;
        return repositoryData.getRepositoriesData(currentPage);
    }
}
