package com.example.desafioandroid.feature.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RepositoryViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private int currentPage;

    public RepositoryViewModelFactory(Application application, int currentPage) {
        this.mApplication = application;
        this.currentPage = currentPage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RepositoryViewModel(mApplication, currentPage);
    }
}
