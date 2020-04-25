package com.concrete.challenge.githubjavapop.ui.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.concrete.challenge.githubjavapop.R;
import com.concrete.challenge.githubjavapop.api.RepositoryApi;
import com.concrete.challenge.githubjavapop.api.SingleSchedulers;
import com.concrete.challenge.githubjavapop.domain.RepositoriesResponse;
import com.concrete.challenge.githubjavapop.domain.Repository;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;

public class RepositoryViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Repository>> repositories;
    private MutableLiveData<Throwable> error;
    private RepositoryApi repositoryApi;
    private SingleSchedulers singleSchedulers;
    private CompositeDisposable disposable;

    public RepositoryViewModel(RepositoryApi repositoryApi, SingleSchedulers singleSchedulers) {
        this.repositoryApi = repositoryApi;
        this.singleSchedulers = singleSchedulers;
        disposable = new CompositeDisposable();
        repositories = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public void loadRepositories(int page) {
        disposable.add(repositoryApi.getRepositories(page)
                .compose(singleSchedulers.applySchedulers())
                .subscribe(this::onSuccess, this::onError));
    }

    private <T> void onSuccess(T result) {
        RepositoriesResponse repositoriesResponse = (RepositoriesResponse) result;
        ArrayList<Repository> temp = repositories.getValue();
        if(temp == null) temp = new ArrayList<>();
        temp.addAll(repositoriesResponse.items);
        repositories.setValue(temp);
    }

    private void onError(Throwable error) {
        this.error.setValue(error);
    }

    public LiveData<ArrayList<Repository>> getRepositories() {
        return repositories;
    }

    public LiveData<Throwable> getError() {
        return error;
    }
}
