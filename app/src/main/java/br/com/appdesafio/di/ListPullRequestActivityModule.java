package br.com.appdesafio.di;

import android.app.Application;

import br.com.appdesafio.model.persistence.SharedPreference;
import br.com.appdesafio.repository.ListRepository;
import br.com.appdesafio.service.IService;
import br.com.appdesafio.viewmodel.LisRepositoryViewModel;
import br.com.appdesafio.viewmodel.ListPullRequestViewModel;
import dagger.Module;
import dagger.Provides;
@Module
public class ListPullRequestActivityModule {

    @Provides
    static ListPullRequestViewModel providePullRequestViewModel(final Application application,
                                                                final IService iService,
                                                                final SharedPreference sharedPreference) {//*, final AppExecutors appExecutors, final AppDatabase appDatabase, final SharedPreferencesUtil sharedPreferencesUtil*//*) {
        return new ListPullRequestViewModel(new ListRepository(iService, sharedPreference, application ), application);
    }
}
