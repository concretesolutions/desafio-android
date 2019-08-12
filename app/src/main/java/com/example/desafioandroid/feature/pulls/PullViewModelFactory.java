package com.example.desafioandroid.feature.pulls;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PullViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String user, repository;

    public PullViewModelFactory(Application mApplication, String user, String repository) {
        this.mApplication = mApplication;
        this.user = user;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PullViewModel(mApplication, user, repository);
    }
}
