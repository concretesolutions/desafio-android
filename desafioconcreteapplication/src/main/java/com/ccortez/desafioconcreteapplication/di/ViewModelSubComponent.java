package com.ccortez.desafioconcreteapplication.di;

import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryListViewModel;
import com.ccortez.desafioconcreteapplication.viewmodel.RepositoryViewModel;
import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link com.ccortez.desafioconcreteapplication.viewmodel.RepositoryViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    RepositoryListViewModel repositoryListViewModel();
    RepositoryViewModel repositoryViewModel();
}
