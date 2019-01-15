package br.com.appdesafio.di;

import android.app.Application;

import br.com.appdesafio.repository.ListRepository;
import br.com.appdesafio.service.IService;
import br.com.appdesafio.viewmodel.ListRepositoryViewModel;
import dagger.Module;
import dagger.Provides;

/**
 *Activity module that lists the repositories.
 */
@Module
public class ListRepositoryActivityModule {

    @Provides
    static ListRepositoryViewModel provideRepositoryViewModel(final Application application,
                                                              final IService iService) {
        return new ListRepositoryViewModel(new ListRepository(iService, application), application);
    }
}




