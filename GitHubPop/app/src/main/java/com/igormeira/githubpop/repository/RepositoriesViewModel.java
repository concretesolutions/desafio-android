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

    public RepositoriesViewModel(@NonNull Application application) {
        super(application);
        repositoryData = new RepositoryData();
    }

    public LiveData<List<Repository>> getAllRepositories() {
        return repositoryData.getRepositoriesData();
    }
}
