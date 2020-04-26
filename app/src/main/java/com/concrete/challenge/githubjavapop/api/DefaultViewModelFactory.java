package com.concrete.challenge.githubjavapop.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.concrete.challenge.githubjavapop.ui.repository.RepositoryViewModel;

public class DefaultViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.equals(RepositoryViewModel.class)) {
            return (T) new RepositoryViewModel(new Api(), SingleSchedulers.INSTANCE);
        } else {
            return null;
        }
    }
}
