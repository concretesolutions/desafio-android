package com.concrete.challenge.githubjavapop.ui.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.concrete.challenge.githubjavapop.api.RepositoryApi;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.Repository;

import java.util.ArrayList;

public class RepositoryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Repository>> repositories;

    public RepositoryViewModel() {
        repositories = new MutableLiveData<>();
    }

    public void loadRepositories(Context context, int lastId) {
        new RepositoryApi().getRepositories(lastId, new RepositoryApi.RepositoryCallback<RepositoriesResponse>() {
            @Override
            public void onResponse(RepositoriesResponse result) {
                ArrayList<Repository> temp = repositories.getValue();
                if(temp == null) temp = new ArrayList<>();
                temp.addAll(result.items);
                repositories.setValue(temp);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public LiveData<ArrayList<Repository>> getRepositories() {
        return repositories;
    }
}
