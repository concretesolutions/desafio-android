package com.jcjm.desafioandroid.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.jcjm.desafioandroid.repository.Repository;
import com.jcjm.desafioandroid.viewmodel.PagingGitRepositoriesViewModel;
import com.jcjm.desafioandroid.viewmodel.PullRequestViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PagingGitRepositoriesViewModel.class)) {
            return (T) new PagingGitRepositoriesViewModel(repository);
        }
        if (modelClass.isAssignableFrom(PullRequestViewModel.class)) {
            return (T) new PullRequestViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
