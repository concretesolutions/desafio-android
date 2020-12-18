package com.ccortez.desafioconcreteapplication.viewmodel;

import androidx.collection.ArrayMap;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.ccortez.desafioconcreteapplication.di.ViewModelSubComponent;

@Singleton
public class RepositoryViewModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public RepositoryViewModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(RepositoryListViewModel.class, () -> viewModelSubComponent.repositoryListViewModel());
        creators.put(RepositoryViewModel.class, () -> viewModelSubComponent.repositoryViewModel());
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}